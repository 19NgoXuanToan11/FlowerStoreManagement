package obj;

import java.io.Serializable;

public class Flower implements Serializable {
    private String id;
    private String name;
    private String description;
    private String importDate;
    private double unitPrice;
    private String category;

    public Flower() {
        
    }

    public Flower(String id, String name, String description, String importDate, double unitPrice, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Flower{" + "id = " + id + 
                       ", name = " + name + 
                ", description = " + description + 
                 ", importDate = " + importDate + 
                  ", unitPrice = " + unitPrice + 
                   ", category = " + category + '}';
    }
}

