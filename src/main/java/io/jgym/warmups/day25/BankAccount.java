package io.jgym.warmups.day25;

public sealed class BankAccount permits SlowBankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        deposit(-amount);
    }

    public synchronized double getBalance() {
        return balance;
    }

    public void transferTo(BankAccount recipient, double amount) {
        synchronized (this) {
            sleepAWhileForTesting();
            synchronized (recipient) {
                withdraw(amount);
                recipient.deposit(amount);
            }
        }
    }

    protected void sleepAWhileForTesting() {
    }
}
