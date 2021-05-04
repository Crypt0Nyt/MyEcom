package Streamliners;

import Streamliners.Model.Product;
import Streamliners.Model.Variant;
import Streamliners.Model.VariantsBasedProduct;
import Streamliners.Model.WeightBasedProduct;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        //      Create Products
        Product apple = new WeightBasedProduct("Apple","",5,90);
        Product kiwi = new VariantsBasedProduct("Kiwi","",new ArrayList<>(
                Arrays.asList(
                        new Variant("500g",80),
                        new Variant("1kg",160))
        ));

        //      Add them in a List
        List<Product> products = new ArrayList<>(
                Arrays.asList(apple,kiwi));

        //      Printing the list
        System.out.println(products);



    }

}
