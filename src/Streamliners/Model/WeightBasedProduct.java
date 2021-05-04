
package Streamliners.Model;

public class WeightBasedProduct extends Product{
    float minQty, pricePerKg;

    public WeightBasedProduct(String name, String imageURL, float minQty, float pricePerKg) {
        super(name, imageURL);
        this.minQty = minQty;
        this.pricePerKg = pricePerKg;
    }

    @Override
    public String toString() {
        return "\nWeightBasedProduct{" +
                "name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", minQty=" + minQty +
                ", pricePerKg=" + pricePerKg +
                '}';
    }
}


