package obj;

import java.io.Serializable;

public class OrderDetail implements Serializable { 
    private String flowerId;
    private int quantity;
    private double flowerCost;
    
    public OrderDetail() {
        
    }

    public OrderDetail(String flowerId, int quantity, double flowerCost) {
        this.flowerId = flowerId;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFlowerCost() {
        return flowerCost;
    }

    public void setFlowerCost(double flowerCost) {
        this.flowerCost = flowerCost;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + ", flowerId = " + flowerId + 
                                ", quantity = " + quantity + 
                              ", flowerCost = " + flowerCost + '}';
    }
}
