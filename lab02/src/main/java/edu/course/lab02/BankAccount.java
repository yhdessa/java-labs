package edu.course.lab02;

public final class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException();
        } balance = initialBalance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } if (amount > balance) {
            throw new IllegalArgumentException();
        } balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}