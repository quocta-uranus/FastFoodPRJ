/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Dish;

/**
 *
 * @author datka
 */
public class Cart {

    private List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public void addItems(CartItem t)
    {
        if(getItemById(t.getDish().getDish_id()) != null)
        {
            CartItem m =  getItemById(t.getDish().getDish_id());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        } else 
        {
            cartItems.add(t);
        }
    }
    public void addItem(Dish dish, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getDish().getDish_id() == (dish.getDish_id())) {
                // Sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;

            }
        }        // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới
        CartItem cartItem = new CartItem(dish, quantity);
        cartItems.add(cartItem);
    }

    public List<CartItem> removeItem(int index) {
        cartItems.remove(index);
        return cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public CartItem getItemById(int id) {
        for (CartItem i : cartItems) {
            if (i.getDish().getDish_id() == id) {
                return i;
            }
        }
        return null;
    }

    public int getTotal() {
        int total = 0;
        for (CartItem c : cartItems) {
            total += c.getQuantity() * c.getDish().getPrice();
        }
        return total;
    }

    public void updateQuantity(int index, int newQuantity) {

        if (index >= 0 && index < cartItems.size() && newQuantity >= 1) {
            CartItem cartItem = cartItems.get(index);
            cartItem.setQuantity(newQuantity);
        }
    }

    public void decrementItem(Dish dish, int i) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getDish().getDish_id() == (dish.getDish_id())) {
                // Sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng
                cartItem.setQuantity(cartItem.getQuantity() - i);
                return;

            }
        }        // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới
        CartItem cartItem = new CartItem(dish, i);
        cartItems.add(cartItem);
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    public String encodeCartItem(CartItem cartItem) {
        Gson gson = new Gson();
        String json = gson.toJson(cartItem);
        byte[] encodedBytes = Base64.getEncoder().encode(json.getBytes());
        return new String(encodedBytes);
    }

// Giải mã chuỗi Base64 thành đối tượng CartItem
    public CartItem decodeCartItem(String encodedCartItemJSON) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCartItemJSON.getBytes());
        String json = new String(decodedBytes);
        Gson gson = new Gson();
        return gson.fromJson(json, CartItem.class);
    }

    private Dish getProductInList(List<Dish> list, int id) {
        for (Dish d : list) {
            if (d.getDish_id() == id) {
                return d;
            }
        }
        return null;
    }

    public Cart(String txt, List<Dish> list) {
        cartItems = new ArrayList<>();
        try
        {
            if(txt != null  & txt.length() != 0)
        {
            String[] s = txt.split(",");
            for(String i: s)
            {
                String[] n = i.split(":");
                int id =   Integer.parseInt(n[0]);
                int quantity = Integer.parseInt(n[1]);
                Dish dish = getProductInList(list,id);
                CartItem items = new CartItem(dish,quantity);
                addItems(items);
            }
        }
        } catch(Exception ex)
        {
            
        }
        
    }
}