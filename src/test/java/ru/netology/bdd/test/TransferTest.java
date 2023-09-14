package ru.netology.bdd.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.bdd.page.DashBoardPage;
import ru.netology.bdd.page.LoginPage;
import ru.netology.bdd.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.bdd.data.DataHelper.*;
import static ru.netology.bdd.page.DashBoardPage.getRandomInvalidPosAmount;
import static ru.netology.bdd.page.DashBoardPage.getRandomValidAmount;
import static ru.netology.bdd.page.LoginPage.validLogin;


public class TransferTest {
    DashBoardPage dashBoardPage;

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "firefox";
    }

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var userInfo = getUserInfo();
        var verificationPage = validLogin(userInfo);
        var verificationCode = getVerCode();
        dashBoardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Should successfully transfer from first to second")
    public void shouldSuccessfulTransferFromFirstToSecond() {
        TransferPage transferPage = new TransferPage();
        var fstCardInfo = getFstCardInfo();
        var sndCardInfo = getSndCardInfo();
        var fstCardBalance = dashBoardPage.getCardBalance(fstCardInfo);
        var sndCardBalance = dashBoardPage.getCardBalance(sndCardInfo);
        var amountToTransfer = getRandomValidAmount(fstCardBalance);
        dashBoardPage.selectCardToTransfer(sndCardInfo);
        var expectBalanceFstCard = fstCardBalance - amountToTransfer;
        var expectBalanceSndCard = sndCardBalance + amountToTransfer;
        transferPage.validTransfer(amountToTransfer, fstCardInfo);
        var actualBalanceFstCard = dashBoardPage.getCardBalance(fstCardInfo);
        var actualBalanceSndCard = dashBoardPage.getCardBalance(sndCardInfo);

        assertEquals(expectBalanceFstCard, actualBalanceFstCard);
        assertEquals(expectBalanceSndCard, actualBalanceSndCard);
    }

    @Test
    @DisplayName("Should successfully transfer from second to first")
    public void shouldSuccessfulTransferFromSecondToFirst() {
        TransferPage transferPage = new TransferPage();
        var fstCardInfo = getFstCardInfo();
        var sndCardInfo = getSndCardInfo();
        var fstCardBalance = dashBoardPage.getCardBalance(fstCardInfo);
        var sndCardBalance = dashBoardPage.getCardBalance(sndCardInfo);
        var amountToTransfer = getRandomValidAmount(sndCardBalance);
        dashBoardPage.selectCardToTransfer(fstCardInfo);
        var expectBalanceFstCard = fstCardBalance + amountToTransfer;
        var expectBalanceSndCard = sndCardBalance - amountToTransfer;
        transferPage.validTransfer(amountToTransfer, sndCardInfo);
        var actualBalanceFstCard = dashBoardPage.getCardBalance(fstCardInfo);
        var actualBalanceSndCard = dashBoardPage.getCardBalance(sndCardInfo);

        assertEquals(expectBalanceFstCard, actualBalanceFstCard);
        assertEquals(expectBalanceSndCard, actualBalanceSndCard);
    }

    @Test
    @DisplayName("Should not transfer from second to first more than actual balance")
    public void shouldNotTransferFromSecondToFirstAboveActBalance() {
        TransferPage transferPage = new TransferPage();
        var fstCardInfo = getFstCardInfo();
        var sndCardInfo = getSndCardInfo();
        var fstCardBalance = dashBoardPage.getCardBalance(fstCardInfo);
        var sndCardBalance = dashBoardPage.getCardBalance(sndCardInfo);
        var amountToTransfer = getRandomInvalidPosAmount(sndCardBalance);
        dashBoardPage.selectCardToTransfer(fstCardInfo);
        transferPage.validTransfer(amountToTransfer, sndCardInfo);
        transferPage.findErrorNotification("Ошибка!");

        var actualBalanceFstCard = dashBoardPage.getCardBalance(fstCardInfo);
        var actualBalanceSndCard = dashBoardPage.getCardBalance(sndCardInfo);

        assertEquals(fstCardBalance, actualBalanceFstCard);
        assertEquals(sndCardBalance, actualBalanceSndCard);
    }
}