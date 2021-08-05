package com.xl.domain;

public class AuctionCustomerException  extends Exception{
    private String message;
    public String getMessage() {
        return message;
    }

    public AuctionCustomerException(String message) {
        this.message = message;
    }

    public AuctionCustomerException() {

    }

    public void setMessage(String message) {

        this.message = message;
    }

}
