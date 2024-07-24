package ru.topacademy.javaqateamproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    // Проверяет, что нельзя добавить сумму, превышающую максимальный баланс
    @Test
    public void shouldNotAddExceedingMaxBalance() {
        SavingAccount account = new SavingAccount(9000, 1000, 10000, 5);

        boolean result = account.add(2000);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldNotAddExceedingMaxBalanceBalanceUnchanged() {
        SavingAccount account = new SavingAccount(9000, 1000, 10000, 5);

        account.add(2000);

        Assertions.assertEquals(9000, account.getBalance());
    }

    // Проверяет, что нельзя оплатить сумму, превышающую баланс
    @Test
    public void shouldNotPayExceedingBalance() {
        SavingAccount account = new SavingAccount(1000, 100, 10000, 5);

        boolean result = account.pay(1500);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldNotPayExceedingBalanceBalanceUnchanged() {
        SavingAccount account = new SavingAccount(1000, 100, 10000, 5);

        account.pay(1500);

        Assertions.assertEquals(1000, account.getBalance());
    }

    // Проверяет, что нельзя создать аккаунт с отрицательной процентной ставкой
    @Test
    public void shouldNotCreateWithNegativeRate() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 1000, 10000, -5);
        });

        Assertions.assertEquals("Накопительная ставка не может быть отрицательной, а у вас: -5", exception.getMessage());
    }

    // Проверяет, что нельзя создать аккаунт с минимальным балансом, превышающим максимальный баланс
    @Test
    public void shouldNotCreateWithMinGreaterThanMax() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 10000, 1000, 5);
        });

        Assertions.assertEquals("Минимальный баланс не может быть больше максимального, а у вас: 10000 > 1000", exception.getMessage());
    }

    // Проверяет, что нельзя создать аккаунт с начальным балансом, меньшим минимального баланса
    @Test
    public void shouldNotCreateWithInitialLessThanMin() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(500, 1000, 10000, 5);
        });

        Assertions.assertEquals("Начальный баланс не может быть меньше минимального баланса, а у вас: 500", exception.getMessage());
    }

    // Проверяет, что нельзя создать аккаунт с начальным балансом, превышающим максимальный баланс
    @Test
    public void shouldNotCreateWithInitialGreaterThanMax() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(15000, 1000, 10000, 5);
        });

        Assertions.assertEquals("Начальный баланс не может быть больше максимального баланса, а у вас: 15000", exception.getMessage());
    }

     // Проверяет,что баланс не изменяется,если метод pay возвращает false.
    @Test
    public void testBalanceUnchangedWhenPayFails() {
        int balance = 2000;
        int minimumBalance = 1000;
        int amountToPay = 1500;

        SavingAccount account = new SavingAccount(balance, minimumBalance, 10000, 5);

        account.pay(amountToPay);

        Assertions.assertEquals(balance, account.getBalance());
    }

    // Проверяет,что метод yearChange возвращает 0, когда баланс счета отрицательный.
    @Test
    public void testYearChangeWithNegativeBalance() {
        int balance = - 2000;
        int rate = 15;

        SavingAccount account = new SavingAccount(balance,1000,10000,15);

        Assertions.assertEquals(0, account.yearChange());
    }
}
