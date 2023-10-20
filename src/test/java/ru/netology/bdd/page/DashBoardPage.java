package ru.netology.bdd.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashBoardPage {

    private static final ElementsCollection cards = $$(".list__item div");
    private static final SelenideElement heading = $("[data-test-id=dashboard]");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public void DashboardPage() {
        heading.shouldBe(visible);
    }

    public static int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(attribute("data-test-id", cardInfo.getId())).getText();
        return extractBalance(text);
    }

    public static int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getId())).$("button").click();
        return new TransferPage();
    }
}