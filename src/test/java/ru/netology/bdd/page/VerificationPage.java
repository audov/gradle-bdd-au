package ru.netology.bdd.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {


    private final SelenideElement codeField = $("[data-test-id='code'] .input__control");
    private final SelenideElement buttonVer = $("[data-test-id='action-verify']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashBoardPage validVerify(DataHelper.VerCode verCode) {
        codeField.setValue(verCode.getVerCode());
        buttonVer.click();
        return new DashBoardPage();
    }
}