package org.example.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import org.example.pages.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class LoginSteps {
    
    private final LoginPage loginPage = new LoginPage();
    
    @Когда("открываем страницу логина")
    public void открываемСтраницуЛогина() {
        open(LoginPage.LOGIN_URL);
    }

    @Когда("закрываем страницу логина")
    public void закрываемСтраницуЛогина() {
        closeWindow();
    }
    
    @И("вводим телефон {string}")
    public void вводимТелефон(String phone) {
        loginPage.phoneInput.val(phone);
    }
    
    @И("вводим пароль {string}")
    public void вводимПароль(String password) {
        loginPage.passwordInput.val(password);
    }
    
    @И("нажимаем кнопку входа")
    public void нажимаемКнопкуВхода() {
        loginPage.loginButton.click();
        sleep(3000);
        System.out.println("=== URL после клика: " + webdriver().driver().url());
        System.out.println("=== Title страницы: " + title());
    }
} 