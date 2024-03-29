package com.http.local;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {
    public static void main(String[] args) {
        Locale locale = Locale.of("ru", "RU");
        System.out.println(Locale.getDefault());

        ResourceBundle translations = ResourceBundle.getBundle("translations", locale);
        System.out.println(translations.getString("page.login.password"));
    }
}
