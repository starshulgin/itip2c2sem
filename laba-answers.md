# Ответы на контрольные вопросы по Apache Maven
## На примере проекта Test-Maven

---

## Структура проекта

```
/Users/vladimir/MavenProject/
├── pom.xml                          # Конфигурация Maven
├── src/
│   └── main/
│       └── java/
│           └── org/example/
│               └── Main.java        # Исходный код
└── target/                          # Результат сборки
    ├── classes/                     # Скомпилированные .class файлы
    ├── site/
    │   └── spotbugs.html            # Отчёт о качестве кода
    └── maven-status/                # Статус сборки
```

**Результат анализа SpotBugs:** 0 ошибок, 0 предупреждений (код чистый)

---

## Контрольные вопросы

### 1. Что такое Apache Maven и для чего он используется?

**Maven** — это инструмент автоматизации сборки проектов на Java, основанный на концепции Project Object Model (POM).

**Основные возможности Maven:**
- **Управление зависимостями** — автоматическая загрузка библиотек
- **Сборка проекта** — компиляция, тестирование, упаковка
- **Стандартизация** — единая структура для всех проектов
- **Отчётность** — генерация документации и отчётов о качестве

**На примере моего проекта:**
```
Проект Test-Maven использует Maven для:
├── Загрузки SLF4J (логирование) и Jackson (JSON)
├── Компиляции Main.java и User.java
├── Запуска статического анализа SpotBugs
└── Создания исполняемого JAR-файла
```

**Без Maven** разработчику пришлось бы:
1. Вручную скачивать `.jar` файлы библиотек
2. Прописывать classpath для компиляции
3. Самостоятельно управлять версиями зависимостей

---

### 2. Как установить Maven на различные операционные системы?

**macOS:**
```bash
# Через Homebrew (рекомендуется)
brew install maven

# Проверка установки
mvn -version
```

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install maven

# Проверка
mvn -version
```

**Windows:**
1. Скачать дистрибутив с https://maven.apache.org/download.cgi
2. Распаковать в `C:\Program Files\Apache\maven`
3. Добавить `bin` в переменную среды `PATH`
4. Проверить: `mvn -version`

**Требования:**
- Установленный JDK (Java Development Kit) версии 8 или выше
- Переменная среды `JAVA_HOME` должна указывать на JDK

**Моя среда:**
- ОС: macOS (Darwin)
- JDK: OpenJDK 25
- Maven: установлен через Homebrew

---

### 3. Какова структура проекта Maven?

**Стандартная структура Maven (Convention over Configuration):**

```
MavenProject/
├── pom.xml                          # Дескриптор проекта (Project Object Model)
│
├── src/
│   ├── main/
│   │   ├── java/                    # Исходный код приложения
│   │   │   └── org/example/
│   │   │       └── Main.java
│   │   ├── resources/               # Ресурсы (конфигурационные файлы)
│   │   └── webapp/                  # Веб-приложение (для WAR)
│   │
│   ├── test/
│   │   ├── java/                    # Тестовый код
│   │   └── resources/               # Тестовые ресурсы
│   │
│   └── site/                        # Документация проекта
│
├── target/                          # Выходная директория (генерируется)
│   ├── classes/                     # Скомпилированные .class файлы
│   ├── test-classes/                # Скомпилированные тесты
│   ├── *.jar                        # Собранный артефакт
│   └── site/                        # Сгенерированная документация
│
└── README.md                        # Документация проекта
```

**В моём проекте:**
- `src/main/java/org/example/Main.java` — главный класс с методом `main()`
- `src/main/java/org/example/User.java` — класс модели данных (в том же файле)
- `target/classes/` — скомпилированные байт-коды
- `target/site/spotbugs.html` — отчёт статического анализатора

---

### 4. Что такое POM файл и какова его роль в проекте Maven?

**POM (Project Object Model)** — это XML-файл `pom.xml`, который является центральным элементом любого Maven-проекта.

**Роль POM:**
1. **Описание проекта** — название, версия, авторы
2. **Конфигурация сборки** — плагины, настройки компиляции
3. **Управление зависимостями** — список библиотек
4. **Настройка отчётов** — документация, метрики качества

**Мой pom.xml:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <!-- 1. Координаты проекта (GAV) -->
    <groupId>org.example</groupId>      <!-- Группа/организация -->
    <artifactId>Test-Maven</artifactId>  <!-- Название проекта -->
    <version>1.0-SNAPSHOT</version>      <!-- Версия сборки -->
    
    <!-- 2. Свойства сборки -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <!-- 3. Зависимости -->
    <dependencies>...</dependencies>
    
    <!-- 4. Плагины сборки -->
    <build>...</build>
</project>
```

**Без POM Maven не может:**
- Определить структуру проекта
- Загрузить зависимости
- Выполнить сборку

---

### 5. Какова структура файла POM?

**Полная структура POM с примерами из моего проекта:**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
    <!-- 1. Модель POM -->
    <modelVersion>4.0.0</modelVersion>
    
    <!-- 2. Координаты проекта (GAV - Group, Artifact, Version) -->
    <groupId>org.example</groupId>
    <artifactId>Test-Maven</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>  <!-- Тип артефакта: jar, war, pom -->
    
    <!-- 3. Метаданные проекта -->
    <name>Test-Maven</name>
    <description>Учебный проект Maven</description>
    <url>https://github.com/username/project</url>
    
    <!-- 4. Свойства (переменные) -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <jackson.version>2.15.2</jackson.version>  <!-- Своя переменная -->
    </properties>
    
    <!-- 5. Зависимости -->
    <dependencies>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.7</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>
    </dependencies>
    
    <!-- 6. Управление зависимостями (для наследования) -->
    <dependencyManagement>
        <dependencies>
            <!-- Версии для подмодулей -->
        </dependencies>
    </dependencyManagement>
    
    <!-- 7. Настройка сборки -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <release>17</release>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
    <!-- 8. Профили сборки -->
    <profiles>
        <profile>
            <id>production</id>
            <!-- Специфичные настройки -->
        </profile>
    </profiles>
    
    <!-- 9. Отчёты -->
    <reporting>
        <plugins>
            <plugin>
                <groupId>com.github.spotbugs</groupId>
                <artifactId>spotbugs-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </reporting>
</project>
```

---

### 6. Что такое зависимости (dependencies) в Maven и как они определяются?

**Зависимость (dependency)** — это внешняя библиотека, необходимая для работы проекта.

**Структура зависимости:**
```xml
<dependency>
    <groupId>org.slf4j</groupId>           <!-- Группа разработчиков -->
    <artifactId>slf4j-simple</artifactId>  <!-- Название библиотеки -->
    <version>2.0.7</version>               <!-- Версия -->
    <scope>compile</scope>                 <!-- Область использования -->
    <optional>false</optional>             <!-- Необязательная зависимость -->
</dependency>
```

**В моём проекте:**

| Зависимость | Назначение | Используется в коде |
|-------------|------------|---------------------|
| `slf4j-simple:2.0.7` | Логирование | `Logger`, `LoggerFactory` |
| `jackson-databind:2.15.2` | Сериализация JSON | `ObjectMapper` |

**Пример использования в коде:**
```java
// Импорты из зависимостей
import org.slf4j.Logger;                    // Из slf4j-simple
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;  // Из jackson-databind

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();  // Использование библиотеки
        String json = mapper.writeValueAsString(user);
    }
}
```

**Как Maven обрабатывает зависимости:**
1. Читает `pom.xml`
2. Проверяет локальный репозиторий `~/.m2/repository`
3. Если нет — скачивает из центрального репозитория
4. Сохраняет в локальный кэш
5. Добавляет в classpath при компиляции и запуске

---

### 7. Что такое репозиторий Maven и какие виды репозиториев существуют?

**Репозиторий Maven** — это хранилище артефактов (`.jar` файлов) и метаданных.

**Виды репозиториев:**

| Вид | Расположение | Описание |
|-----|--------------|----------|
| **Локальный** | `~/.m2/repository` | Кэш на компьютере разработчика |
| **Центральный** | https://repo.maven.apache.org | Официальный репозиторий Maven |
| **Удалённый** | Корпоративный сервер | Nexus, Artifactory, JFrog |

**Схема работы:**
```
┌─────────────┐    Проверка    ┌──────────────┐
│   Проект    │ ─────────────> │  Локальный   │
│   Maven     │                │  репозиторий │
└─────────────┘                └──────────────┘
                                      │
                              Если нет? │
                                      ▼
                               ┌──────────────┐
                               │  Центральный │
                               │  репозиторий │
                               │  (Internet)  │
                               └──────────────┘
                                      │
                              Скачивание .jar │
                                      ▼
                               ┌──────────────┐
                               │  Локальный   │
                               │  (сохранение)│
                               └──────────────┘
```

**Пример из моего проекта:**
```
При первой сборке Maven скачивает:
├── slf4j-simple-2.0.7.jar
├── jackson-databind-2.15.2.jar
├── jackson-core-2.15.2.jar         (транзитивная)
└── jackson-annotations-2.15.2.jar  (транзитивная)

Все файлы сохраняются в ~/.m2/repository/
```

---

### 8. Как добавить зависимость в проект Maven?

**Пошаговая инструкция:**

**Шаг 1:** Найти координаты зависимости на https://mvnrepository.com

**Шаг 2:** Добавить в `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>31.1-jre</version>
    </dependency>
</dependencies>
```

**Шаг 3:** Обновить проект
```bash
mvn clean install
```

**Шаг 4:** Использовать в коде
```java
import com.google.common.collect.Lists;

List<String> list = Lists.newArrayList();
```

**Пример из моего проекта:**
```xml
<!-- Было: нет зависимости -->
<!-- Стало: добавлено в pom.xml -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
```

```java
// Теперь можно использовать в коде
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper mapper = new ObjectMapper();
String json = mapper.writeValueAsString(user);
```

**Maven автоматически:**
- Скачает `jackson-databind-2.15.2.jar`
- Скачает транзитивные зависимости (`jackson-core`, `jackson-annotations`)
- Добавит в classpath при компиляции

---

### 9. Что такое плагины в Maven и как они используются?

**Плагин Maven** — это расширение, которое выполняет конкретную задачу сборки.

**Категории плагинов:**

| Категория | Примеры | Назначение |
|-----------|---------|------------|
| **Build** | `compiler`, `jar`, `war` | Сборка проекта |
| **Testing** | `surefire`, `failsafe` | Запуск тестов |
| **Documentation** | `javadoc`, `site` | Генерация документации |
| **Quality** | `spotbugs`, `checkstyle` | Анализ качества |
| **Deployment** | `deploy`, `release` | Публикация артефактов |

**Плагины в моём проекте:**

```xml
<build>
    <plugins>
        <!-- 1. Компилятор Java -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <release>17</release>  <!-- Версия Java -->
            </configuration>
        </plugin>
        
        <!-- 2. Запуск приложения -->
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>exec-maven-plugin</artifactId>
            <version>3.1.0</version>
            <configuration>
                <mainClass>org.example.Main</mainClass>
            </configuration>
        </plugin>
        
        <!-- 3. Анализ кода (SpotBugs) -->
        <plugin>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs-maven-plugin</artifactId>
            <version>4.9.4.0</version>
            <configuration>
                <failOnError>true</failOnError>     <!-- Ошибка при багах -->
                <threshold>Medium</threshold>       <!-- Уровень серьёзности -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

**Вызов плагинов:**
```bash
# Компиляция (compiler:compile)
mvn compile

# Запуск приложения (exec:java)
mvn exec:java

# Проверка кода (spotbugs:check)
mvn spotbugs:check

# Генерация отчёта (spotbugs:spotbugs)
mvn spotbugs:spotbugs
```

---

### 10. Как создать новый проект Maven с помощью команды Maven?

**Создание через archetype (шаблон):**

```bash
mvn archetype:generate \
  -DgroupId=org.example \
  -DartifactId=MyProject \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false
```

**Параметры:**
| Параметр | Описание |
|----------|----------|
| `groupId` | Имя пакета организации |
| `artifactId` | Название проекта |
| `archetypeArtifactId` | Шаблон проекта |
| `interactiveMode` | Режим диалога |

**Популярные archetype:**
- `maven-archetype-quickstart` — простой проект с тестами
- `maven-archetype-webapp` — веб-приложение (WAR)
- `maven-archetype-j2ee-simple` — J2EE приложение

**Результат выполнения:**
```
MyProject/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/example/
│   │           └── App.java
│   └── test/
│       └── java/
│           └── org/example/
│               └── AppTest.java
```

**Мой проект** был создан аналогично (вручную или через IDE).

---

### 11. Что такое цели (goals) и фазы (phases) в Maven и в чем их отличие?

**Goal (цель)** — конкретная задача, которую выполняет плагин.

**Phase (фаза)** — этап жизненного цикла сборки.

**Отличия:**

| Характеристика | Goal (цель) | Phase (фаза) |
|----------------|-------------|--------------|
| **Привязка** | К плагину | К жизненному циклу |
| **Выполнение** | Независимое | Последовательное |
| **Пример** | `compiler:compile` | `compile` |
| **Синтаксис** | `mvn plugin:goal` | `mvn phase` |

**Примеры целей:**
```bash
# Конкретные цели плагинов
mvn compiler:compile      # Компиляция исходного кода
mvn compiler:testCompile # Компиляция тестов
mvn surefire:test         # Запуск тестов
mvn jar:jar               # Создание JAR
mvn spotbugs:check        # Проверка кода
mvn javadoc:javadoc       # Генерация JavaDoc
```

**Примеры фаз:**
```bash
# Фазы жизненного цикла
mvn validate    # Проверка проекта
mvn compile     # Компиляция
mvn test        # Тестирование
mvn package     # Упаковка
mvn install     # Установка в локальный репозиторий
mvn deploy      # Развёртывание
```

**Важное отличие:**
```bash
# При выполнении фазы выполняются ВСЕ предыдущие фазы
mvn package  # Выполняет: validate → compile → test → package

# При выполнении цели выполняется ТОЛЬКО эта цель
mvn jar:jar  # Только создание JAR (без компиляции и тестов)
```

**В моём проекте:**
```bash
mvn compile          # Фаза: компилирует Main.java
mvn exec:java        # Цель: запускает main() метод
mvn spotbugs:check   # Цель: проверяет код на ошибки
```

---

### 12. Как выполнить команду сборки проекта в Maven?

**Основные команды сборки:**

```bash
# 1. Компиляция исходного кода
mvn clean compile

# 2. Запуск тестов
mvn test

# 3. Упаковка в JAR/WAR
mvn package

# 4. Установка в локальный репозиторий
mvn install

# 5. Полная сборка (рекомендуется)
mvn clean install
```

**Что происходит при `mvn clean install`:**

```
Этап 1: clean
├── Удаление директории target/
└── Очистка предыдущих результатов

Этап 2: validate
├── Проверка структуры проекта
└── Проверка pom.xml

Этап 3: compile
├── Компиляция src/main/java
└── Создание target/classes/

Этап 4: test
├── Компиляция src/test/java
└── Запуск тестов (если есть)

Этап 5: package
├── Создание JAR/WAR файла
└── target/Test-Maven-1.0-SNAPSHOT.jar

Этап 6: install
└── Копирование в ~/.m2/repository/
```

**Для моего проекта:**
```bash
# Компиляция и проверка кода
mvn clean compile spotbugs:check

# Запуск приложения
mvn exec:java

# Вывод:
# [main] INFO org.example.Main - приложение запущено!
# [main] INFO org.example.Main - объект в JSON: {"name":"Vladimir","age":20}
```

---

### 13. Что такое жизненный цикл сборки (build lifecycle) в Maven?

**Жизненный цикл (lifecycle)** — последовательность фаз для выполнения сборки.

**Три основных жизненных цикла:**

| Цикл | Назначение | Фазы |
|------|------------|------|
| **default** | Основная сборка | validate → compile → test → package → install → deploy |
| **clean** | Очистка | pre-clean → clean → post-clean |
| **site** | Документация | pre-site → site → post-site → site-deploy |

**Детализация цикла default:**

```
validate         → Проверка структуры проекта и параметров
  ↓
initialize       → Инициализация (создание директорий)
  ↓
generate-sources → Генерация исходного кода
  ↓
process-sources  → Обработка ресурсов (фильтрация)
  ↓
generate-resources → Генерация ресурсов
  ↓
process-resources   → Копирование ресурсов в target/
  ↓
compile          → Компиляция основного кода
  ↓
process-classes  → Обработка классов (байт-код)
  ↓
generate-test-sources → Генерация тестового кода
  ↓
process-test-sources  → Обработка тестов
  ↓
generate-test-resources → Генерация тестовых ресурсов
  ↓
process-test-resources  → Копирование тестовых ресурсов
  ↓
test-compile     → Компиляция тестов
  ↓
process-test-classes → Обработка тестовых классов
  ↓
test             → Запуск тестов (Surefire)
  ↓
prepare-package  → Подготовка к упаковке
  ↓
package          → Упаковка в JAR/WAR/EAR
  ↓
pre-integration-test → Подготовка интеграционных тестов
  ↓
integration-test     → Запуск интеграционных тестов
  ↓
post-integration-test → Обработка результатов
  ↓
verify           → Проверка качества
  ↓
install          → Установка в локальный репозиторий
  ↓
deploy           → Развёртывание в удалённый репозиторий
```

**Пример выполнения:**
```bash
# Выполняет все фазы до package включительно
mvn package

# Выполняет все фазы до install включительно
mvn install

# Пропуск тестов
mvn install -DskipTests
```

---

### 14. Как настроить профили (profiles) в Maven для разных сред?

**Профиль** — набор конфигураций для特定ной среды (dev, test, prod).

**Пример конфигурации:**

```xml
<profiles>
    <!-- Профиль для разработки -->
    <profile>
        <id>development</id>
        <activation>
            <activeByDefault>true</activeByDefault>  <!-- Активен по умолчанию -->
        </activation>
        <properties>
            <environment>dev</environment>
            <logging.level>DEBUG</logging.level>
        </properties>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-devtools</artifactId>
                <scope>runtime</scope>
            </dependency>
        </dependencies>
    </profile>
    
    <!-- Профиль для тестирования -->
    <profile>
        <id>testing</id>
        <properties>
            <environment>test</environment>
            <logging.level>INFO</logging.level>
        </properties>
    </profile>
    
    <!-- Профиль для продакшена -->
    <profile>
        <id>production</id>
        <properties>
            <environment>prod</environment>
            <logging.level>ERROR</logging.level>
        </properties>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <optimize>true</optimize>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
```

**Активация профиля:**

```bash
# Явная активация
mvn install -P production

# Несколько профилей
mvn install -P testing,production

# Деактивация
mvn install -P !development
```

**Автоматическая активация:**
```xml
<activation>
    <jdk>17</jdk>           <!-- По версии JDK -->
    <os>
        <name>Windows 10</name>
    </os>                   <!-- По ОС -->
    <property>
        <name>environment</name>
        <value>prod</value>
    </property>             <!-- По свойству -->
</activation>
```

---

### 15. Как управлять версиями зависимостей в Maven?

**Способ 1: Явное указание в `<dependencyManagement>`**

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.7</version>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- В зависимостях версия не указывается -->
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <!-- Версия берётся из dependencyManagement -->
    </dependency>
</dependencies>
```

**Способ 2: Через свойства (`<properties>`)**

```xml
<properties>
    <jackson.version>2.15.2</jackson.version>
    <slf4j.version>2.0.7</slf4j.version>
</properties>

<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>${jackson.version}</version>
    </dependency>
</dependencies>
```

**Способ 3: BOM (Bill of Materials)**

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson</groupId>
            <artifactId>jackson-bom</artifactId>
            <version>2.15.2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**Рекомендации:**
- Использовать `<dependencyManagement>` для многопроектных сборок
- Использовать свойства для простых проектов
- Фиксировать версии (избегать `LATEST`, `RELEASE`)

---

### 16. Что такое "SNAPSHOT" версии в Maven и как они используются?

**SNAPSHOT** — специальная версия, обозначающая черновую (разрабатываемую) сборку.

**Формат версии:**
```
<major>.<minor>.<patch>-SNAPSHOT
  1     .   0    .   0   -SNAPSHOT
```

**Особенности SNAPSHOT:**

| Характеристика | Описание |
|----------------|----------|
| **Изменчивость** | Версия может меняться со временем |
| **Проверка** | Maven проверяет актуальность при каждой сборке |
| **Хранение** | В репозитории хранится с меткой времени |
| **Использование** | Только для разработки |

**Пример из моего проекта:**
```xml
<version>1.0-SNAPSHOT</version>
```

**Как Maven обрабатывает SNAPSHOT:**
```
1. При сборке Maven проверяет удалённый репозиторий
2. Если есть новая версия — скачивает
3. В локальном репозитории хранится:
   artifact-1.0-20260318.120000-1.jar
   artifact-1.0-20260318.150000-2.jar
```

**Сравнение версий:**

| Версия | Описание | Когда использовать |
|--------|----------|-------------------|
| `1.0-SNAPSHOT` | Черновая | Во время разработки |
| `1.0` | Релизная | Для продакшена |
| `1.0.1-SNAPSHOT` | Исправления | После релиза, перед следующим патчем |

**Команды для работы с SNAPSHOT:**
```bash
# Принудительное обновление SNAPSHOT
mvn clean install -U

# Развертывание SNAPSHOT в удалённый репозиторий
mvn deploy
```

---

### 17. Как использовать Maven для создания отчета о качестве кода?

**SpotBugs в моём проекте:**

**Конфигурация плагина:**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs-maven-plugin</artifactId>
            <version>4.9.4.0</version>
            <configuration>
                <jvmArgs>-Djava.awt.headless=true</jvmArgs>
                <failOnError>true</failOnError>   <!-- Ошибка сборки при багах -->
                <threshold>Medium</threshold>     <!-- Уровень серьёзности -->
                <effort>default</effort>          <!-- Глубина анализа -->
            </configuration>
        </plugin>
    </plugins>
</build>

<reporting>
    <plugins>
        <plugin>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs-maven-plugin</artifactId>
            <version>4.9.4.0</version>
        </plugin>
    </plugins>
</reporting>
```

**Уровни серьёзности (threshold):**
| Уровень | Описание |
|---------|----------|
| `Low` | Все предупреждения, включая малозначимые |
| `Medium` | Средние и высокие (рекомендуется) |
| `High` | Только критические ошибки |

**Запуск анализа:**
```bash
# Проверка кода
mvn spotbugs:check

# Генерация HTML-отчёта
mvn spotbugs:spotbugs

# Отчёт в консоль
mvn spotbugs:debug
```

**Результат анализа моего проекта:**
```
┌────────────┬──────┬────────┬────────────────┐
│   Классы   │ Баги │ Ошибки │ Отсутствуют    │
├────────────┼──────┼────────┼────────────────┤
│     2      │  0   │   0    │       0        │
└────────────┴──────┴────────┴────────────────┘

Отчёт: target/site/spotbugs.html
```

**Типы обнаруживаемых проблем:**
- `NP` — NullPointerException
- `DM` — Performance issues
- `RV` — Ignored return value
- `UuF` — Unused fields
- `UwF` — Unwritten fields

**Другие плагины качества:**
- `maven-checkstyle-plugin` — проверка стиля кода
- `maven-pmd-plugin` — статический анализ
- `maven-javadoc-plugin` — генерация документации

---

### 18. Какие команды Maven используются для очистки проекта, сборки, тестирования и установки?

**Таблица основных команд:**

| Команда | Описание | Результат |
|---------|----------|-----------|
| `mvn clean` | Очистка | Удаляет `target/` |
| `mvn validate` | Проверка | Проверяет структуру |
| `mvn compile` | Компиляция | `.java` → `.class` |
| `mvn test` | Тесты | Запуск unit-тестов |
| `mvn package` | Упаковка | Создание JAR/WAR |
| `mvn verify` | Проверка | Проверка качества |
| `mvn install` | Установка | В локальный репозиторий |
| `mvn deploy` | Развертывание | В удалённый репозиторий |

**Комбинированные команды:**

```bash
# Полная сборка
mvn clean install

# Сборка без тестов
mvn clean install -DskipTests

# Сборка с проверкой кода
mvn clean install spotbugs:check

# Очистка и компиляция
mvn clean compile

# Запуск приложения
mvn exec:java

# Генерация отчётов
mvn site
```

**Полезные флаги:**

| Флаг | Описание |
|------|----------|
| `-U` | Принудительное обновление зависимостей |
| `-DskipTests` | Пропуск тестов |
| `-Dmaven.test.skip=true` | Пропуск компиляции и запуска тестов |
| `-P profile` | Активация профиля |
| `-X` | Подробный вывод (debug) |
| `-q` | Тихий режим (только ошибки) |

**Примеры для моего проекта:**
```bash
# Компиляция и проверка SpotBugs
mvn clean compile spotbugs:check

# Запуск Main.main()
mvn exec:java

# Вывод:
# INFO - приложение запущено!
# INFO - объект в JSON: {"name":"Vladimir","age":20}
```

---

### 19. Как интегрировать Maven с системой контроля версий, такой как Git?

**Шаг 1: Создать `.gitignore`**

```gitignore
# Игнорировать результаты сборки
target/
*.class

# Игнорировать логи
*.log

# Игнорировать IDE настройки
.idea/
*.iml
.vscode/
*.swp
*.swo

# Игнорировать системные файлы
.DS_Store
Thumbs.db
```

**Шаг 2: Инициализировать Git**

```bash
# Инициализация репозитория
git init

# Добавление файлов
git add pom.xml src/ README.md .gitignore

# Первый коммит
git commit -m "Initial Maven project structure"

# Добавление удалённого репозитория
git remote add origin https://github.com/username/Test-Maven.git

# Отправка
git push -u origin main
```

**Шаг 3: Настроить Maven для CI/CD**

```xml
<scm>
    <connection>scm:git:https://github.com/username/Test-Maven.git</connection>
    <developerConnection>scm:git:ssh://git@github.com/username/Test-Maven.git</developerConnection>
    <url>https://github.com/username/Test-Maven</url>
    <tag>HEAD</tag>
</scm>
```

**Шаг 4: GitHub Actions (опционально)**

```yaml
# .github/workflows/maven.yml
name: Maven Build

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Build with Maven
        run: mvn clean install
```

**Важно:**
- ✅ Коммитить: `pom.xml`, `src/`, `.gitignore`
- ❌ Не коммитить: `target/`, `.class`, `.iml`, IDE настройки

---

### 20. Как добавить и настроить сторонний репозиторий в проекте Maven?

**Добавление репозитория:**

```xml
<project>
    <repositories>
        <!-- Центральный репозиторий (по умолчанию) -->
        <repository>
            <id>central</id>
            <name>Maven Central</name>
            <url>https://repo.maven.apache.org/maven2</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
        
        <!-- JitPack (для GitHub проектов) -->
        <repository>
            <id>jitpack.io</id>
            <name>JitPack</name>
            <url>https://jitpack.io</url>
        </repository>
        
        <!-- Sonatype OSSRH (для SNAPSHOT) -->
        <repository>
            <id>ossrh</id>
            <name>Sonatype OSSRH</name>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
</project>
```

**Настройка аутентификации (`~/.m2/settings.xml`):**

```xml
<settings>
    <servers>
        <server>
            <id>private-repo</id>
            <username>myuser</username>
            <password>mypassword</password>
        </server>
    </servers>
</settings>
```

**Пример использования:**
```xml
<dependency>
    <groupId>com.github.user</groupId>
    <artifactId>library</artifactId>
    <version>v1.0.0</version>
</dependency>
```

---

### 21. Какие скоупы зависимостей существуют в Maven и для чего они используются?

**Scope (область видимости)** определяет, когда зависимость доступна.

**Таблица scopes:**

| Scope | Compile | Test | Runtime | Provided | System |
|-------|---------|------|---------|----------|--------|
| **Доступен при компиляции** | ✅ | ✅ | ❌ | ✅ | ✅ |
| **Доступен при тестировании** | ✅ | ✅ | ✅ | ✅ | ✅ |
| **Доступен при выполнении** | ✅ | ✅ | ✅ | ❌ | ✅ |
| **Включается в пакет** | ✅ | ❌ | ✅ | ❌ | ❌ |
| **По умолчанию** | ✅ | ❌ | ❌ | ❌ | ❌ |

**Описание каждого scope:**

**1. compile (по умолчанию)**
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.7</version>
    <!-- scope: compile (не указывается) -->
</dependency>
```
- Доступен везде
- Включается в JAR/WAR

**2. test**
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```
- Только для тестов
- Не включается в пакет

**3. provided**
```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
```
- Нужен для компиляции
- Предоставляется средой выполнения (Tomcat, Jetty)
- Не включается в пакет

**4. runtime**
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
    <scope>runtime</scope>
</dependency>
```
- Не нужен для компиляции
- Нужен при выполнении
- Включается в пакет

**5. system**
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>local-lib</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/local-lib.jar</systemPath>
</dependency>
```
- Локальный JAR файл
- Не рекомендуется использовать

**В моём проекте:**
```xml
<dependencies>
    <!-- compile (по умолчанию) - используется в коде -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>2.0.7</version>
    </dependency>
    
    <!-- compile - используется в коде -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
</dependencies>
```

---

### 22. Чем отличается плагин от зависимости в Maven?

**Зависимость (dependency)** — библиотека, используемая в коде приложения.

**Плагин (plugin)** — инструмент для выполнения задач сборки.

**Сравнительная таблица:**

| Критерий | Зависимость | Плагин |
|----------|-------------|--------|
| **Назначение** | Библиотека для кода | Инструмент сборки |
| **Использование** | В коде (`import`) | В `pom.xml` |
| **Секция POM** | `<dependencies>` | `<build><plugins>` |
| **Время** | Runtime | Build time |
| **Пример** | `jackson-databind` | `maven-compiler-plugin` |

**Пример из моего проекта:**

```xml
<!-- ЗАВИСИМОСТЬ: используется в Main.java -->
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
</dependencies>

// В коде:
import com.fasterxml.jackson.databind.ObjectMapper;
ObjectMapper mapper = new ObjectMapper();

<!-- ПЛАГИН: компилирует код -->
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
        </plugin>
    </plugins>
</build>
// В коде не используется
```

**Транзитивность:**
- Зависимости: транзитивны (загружаются зависимости зависимостей)
- Плагины: не транзитивны

**Версионирование:**
- Зависимости: фиксируются в проекте
- Плагины: могут использовать версии по умолчанию (не рекомендуется)

---

### 23. Как работает транзитивность зависимостей?

**Транзитивность** — автоматическое загрузка зависимостей зависимостей.

**Пример дерева зависимостей:**

```
Мой проект (Test-Maven)
│
└── jackson-databind:2.15.2
    ├── jackson-core:2.15.2
    └── jackson-annotations:2.15.2
```

**Как это работает:**
1. Maven читает `pom.xml`
2. Находит `jackson-databind`
3. Читает POM `jackson-databind`
4. Находит его зависимости (`jackson-core`, `jackson-annotations`)
5. Загружает все автоматически

**Просмотр дерева зависимостей:**
```bash
mvn dependency:tree
```

**Вывод для моего проекта:**
```
[INFO] --- dependency:3.6.0:tree
[INFO] org.example:Test-Maven:1.0-SNAPSHOT
[INFO] +- org.slf4j:slf4j-simple:2.0.7
[INFO] |  \- org.slf4j:slf4j-api:2.0.7
[INFO] +- com.fasterxml.jackson.core:jackson-databind:2.15.2
[INFO] |  +- com.fasterxml.jackson.core:jackson-annotations:2.15.2
[INFO] |  \- com.fasterxml.jackson.core:jackson-core:2.15.2
```

**Проблемы транзитивности:**

**1. Конфликт версий:**
```
Проект
├── lib-a:1.0 → slf4j:1.7.0
└── lib-b:2.0 → slf4j:2.0.0  (конфликт!)
```

**Решение:** Использовать `<dependencyManagement>` для фиксации версии

**2. Исключение зависимостей:**
```xml
<dependency>
    <groupId>lib-a</groupId>
    <artifactId>lib-a</artifactId>
    <version>1.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

---

### 24. Для чего нужен плагин surefire?

**Maven Surefire Plugin** — плагин для запуска unit-тестов.

**Конфигурация:**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0</version>
            <configuration>
                <includes>
                    <include>**/*Test.java</include>
                </includes>
                <excludes>
                    <exclude>**/*IntegrationTest.java</exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

**Как работает:**
1. Находит тесты по шаблону (`*Test.java`, `*Tests.java`, `Test*.java`)
2. Компилирует тесты
3. Запускает каждый тест
4. Генерирует отчёты

**Отчёты:**
- `target/surefire-reports/` — XML отчёты
- Консольный вывод при сборке

**Команды:**
```bash
# Запуск тестов (фаза test)
mvn test

# Запуск конкретного теста
mvn test -Dtest=MyTestClass

# Запуск по шаблону
mvn test -Dtest=*UserService*

# Пропуск тестов
mvn install -DskipTests
```

**Пример теста:**
```java
// src/test/java/org/example/UserTest.java
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testUserCreation() {
        User user = new User("Vladimir", 20);
        assertEquals("Vladimir", user.getName());
        assertEquals(20, user.getAge());
    }
}
```

**В моём проекте:** тесты пока не добавлены, но плагин можно использовать при их создании.

---

### 25. Как исключить транзитивную зависимость?

**Исключение зависимостей** — механизм `<exclusions>`.

**Синтаксис:**
```xml
<dependency>
    <groupId>группа</groupId>
    <artifactId>артефакт</artifactId>
    <version>версия</version>
    <exclusions>
        <exclusion>
            <groupId>исключаемая-группа</groupId>
            <artifactId>исключаемый-артефакт</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**Пример 1: Исключение логгера**
```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.0</version>
    <exclusions>
        <!-- Исключаем старый логгер -->
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**Пример 2: Замена зависимости**
```xml
<dependencies>
    <!-- Зависимость с исключением -->
    <dependency>
        <groupId>lib-a</groupId>
        <artifactId>lib-a</artifactId>
        <version>1.0</version>
        <exclusions>
            <exclusion>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    
    <!-- Явно указываем нужную версию -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.7</version>
    </dependency>
</dependencies>
```

**Проверка исключений:**
```bash
# Просмотр дерева зависимостей
mvn dependency:tree

# Детальный анализ
mvn dependency:analyze
```

**Когда использовать исключения:**
- Конфликт версий
- Нежелательные зависимости
- Замена на альтернативу
- Уменьшение размера пакета

---

## Приложение: Исходный код проекта

### Main.java

```java
package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("приложение запущено!");

        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = new User("Vladimir", 20);
            String json = mapper.writeValueAsString(user);
            logger.info("объект в JSON: {}", json);
        } catch (Exception e) {
            logger.error("ошибка сериализации", e);
        }
    }
}

class User {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Test-Maven</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.7</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <release>17</release>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <mainClass>org.example.Main</mainClass>
                </configuration>
            </plugin>
            <plugin>
                <groupId>com.github.spotbugs</groupId>
                <artifactId>spotbugs-maven-plugin</artifactId>
                <version>4.9.4.0</version>
                <configuration>
                    <jvmArgs>-Djava.awt.headless=true</jvmArgs>
                    <failOnError>true</failOnError>
                    <threshold>Medium</threshold>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Краткая шпаргалка для экзамена

| № | Тема | Ключевая фраза |
|---|------|----------------|
| 1 | Maven | Сборка + зависимости + стандартизация |
| 4 | POM | XML-конфиг проекта (GAV, dependencies, plugins) |
| 6 | Dependency | Внешняя библиотека (groupId, artifactId, version) |
| 9 | Plugin | Инструмент сборки (compiler, surefire, spotbugs) |
| 11 | Goal vs Phase | Goal = задача плагина, Phase = этап lifecycle |
| 13 | Lifecycle | default, clean, site (последовательность фаз) |
| 16 | SNAPSHOT | Черновая версия (обновляется автоматически) |
| 21 | Scopes | compile, test, provided, runtime, system |
| 22 | Plugin vs Dependency | Plugin = сборка, Dependency = код |
| 23 | Транзитивность | Зависимости зависимостей загружаются автоматически |
| 24 | Surefire | Запуск unit-тестов |
| 25 | Exclusions | Исключение транзитивных зависимостей |

**Команды:**
```bash
mvn clean install          # Полная сборка
mvn exec:java              # Запуск приложения
mvn spotbugs:check         # Проверка кода
mvn dependency:tree        # Дерево зависимостей
mvn test -Dtest=ClassName  # Запуск теста
```
