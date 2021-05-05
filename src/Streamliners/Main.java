package Streamliners;

import Streamliners.Model.*;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CartFunctions cartFunctions = new CartFunctions();
        MyShop shop = new MyShop();
        Cart cart = new Cart();

        String optionsMenu = "\nSelect your choice from the given options: " +
                "\n\n0: Exit" +
                "\n1: to Add a product to the Shop" +
                "\n2: to Edit a product in the Shop" +
                "\n3: to Delete a product from the Shop" +
                "\n4: to See all the products in the Shop" +
                "\n\n5: to Add product to the Cart" +
                "\n6: to Edit product in the Cart" +
                "\n7: to Remove product from the Cart" +
                "\n8: tp Get Cart details" +
                "\n9: to Place Order" +
                "\n\n Now Enter your choice: ";

        while (true){
            System.out.print(optionsMenu);
            int n = sc.nextInt();

            switch (n){
                case 0:
                    System.out.println("Thank You!");
                    return;

                case 1:
                    shop.addProducts();
                    break;

                case 2:
                    shop.editProduct(cart,shop);
                    break;

                case 3:
                    shop.removeProduct();
                    break;

                case 4:
                    if (shop.productList.isEmpty()){
                        System.out.println(Colors.YELLOW + "\nShop is currently empty!\n" + Colors.RESET);
                        break;
                    }
                    System.out.println(shop);
                    break;

                case 5:
                    cartFunctions.addToCart(cart,shop);
                    break;

                case 6:
                    cartFunctions.editCart(cart,shop);
                    break;

                case 7:
                    cartFunctions.removeFromCart(cart,shop);
                    break;

                case 8:
                    if (cart.cartItems.isEmpty()){
                        System.out.println(Colors.YELLOW + "\nYour cart is empty!\n" + Colors.RESET);
                        break;
                    }
                    System.out.println(cart);
                    break;

            }
        }

    }
}


