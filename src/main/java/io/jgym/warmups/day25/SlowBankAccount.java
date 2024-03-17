package io.jgym.warmups.day25;

import java.util.concurrent.locks.*;

final class SlowBankAccount extends BankAccount {
    public SlowBankAccount(double balance) {
        super(balance);
    }

    protected void sleepAWhileForTesting() {
        LockSupport.parkNanos(50_000_000);
    }
}
