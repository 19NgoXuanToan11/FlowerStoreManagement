package obj;

import java.io.Serializable;

import java.util.ArrayList;

public class OrderHeader implements Serializable {
    private int orderID; 
    private String orderDate;
    private String customerName;
    private int quantity;
    private double total;
    private ArrayList<OrderDetail> listOfDetail;
    
    public OrderHeader() {
        
    }

    public OrderHeader(int orderID, String orderDate, String customerName, int quantity, double total, ArrayList<OrderDetail> listOfDetail) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.quantity = quantity;
        this.total = total;
        this.listOfDetail = listOfDetail;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<OrderDetail> getListOfDetail() {
        return listOfDetail;
    }

    public void setoList(ArrayList<OrderDetail> list) {
        this.listOfDetail = list;
    }

    @Override
    public String toString() {
        return "OrderHeader{" + 
                 "orderID = " + orderID + 
             ", orderDate = " + orderDate + 
          ", customerName = " + customerName + 
              ", quantity = " + quantity + 
                 ", total = " + total + 
          ", listOfDetail = " + listOfDetail + '}';
    }
}
