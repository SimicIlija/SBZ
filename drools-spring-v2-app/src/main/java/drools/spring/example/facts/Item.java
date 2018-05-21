package drools.spring.example.facts;

import java.io.Serializable;

public class Item implements Serializable {

    public enum Category {
        NA, LOW_RANGE, MID_RANGE, HIGH_RANGE, SIMA_KAT,
        SPECIAL_MIDHIGH_RANGE //used in chapter 4
    }

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double cost;
    private Double salePrice;
    private Category category;

    public Item() {
    }

    public Item(String name, Double cost, Double salePrice) {
        this(null, name, cost, salePrice);
    }
    
    public Item(Long id, String name, Double cost, Double salePrice) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.salePrice = salePrice;
        this.category = Category.NA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!id.equals(item.id)) return false;
        if (!name.equals(item.name)) return false;
        if (!cost.equals(item.cost)) return false;
        if (!salePrice.equals(item.salePrice)) return false;
        return category == item.category;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + cost.hashCode();
        result = 31 * result + salePrice.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", cost=" + cost
                + ", salePrice=" + salePrice + ", category=" + category + '}';
    }

}
