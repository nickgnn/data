package my.business.data.entities;

import org.springframework.stereotype.Component;

public class Deal {
    private String number;
    private String date;
    private String time;
    private String price;
    private String quantity;
    private String direction;

    public Deal() {
    }

    public Deal(String number, String date, String time, String price, String quantity, String direction) {
        this.number = number;
        this.date = date;
        this.time = time;
        this.price = price;
        this.quantity = quantity;
        this.direction = direction;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
