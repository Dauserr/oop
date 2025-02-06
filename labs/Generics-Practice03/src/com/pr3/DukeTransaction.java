package com.pr3;

public class DukeTransaction {
    private String productId;
    private String transactionType;
    private int count;

    public DukeTransaction(String productId, String transactionType, int count) {
        this.productId = productId;
        this.transactionType = transactionType;
        this.count = count;
    }
    public String getProductId() {
        return productId;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public int getCount() {
        return count;
    }
}
