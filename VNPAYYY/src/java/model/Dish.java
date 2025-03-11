/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author HAU
 */
public class Dish {
    private int dish_id;
    private String name;
    private String infor;
    private int price;
    private String image;
    private String comment;
    private String typeOfDish;

    public Dish() {
    }

    public Dish(int dish_id, String name, String infor, int price, String image, String comment, String typeOfDish) {
        this.dish_id = dish_id;
        this.name = name;
        this.infor = infor;
        this.price = price;
        this.image = image;
        this.comment = comment;
        this.typeOfDish = typeOfDish;
    }

    public Dish(String name, String infor, int price, String image, String comment, String typeOfDish) {
        this.name = name;
        this.infor = infor;
        this.price = price;
        this.image = image;
        this.comment = comment;
        this.typeOfDish = typeOfDish;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTypeOfDish() {
        return typeOfDish;
    }

    public void setTypeOfDish(String typeOfDish) {
        this.typeOfDish = typeOfDish;
    }

    @Override
    public String toString() {
        return "Dish{" + "dish_id=" + dish_id + ", name=" + name + ", infor=" + infor + ", price=" + price + ", image=" + image + ", comment=" + comment + ", typeOfDish=" + typeOfDish + '}';
    }


    
}
