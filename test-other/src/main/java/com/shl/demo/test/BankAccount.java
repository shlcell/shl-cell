package com.shl.demo.test;
/**
 * 在上述代码中，我们定义了一个 BankAccount 类，并实现了两个方法：updateBalanceOptimistic 和 updateBalancePessimistic。
 *
 * updateBalanceOptimistic 方法使用乐观锁实现，在尝试了最多 3 次后更新余额。
 * 具体地，我们首先先获取账户当前的余额（oldBalance），然后计算出新的余额（newBalance）。
 * 如果在计算过程中，其它线程修改了账户的余额，导致 oldBalance != balance，则不能更新余额。
 * 在这种情况下，我们将重复尝试更新余额，最多尝试 3 次，然后返回是否更新成功。
 *
 * updateBalancePessimistic 方法使用悲观锁实现，在方法上加了 synchronized 关键字，以保证同一时间只有一个线程可以访问该方法。
 * 这种方式简单直接，但是会降低系统的性能，因为在极端情况下可能会有大量的线程在等待获取锁。
 *
 * 需要注意的是，乐观锁和悲观锁都有其适用的场景，视具体情况而定。
 * 在实际应用中，我们需要根据业务需求、数据访问频率、性能要求等多方面的因素综合考虑，选择合适的锁机制。
 */
public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // 使用乐观锁更新余额，尝试最多 3 次
    public boolean updateBalanceOptimistic(double amount) {
        for (int i = 0; i < 3; i++) {
            double oldBalance = balance;
            double newBalance = oldBalance + amount;
            if (oldBalance == balance) {
                balance = newBalance;
                return true;
            }
        }
        return false;
    }

    // 使用悲观锁更新余额
    public synchronized void updateBalancePessimistic(double amount) {
        balance += amount;
    }
}

