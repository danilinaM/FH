package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.example.annotations.Page;

import static com.codeborne.selenide.Selenide.$;

@Page(url = "/login")
public class LoginPage extends BasePage {

    public static final String LOGIN_URL = "https://lk.fitnesshouse.ru/login";

    public SelenideElement phoneInput = $("input[type='tel']");
    public SelenideElement passwordInput = $("input[type='password']");
    public SelenideElement loginButton = $("button[class*='button-ultramarine'] span[class*='block']");
   // public SelenideElement checkBoxRememberMe = $("label input[type='checkbox']");
} 