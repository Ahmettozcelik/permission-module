package com.example.permissionmodule.language;

import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class LanguageConfig {

    public static String getErrorMessage(String key, String language) {

        String baseName;
        Locale locale;

        if (language != null && language.equalsIgnoreCase("TR")) {
            baseName = "messages_tr";
            locale = new Locale("tr", "TR");
        } else {
            baseName = "messages_us";
            locale = Locale.US;
        }
        ResourceBundle messages = ResourceBundle.getBundle(baseName, locale);
        return messages.getString(key);
    }

}
