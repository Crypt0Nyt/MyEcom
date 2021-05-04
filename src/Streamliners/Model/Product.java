package Streamliners.Model;

import java.util.List;
import java.util.Objects;

public class Product {
//  Common
    public String name, imageURL;
    public int type;

//    Weight Based
    float minQty, pricePerKg;

//    Variants Based
    public List<Variant> variants;

    public Product(String name, String imageURL, float minQty, float pricePerKg) {
        type = ProductType.TYPE_WB;
        this.name = name;
        this.imageURL = imageURL;
        this.minQty = minQty;
        this.pricePerKg = pricePerKg;
    }

    public Product(String name, String imageURL, List<Variant> variants) {
        type = ProductType.TYPE_VB;
        this.name = name;
        this.imageURL = imageURL;
        this.variants = variants;
    }


    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();

      if(type == ProductType.TYPE_WB)
          builder.append("WB { ");
      else
          builder.append("VB { ");

      builder.append("name = ").append(name);

      if(type == ProductType.TYPE_WB) {
          builder.append(", minQty = ").append(minQty);
          builder.append(", pricePerKg = ").append(pricePerKg);
      }
      else {
          builder.append("VB { ");
          builder.append("Variants = ").append(variants);
      }
      builder.append(" } ");

      return builder.toString();

    }
}
