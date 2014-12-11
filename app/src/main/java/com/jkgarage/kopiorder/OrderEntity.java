package com.jkgarage.kopiorder;

/**
 * Created by admin on 10/12/14.
 */
public class OrderEntity {
    private String itemName;
    private int quantity;

    public OrderEntity(String item) {
        super();
        this.itemName = item;
        this.quantity = 0;
    }

    public OrderEntity (String item, int quantity) {
        this.itemName = item;
        this.quantity = quantity;
    }

    public void addOrder () {
        quantity++;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String item) {
        this.itemName = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
