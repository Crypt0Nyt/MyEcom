package Streamliners.Model;

public class Variant {
    String name;
    float price;

    public Variant(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nVariant{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
