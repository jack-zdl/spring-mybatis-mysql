package com.bsg.api.dto;

/**
 * Created by zhang on 2017/4/24.
 */
public class BookDto extends BaseDto {
    private String id;
    private String number;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public BookDto() {
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
