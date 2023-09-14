package ru.netology.bdd.data;


import lombok.Value;

public class DataHelper {

    public void DataHelper() {
    }

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
