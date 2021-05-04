package Streamliners;

import Streamliners.Model.*;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



public class Main {
    public static void main(String[] args) {

        Product apple = new Product("Apple", "", 0.5f, 100),
                orange = new Product("Orange", "", 0.5f, 80),
                kiwi = new Product("Kiwi", "", new ArrayList<>(
                Arrays.asList(
                        new Variant("500g", 96),
                        new Variant("1Kg", 180)
                ))), surfExcel = new Product("Surf Excel", " ", new ArrayList<>(
                Collections.singletonList(new Variant("1kg", 180))));

        Cart cart = new Cart();
        cart.add(orange, 2.5f);
        cart.add(kiwi, kiwi.variants.get(1));
        cart.add(kiwi, kiwi.variants.get(1));
        cart.add(kiwi, kiwi.variants.get(1));
        cart.add(surfExcel , surfExcel.variants.get(0));
        cart.add(surfExcel , surfExcel.variants.get(0));

        System.out.println(cart);

        cart.remove(orange);
        System.out.println();
        System.out.println(cart);

        cart.decrement(surfExcel , surfExcel.variants.get(0));
        System.out.println();
        System.out.println(cart);

        cart.decrement(surfExcel , surfExcel.variants.get(0));
        System.out.println();
        System.out.println(cart);

    }
}


