package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.bdd.page.DashBoardPage.getRandomValidAmount;

public class TransferPage {

    private final SelenideElement amountTransfer = $("[data-test-id='amount'] .input__control");
    private final SelenideElement fromTransfer = $("[data-test-id='from'] .input__control");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement buttonTransfer = $("[data-test-id='from'] .input__control");
    private final SelenideElement transferHead = $(byText("Пополнить"));
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .input__content");

    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }

    public DashBoardPage validTransfer(int amount, DataHelper.CardInfo cardInfo) {
        makeTransfer(amount, cardInfo);
        return new DashBoardPage();
    }

    public void makeTransfer(int amount, DataHelper.CardInfo cardInfo) {
        amountTransfer.setValue(String.valueOf(amount));
        fromTransfer.setValue(cardInfo.getCardNum());
        transferButton.click();
    }

    public void findErrorNotification(String text) {
        errorNotification.shouldHave(exactText(text), Duration.ofSeconds(15)).shouldBe(visible);
    }
}