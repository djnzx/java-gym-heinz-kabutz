package io.jgym.warmups.day27;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Invalid amount");
        updateBalance(balance, amount);
    }

    public synchronized void withdraw(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Invalid amount");
        if (balance - amount < 0) throw new IllegalArgumentException("Overdrawn");
        updateBalance(balance, -amount);
    }

    private void updateBalance(double balance, double amount) {
        assert Thread.holdsLock(this);
        balance += amount;
    }
}
