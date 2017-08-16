package org.androidluckyguys.model;

/**
 * Created by XKL0275 on 2/11/2015.
 */
public class CvpBalance {
    public String getBalance() {
        return balance;
    }

    String balance;
    String unClearBalance;
    String isFirstTime;
    String childBalanceVOs;

    public CvpBalance(String bal, String unClearBalance, String isFirstTime, String childBalanceVOs)
    {
        this.balance = bal;
        this.unClearBalance = unClearBalance;
        this.isFirstTime = isFirstTime;
        this.childBalanceVOs = childBalanceVOs;
    }
}
