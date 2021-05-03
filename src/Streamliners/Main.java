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


        /*
         Null pointer exception:
         Product product1 = new Product();
         System.out.println(product1.name.toUpperCase(Locale.ROOT));
         */



        /*
        Array listed Products:
        List<Product> products = new ArrayList<>(
                Arrays.asList(
                        new Product("A","")
                        ,new Product("B","")
                        ,new Product("C","")
                ));
        System.out.println(products);
        -----------------------------------------------------------------------------------------------------------
        OUTPUT = [Product{name='A', imageURL=''}, Product{name='B', imageURL=''}, Product{name='C', imageURL=''}]
        -----------------------------------------------------------------------------------------------------------
        */



        /*
        List<Variant> variants = new ArrayList<>(
                Arrays.asList(new Variant("500g",90),new Variant("2kg",180))
        );

        VariantsBasedProduct product = new VariantsBasedProduct(
                "Kiwi","",variants);

        System.out.println(product);
        ----------------------------------------------------------------------------------------------------------------------------------------
        OUTPUT = VariantsBasedProduct{name='Kiwi', imageURL='', variants=[Variant{name='500g', price=90.0}, Variant{name='2kg', price=180.0}]}
        ----------------------------------------------------------------------------------------------------------------------------------------
        */



        /*
        Equate Hashcode
        Product a = new Product("a","");
        Product b = new Product("a","");

        System.out.println(a.equals(b));
        --------------------------------
        OUTPUT = true
        --------------------------------
        */







    }

}
