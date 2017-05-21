package com.bsg.api.vo;


import javax.validation.constraints.NotNull;

/**
 * Created by zhang on 2017/5/18.
 */
public class BookPostVo {
    @NotNull
    private String number;
    @NotNull
    private String price;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BookPostVo(String number, String price) {
        this.number = number;
        this.price = price;
    }

    public BookPostVo() {
    }

    @Override
    public String toString() {
        return "BookPostVo{" +
                "number='" + number + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
