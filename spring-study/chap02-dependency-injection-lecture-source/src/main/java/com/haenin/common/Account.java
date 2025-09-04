package com.haenin.common;

public interface Account {
    /* 설명. 1. 잔액 조회 */
    String getBalance();

    /* 설명. 2. 입급 */
    String deposit(int money);

    /* 설명. 3. 출금 */
    String withdraw(int money);
}
