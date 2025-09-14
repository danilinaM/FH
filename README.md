# Fitness House Test Automation

Автоматизация тестирования личного кабинета Fitness House с использованием Selenide, REST Assured и Cucumber.

## Описание

Проект содержит автоматизированные UI и API тесты личного кабинета пользователя Fitness House. 

## Структура проекта

### Page Objects
- `LoginPage.java` - страница авторизации
- `BasePage.java` - базовый класс для всех страниц
- `PageFactory.java` - фабрика для создания Page Objects

### Step Definitions
- `LoginSteps.java` - шаги для UI тестов логина
- `ApiSteps.java` - шаги для API тестов
- `AuthHelper.java` - вспомогательные методы авторизации
- `CommonSteps.java` - общие шаги для проверки элементов
- `WebDriverHooks.java` - хуки для управления браузером

### Тестовые данные
- `TestData.java` - константы и тестовые данные

### Feature файлы
- `ui.Login.feature` - сценарии UI тестов
- `api.feature` - сценарии API тестов

### Модели и билдеры
- `User.java` - модель пользователя
- `UserBuilder.java` - билдер для создания объектов User

## Требования к тестовой среде

### Системные требования
- **Java**: 17+ 
- **ОС**: Windows 10/11, macOS, Linux

### Браузеры
- **Chrome**



