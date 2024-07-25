package ru.topacademy.javaqateamproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
    @Test
    public void testConstructorNegativeRateThrowsException() {
        new CreditAccount(100, 1000, -5);
    }

    @Test
    public void testPayPositiveAmountReducesBalance() {
        CreditAccount account = new CreditAccount(100, 1000, 5);
        account.pay(50);
        Assertions.assertEquals(50, account.getBalance());

    }

    @Test
    public void testPayNegativeAmountReturnsFalse() {
        CreditAccount account = new CreditAccount(100, 1000, 5);
        account.pay(-50);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void testPayAmountGreaterThanBalanceReturnsFalse() {
        CreditAccount account = new CreditAccount(100, 1000, 5);
        account.pay(150);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void testPayAmountEqualsCreditLimitReturnsTrue() {
        CreditAccount account = new CreditAccount(100, 1000, 5);
        account.pay(1100);
        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    public void testPayAmountGreaterThanCreditLimitReturnsFalse() {
        CreditAccount account = new CreditAccount(100, 1000, 5);
        account.pay(1200);
        Assertions.assertEquals(100, account.getBalance());
    }
    @Test
    public void testCreditAccountCreationWithZeroRate() {
        Assertions.assertDoesNotThrow(() -> new CreditAccount(1000, 5000, 0));
    }
     @Test
    public void testPayExceedingCreditLimitBalanceUnchanged() {
        CreditAccount account = new CreditAccount(1000, 5000, 10);
        account.pay(7000);
        int actual = account.getBalance();
        int expected = 1000;

        Assertions.assertEquals(expected, actual);
    }
}
