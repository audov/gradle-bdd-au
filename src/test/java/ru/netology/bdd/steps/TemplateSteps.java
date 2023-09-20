package ru.netology.bdd.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.netology.bdd.data.DataHelper;
import ru.netology.bdd.page.DashBoardPage;
import ru.netology.bdd.page.LoginPage;
import ru.netology.bdd.page.TransferPage;
import ru.netology.bdd.page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateSteps {
    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static DashBoardPage dashBoardPage;
    private static TransferPage transferPage;


    @Given("login page is open at {string} in firefox browser")
    public void authPageRun(String url) {
        Configuration.browser = "firefox";
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @When("the user login with name {string} and password {string}")
    public void loginWithNamePassword(String login, String password) {
        verificationPage = loginPage.validLogin(new DataHelper.UserInfo(login, password));
    }

    @And("inputs verification code {string}")
    public void verificationCodeInput(String code) {
        dashBoardPage = verificationPage.validVerify(new DataHelper.VerCode(code));
    }

    @Then("verification is complete and dashboard is open")
    public void dashBoardPageIsOpen() {
        dashBoardPage = dashBoardPage;
    }

    @When("the user chooses to replenish the first card")
    public void chooseCardToReplenish() {
        dashBoardPage.selectCardToTransfer(DataHelper.getFstCardInfo());
    }

    @And("inputs {string} rub to transfer from first card to second")
    public void transferMoney(String amount) {
        dashBoardPage = transferPage.validTransfer(Integer.parseInt(amount), DataHelper.getSndCardInfo());
    }

    @Then("the balance of the first card must be {string} rub")
    public void getFstCardBalance(String amount) {
        var actualBalanceFstCard = dashBoardPage.getCardBalance(DataHelper.getFstCardInfo());
        var expectBalanceFstCard = amount;
        assertEquals(expectBalanceFstCard, actualBalanceFstCard);
    }
}
