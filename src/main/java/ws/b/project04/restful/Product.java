/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.b.project04.restful;

/**
 *
 * @author T.U.F GAMING
 */
public class Product {
    private String id;
    private String name;
    private int price;
    private int number;
    private int totalBuy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getTotal() {
        return price * number;
    }
    
    public void setTotal(int total) {
        this.totalBuy = total;
    }
    
}
