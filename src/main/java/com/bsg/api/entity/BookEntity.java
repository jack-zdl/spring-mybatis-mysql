package com.bsg.api.entity;

import java.io.Serializable;

/**
 * Created by zhang on 2017/3/28.
 */
public class BookEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * @description 书籍编码
     */
    private String id;

    /**
     * @description 书籍数量
     */
    private String number;

    /**
     * @description 书籍价格
     */
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

    public BookEntity(String id, String number, String price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }

    public BookEntity() {
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
