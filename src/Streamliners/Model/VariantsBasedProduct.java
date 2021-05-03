package Streamliners.Model;

import java.util.List;

public class VariantsBasedProduct extends Product{
        List<Variant> variants;

        public VariantsBasedProduct(String name, String imageURL, List<Variant> variants) {
                super(name,imageURL);
                this.variants = variants;
        }

        @Override
        public String toString() {
                return "\nVariantsBasedProduct{" +
                        "name='" + name + '\'' +
                        ", imageURL='" + imageURL + '\'' +
                        ",\nvariants= " + variants +
                        '}'+"\n";
        }
}
