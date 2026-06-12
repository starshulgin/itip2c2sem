package org.example

// Импорт утилит для работы со строками
import org.apache.commons.lang3.StringUtils
// Импорт логгера для вывода сообщений
import org.slf4j.LoggerFactory
import java.io.File

// Точка входа в приложение
fun main(args: Array<String>) {
    // Инициализация логгера
    val log = LoggerFactory.getLogger("Main")

    log.info("Программа запускается")

    // Получение входных данных: аргумент командной строки или значение по умолчанию
    val input = if (args.isNotEmpty()) {
        args[0]
    } else {
        "Hello, World!"
    }

    // Обработка строки: переворот и капитализация
    val reversed = StringUtils.reverse(input)
    val capitalized = StringUtils.capitalize(input)

    log.info("Обработанная строка: {}", reversed)
    log.info("Капитализированная строка: {}", capitalized)

    // Чтение файла паспорта сборки из ресурсов
    val resourceStream = Thread.currentThread().contextClassLoader.getResourceAsStream("META-INF/build-passport.properties")
    val resourcePath = resourceStream?.bufferedReader()?.readText() ?: "Файл сборки не найден"
    log.info("Информация о сборке:\n{}", resourcePath)

    log.info("Программа завершена успешно")
}