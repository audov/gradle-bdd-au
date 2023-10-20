package ru.netology.bdd.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

import static ru.netology.bdd.page.DashBoardPage.getCardBalance;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("ru"));

    public static UserInfo getUserInfo() {
        return new UserInfo("vasya", "qwerty123");
    }

    public static VerCode getVerCode() {
        return new VerCode("12345");
    }

    public static CardInfo getFstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getSndCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int getRandomValidAmount(CardInfo cardInfo) {
        return faker.number().numberBetween(1, getCardBalance(cardInfo));
    }

    public static int getRandomInvalidPosAmount(int getCardBalance) {
        return faker.number().numberBetween(getCardBalance, getCardBalance + 1);
    }

    public void DataHelper() {
    }

    @Value
    public static class UserInfo {
        String login;
        String password;
    }

    @Value
    public static class VerCode {
        String verCode;
    }

    @Value
    public static class CardInfo {
        String cardNum;
        String id;
    }
}
