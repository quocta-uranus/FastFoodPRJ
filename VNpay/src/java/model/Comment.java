/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Comment {

    private int comment_id;
    private int dish_id;
    private int customer_id;
    private String comment_content;
    private Date comment_date;
    private String username;
    private int order_id;
    public Comment() {
    }

    public Comment(int comment_id, int dish_id, int customer_id, String comment_content, Date comment_date, 
            String username, int order_id) {
        this.comment_id = comment_id;
        this.dish_id = dish_id;
        this.customer_id = customer_id;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
        this.username = username;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }



    public int getComment_id() {
        return comment_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    @Override
    public String toString() {
        return "Comment{" + "comment_id=" + comment_id + ", dish_id=" + dish_id + ", customer_id=" + customer_id + ", comment_content=" + comment_content + ", comment_date=" + comment_date + ", username=" + username + '}';
    }

   

}
