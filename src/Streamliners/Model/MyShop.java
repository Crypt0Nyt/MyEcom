package Streamliners.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;


public class MyShop {
    Scanner sc = new Scanner(System.in);

//    List of products for the hashmap
    public HashMap<String, Product> productList = new HashMap<>();

//    Addition of products to the shop
    public void addProducts(){
        String menu = "\nSelect the type of your products" +
                "\n0: to Add Weight Based Product" +
                "\n1: to Add Variant Based Product" +
                "\n2: to Go Back " +
                "\nEnter your choice: " ;

        int option = 1;

//        Checking to go back from the menu
        while(option != 2){
            System.out.print(menu);
            option = sc.nextInt();

//            To add Weight Based Product
            if(option == 0){
                System.out.print("Enter the name of the Product: ");
                String name = sc.nextLine();

                while (name.isEmpty())
                    name = sc.nextLine();

//              Checking if the product already exist or not
                if(productList.containsKey(name)){
                    System.out.println("This product already exist in the shop");
                    continue;
                }
                System.out.print("Enter the image URL of the product: ");
                String imageURL = sc.nextLine();

                System.out.print("Please enter the minimum quantity of the product: ");
                float minQty = sc.nextFloat();

                System.out.print("Enter price per Kg: ");
                float pricePerKg = sc.nextFloat();
                Product product = new Product(name, imageURL, minQty, pricePerKg);

                productList.put(product.name, product);
                System.out.println(Colors.GREEN + "Product successfully added to the Shop!" + Colors.RESET);
            }
//            To add Varients Based Product to the Shop
            else if(option == 1){
                System.out.print("Please enter the name of the Product: ");
                String name = sc.nextLine();
                while(name.isEmpty())
                    name = sc.nextLine();

                System.out.print("Enter the image URL of the Product: ");
                String imgURL = sc.nextLine();

                System.out.print("Enter the Variants of your Product: ");
                String v = sc.nextLine();
                String[] var = v.split(" ");
                List<Variant> variantShop = new ArrayList<>();
                for(int i = 0; i<var.length-1; i+=2){
                    Variant variant = new Variant(var[i], Float.parseFloat(var[i+1]));
                    variantShop.add(variant);
                }

                //Product added in the shop list
                Product product = new Product(name, imgURL, variantShop);
                productList.put(product.name, product);
                System.out.println(Colors.GREEN + "Your product is successfully added to the Shop!" + Colors.RESET);
            }

            //            for the incorrect option
            else if(option != 2)
                System.out.println(Colors.RED + "Incorrect Option, please enter the option from 0 to 2");
        }
    }

//    To remove products from the Shop
    public void removeProduct(){
        if(productList.isEmpty()){
            System.out.println(Colors.RED + "Oops! your Shop is empty, No item to remove." + Colors.RESET);
            return;
        }
        int choice = 1;
        while(choice != 0){
            Object[] list = productList.keySet().toArray();

//            Printing current products in the shop
            for(int i = 0; i< productList.size(); i++){
                System.out.println("\n" + (i+1) + ". " + list[i]);
            }
            System.out.print("\nChoose product number you want to edit (or Enter 0 exit): ");
            choice = sc.nextInt();
            if(choice != 0){
                productList.remove(list[choice - 1]);
                System.out.println(Colors.YELLOW + "Product is Successfully removed from the Shop!" + Colors.RESET);
                return;
            }
        }

    }

//    To Edit a Product
    public void editProduct(Cart cart, MyShop shop){
        if(productList.isEmpty()){
            System.out.println(Colors.RED + "Oops! your Shop is empty, No item to remove." + Colors.RESET);
            return;
        }
        int choice = 1;
        while( choice != 0){
            Object[] list = productList.keySet().toArray();
            for(int i = 0; i< productList.size(); i++){
                System.out.print("\n" + (i + 1) +  ". " + list[i]); //Product numbers
            }
            System.out.print("\nChoose the product number you want to edit ( or Enter 0 to go back): ");
            choice = sc.nextInt();
            if(choice == 0)
                return;

            if(choice != 0){
//                to Edit Weight Based Products
                if(productList.get(list[choice - 1]).type == 0){
                    String menu = "\n0: to Edit Min Quantity" +
                            "\n1: to Edit Price" +
                            "\n2: to Exit" +
                            "\n\nEnter your Choice: ";

                    int myChoice = 1;
                    while(myChoice != 2){
                        System.out.print(menu); //to print the menu
                        myChoice = sc.nextInt();
                        if(myChoice == 0){
                            editWMinQty(productList.get(list[choice-1]), cart.cartItems.get(productList.get(list[choice-1]).name), shop);
                        }
                        else if(myChoice == 1)
                            editWPrice(productList.get(list[choice-1]), cart.cartItems.get(productList.get(list[choice-1]).name));
                        else if(myChoice != 2)
                            System.out.println(Colors.RED + "Incorrect choice, Choose from 0 to 2" + Colors.RESET);
                    }
                }
//               to Edit Variant Based Product
                else if (productList.get(list[choice-1]).type == 1){
                    editVariantsOfVBP(productList.get(list[choice - 1]), cart);
                }
            }
        }
    }
//    to edit the name of Weight Based Product
    public void editWMinQty(Product product, CartItem cartItem, MyShop shop){

        System.out.print("Please Enter new minimum Quantity: ");
        float newMinQty = sc.nextFloat();

//        to edit name in the cart if the product is there
        product.minQty = newMinQty;
        System.out.println(Colors.GREEN + "Name Changed to " + newMinQty + "Successfully!" + Colors.RESET);
    }

//    TO edit the name of Variant Based Product
    public void editVName(Product product, Cart cart){
//        to Edit the name in the cart if the product exist
        System.out.println("Enter new Name: ");
        String name = sc.nextLine();
        while(name.isEmpty())
            name = sc.nextLine();

        for(Variant variant: product.variants){
            CartItem cartItem = cart.cartItems.get(product.name + " " + variant.name);
            if(cartItem != null)
                cartItem.name = name + " " + variant.name;
        }
        product.name = name;
        System.out.println(Colors.GREEN + "Name has Changed to" + " Successfully" + Colors.RESET);
    }

//    TO Edit the Price of Weight Based Product
    public void editWPrice(Product product, CartItem cartItem){
        System.out.print("Enter new Price for your product: ");
        float newPrice = sc.nextFloat();
        if(cartItem != null)
            cartItem.unitPrice = newPrice;

        product.pricePerKg = newPrice;
        System.out.println(Colors.GREEN + "Minimum Quantity has changed to " + newPrice + "Successdully" + Colors.RESET);
    }

//    to Edit the price of Variant Based Product
    public void editVariantsOfVBP(Product product, Cart cart){
        System.out.print("Enter new Variant string for your product: ");
        String newVariant = sc.nextLine();
        while(newVariant.isEmpty())
            newVariant = sc.nextLine();

        String[] var = newVariant.split(" ");
        List<Variant> variants = new ArrayList<>();
        for(int i = 0; i< var.length; i+=2){
            Variant variant = new Variant(var[i],Float.parseFloat(var[i+1]));
            variants.add(variant);
        }
        productList.get(product.name).variants = variants;
        System.out.println(Colors.GREEN + "Variants has changed Successfully!" + Colors.RESET);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMy Shop:\n");
        Object[] arr = productList.keySet().toArray();
        for (int i = 0; i<productList.size(); i++){
            sb.append(productList.get(arr[i].toString()) + "\n"); //Adding into the string
        }

        return sb.toString();
    }










}
