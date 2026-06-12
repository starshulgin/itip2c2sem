// ПЛАГИНЫ
plugins {
    kotlin("jvm") version "2.1.20"  
    application                      
}

// НАСТРОЙКА РЕПОЗИТОРИЕВ ДЛЯ ПЛАГИНОВ
import java.io.File
import java.time.LocalDateTime

// Настройка JVM для Kotlin
kotlin {
    jvmToolchain(25)
}

// Настройка целевой версии JVM для компиляции
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
    }
}


// Мета-данные проекта (группа и версия)
group = "org.example"
version = "1.0-SNAPSHOT"

// НАСТРОЙКИ ПРИЛОЖЕНИЯ (точка входа)
application {
    mainClass = "org.example.MainKt"
}

// РЕПОЗИТОРИИ (откуда скачивать зависимости)
repositories {
    mavenCentral()
}

// ЗАВИСИМОСТИ (библиотеки проекта)
dependencies {
    // Apache Commons Lang для работы со строками
    implementation("org.apache.commons:commons-lang3:3.12.0")

    // Логирование SLF4J + Logback
    implementation("ch.qos.logback:logback-classic:1.4.7")
    implementation("org.slf4j:slf4j-api:2.0.7")

    // Библиотека для тестирования JUnit
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
}

// НАСТРОЙКИ ТЕСТИРОВАНИЯ
tasks.withType<Test> {
    useJUnitPlatform()
}

// ЗАДАЧА
abstract class GenerateBuildInfoTask : DefaultTask() {

    @get:Input
    abstract val gitCommitHash: Property<String>

    @OutputDirectory
    val outputDir = project.layout.buildDirectory.dir("resources/main/META-INF")

    @TaskAction
    fun generateBuildInfo() {
        val userName = System.getenv().getOrDefault("USERNAME", System.getenv().getOrDefault("USER", "Unknown"))
        val osName = System.getProperty("os.name")
        val javaVersion = System.getProperty("java.version")
        val dateTime = LocalDateTime.now().toString()
        val message = "Добро пожаловать в проект $project.name!"

        val outputDirFile = outputDir.get().asFile
        outputDirFile.mkdirs()
        val propsFile = File(outputDirFile, "build-passport.properties")
        propsFile.writeText("""username=$userName
os=$osName
javaVersion=$javaVersion
buildTime=$dateTime
message=$message
""".trimIndent())

        println("======================================")
        println("Генерация паспорта сборки выполнена")
        println("Пользователь: $userName")
        println("ОС: $osName")
        println("Java: $javaVersion")
        println("Дата сборки: $dateTime")
        println("Сообщение: $message")
        println("======================================")
    }
}

// РЕГИСТРАЦИЯ ЗАДАЧИ
tasks.register<GenerateBuildInfoTask>("generateBuildPassport") {
    group = "Custom"
    description = "Генерирует файл с информацией о версии и дате сборки"
    gitCommitHash.set(project.findProperty("gitCommitHash") as? String ?: "")
}

// СВЯЗИ ЗАДАЧ
tasks.named("processResources") {
    dependsOn(tasks.named("generateBuildPassport"))
}