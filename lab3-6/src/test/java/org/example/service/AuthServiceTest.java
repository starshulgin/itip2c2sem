package org.example.service;

import org.example.model.dto.RegisterRequest;
import org.example.model.enums.UserRole;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUser() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Иван Иванов");
        request.setEmail("ivan@example.com");
        request.setPassword("qwerty123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");

        authService.register(request);

        verify(userRepository, times(1)).existsByEmail(request.getEmail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExistsForRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Иван Иванов");
        request.setEmail("existing@example.com");
        request.setPassword("qwerty123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.register(request));

        assertEquals("Пользователь с таким email уже существует", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldRegisterAdmin() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Админ Админов");
        request.setEmail("admin@example.com");
        request.setPassword("admin123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_admin_password");

        authService.registerAdmin(request);

        verify(userRepository, times(1)).existsByEmail(request.getEmail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExistsForRegisterAdmin() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Админ Админов");
        request.setEmail("existing@example.com");
        request.setPassword("admin123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authService.registerAdmin(request));

        assertEquals("Пользователь с таким email уже существует", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldSaveUserWithCorrectRoleForRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Обычный Пользователь");
        request.setEmail("user@example.com");
        request.setPassword("user123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");

        authService.register(request);

        verify(userRepository).save(argThat(user ->
                user.getRole() == UserRole.ROLE_USER
        ));
    }

    @Test
    void shouldSaveUserWithCorrectRoleForRegisterAdmin() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Администратор");
        request.setEmail("admin2@example.com");
        request.setPassword("admin456");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_admin_password");

        authService.registerAdmin(request);

        verify(userRepository).save(argThat(user ->
                user.getRole() == UserRole.ROLE_ADMIN
        ));
    }
}