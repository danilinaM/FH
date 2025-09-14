package org.example.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.То;
import org.example.pages.PageFactory;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class CommonSteps {

    @То("на странице {string} отображается элемент {string}")
    public void elementOnPageIsVisible(String page, String element) throws NoSuchFieldException, IllegalAccessException {
        Object pageObject = PageFactory.getPage(page);
        SelenideElement selenideElement = (SelenideElement) pageObject.getClass().getDeclaredField(element).get(pageObject);
        selenideElement.shouldBe(Condition.visible);
    }

    @То("на странице {string} не отображается элемент {string}")
    public void elementOnPageIsNotVisible(String page, String element) throws NoSuchFieldException, IllegalAccessException {
        Object pageObject = PageFactory.getPage(page);
        SelenideElement selenideElement = (SelenideElement) pageObject.getClass().getDeclaredField(element).get(pageObject);
        selenideElement.shouldNotBe(Condition.visible);
    }

    @То("на странице отображается элемент с текстом {string}")
    public void elementOnPageHasText(String text) {
        $x("//*[contains(text(), '" + text + "')]").shouldBe(Condition.visible);
    }

    @То("на странице не отображается элемент с текстом {string}")
    public void elementOnPageNotHasText(String text) {
        $x("//*[contains(text(), '" + text + "')]").shouldNotBe(Condition.visible);
    }
}
