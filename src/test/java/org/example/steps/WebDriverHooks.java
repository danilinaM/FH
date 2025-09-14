package org.example.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class WebDriverHooks {

    @Before
    public void setUp() {
        // Можно добавить дополнительную настройку перед каждым сценарием
    }

    @After
    public void tearDown() {
        System.out.println("=== CUCUMBER HOOK: Attempting to close browser ===");
        // Принудительно закрываем браузер после каждого сценария
        try {
            Selenide.closeWebDriver();
            System.out.println("=== Browser closed successfully ===");
        } catch (Exception e) {
            System.out.println("=== Failed to close with closeWebDriver, trying quit() ===");
            // Если не получилось - убиваем процесс принудительно
            try {
                if (WebDriverRunner.hasWebDriverStarted()) {
                    WebDriverRunner.getWebDriver().quit();
                    System.out.println("=== Browser quit() successful ===");
                }
            } catch (Exception ex) {
                // Последняя попытка
                System.err.println("=== Failed to close browser: " + ex.getMessage() + " ===");
            }
        }
    }

    @AfterAll
    public static void tearDownAll() {
        // Закрываем все браузеры в конце всех тестов
        Selenide.closeWebDriver();
    }
}