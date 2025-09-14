package org.example.pages;

import java.util.Map;


public class PageFactory {
    private static final Map<String, Object> pages = Map.of(
            "LoginPage", new LoginPage()
    );

    public static Object getPage(String pageName) {
        if (!pages.containsKey(pageName)) {
            throw new IllegalArgumentException("Неизвестная страница: " + pageName);
        }
        return pages.get(pageName);
    }
}