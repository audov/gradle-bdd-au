#language:en


Feature: Money transfer between bank accounts

  Scenario: successful authorization, verification and money transfer
    Given login page is open at "http://localhost:9999" in firefox browser
    When the user login with name "vasya" and password "qwerty123"
    And inputs verification code "12345"
    Then verification is complete and dashboard is open
    When the user chooses to replenish the first card
    And inputs "5000" rub to transfer from first card to second
    Then the balance of the first card must be "15000" rub


