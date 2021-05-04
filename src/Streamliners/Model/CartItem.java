package Streamliners.Model;

public class CartItem {

    String name;
    private float pricePerKg;
    float unitPrice, qty;

    public CartItem(String name, float unitPrice, float qty) {

        this.name = name;
        this.pricePerKg = unitPrice;
        this.qty = qty;
    }

    float cost(){

        return pricePerKg * qty;
    }

    @Override
    public String toString() {
        return "\n" + name +
                String.format(", (%f X %f = %f" ,pricePerKg, qty, cost()) +
                ")";
    }
}
