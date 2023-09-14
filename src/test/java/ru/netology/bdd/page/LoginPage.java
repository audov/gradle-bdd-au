package ru.netology.bdd.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static final SelenideElement loginField = $("[data-test-id='login'] input");
    private static final SelenideElement passwordField = $("[data-test-id='password'] input");
    private static final SelenideElement button = $("[data-test-id='action-login']");

    public static VerificationPage validLogin(DataHelper.UserInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }
}