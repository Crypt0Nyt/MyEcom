package Streamliners.Model;

import java.util.Scanner;

public class CartFunctions {
    Scanner scanner = new Scanner(System.in);

    //    Addition of products to the cart
    public void addToCart(Cart cart, MyShop shop) {
        int choice = 1;

//        Return if the shop is empty
        if (shop.productList.isEmpty()) {
            System.out.println(Colors.YELLOW + "Your Shop is empty" + Colors.RESET);
            return;
        }
        while (true) {
            Object[] list = shop.productList.keySet().toArray();

//            To print the current item in the Cart
            for (int i = 0; i < list.length; i++) {
                System.out.println("\n" + (i + 1) + ". " + list[i]);
            }
            System.out.print("\nEnter the Product number of the item to Add (or Enter 0 to Go back): ");
            choice = scanner.nextInt();
            if (choice == 0)
                break;
            choice = choice - 1;

//            to Edit Weight Based Product in cart
            if (shop.productList.get(list[choice]).type == 0) {  //type 0 is for Weight based Products
                System.out.print("Enter the Quantity: ");
                int quantity = scanner.nextInt();
                cart.add(shop.productList.get(list[choice]), quantity);
                choice = choice + 1;
            }

//            to Edit Variant Based Product in the Cart
            else if (shop.productList.get(list[choice]).type == 1) {
                //Printing variants of the product
                for (int i = 0; i < shop.productList.get(list[choice]).variants.size(); i++) {
                    System.out.print("\n" + i + ", " + shop.productList.get(list[choice]).variants.get(i));
                }
                System.out.print("\nChoose variant to add: ");
                int chooseVariant = scanner.nextInt();

                cart.add(shop.productList.get(list[choice]), shop.productList.get(list[choice]).variants.get(chooseVariant));
                choice = choice + 1;
            }
        }
    }

    //    making a function to remove the products from the cart
    public void removeFromCart(Cart cart, MyShop shop) {
//        return if the cart is already empty
        if (cart.cartItems.isEmpty()) {
            System.out.println(Colors.YELLOW + "Your Cart is already empty" + Colors.RESET);
            return;
        }
        int choice = 1;
        while (choice != 0) {
            Object[] item = cart.cartItems.keySet().toArray();

//            Printing the current cart items
            for (int i = 0; i < item.length; i++) {
                System.out.print("\n" + (i + 1) + ". " + item[i]);
            }
            System.out.print("\nChoose the product to Remove from the cart (or Enter 0 to Go back): ");
            choice = scanner.nextInt();
            if (choice != 0) {
                String[] items = item[choice - 1].toString().split(" ");
                if (items.length == 1)
                    cart.remove(shop.productList.get(items[0]));
                System.out.println(Colors.GREEN + "Product Successfully Removed from your Cart." + Colors.RESET);
            }
        }
    }

    //    to Edit the Cart
    public void editCart(Cart cart, MyShop shop) {
        // Return if cart is empty
        if (cart.cartItems.isEmpty()) {
            System.out.println(Colors.YELLOW + "There is nothing to edit, your Cart is empty" + Colors.RESET);
            return;
        }
        int choice = 1;
        while (choice != 0) {
            Object[] items = cart.cartItems.keySet().toArray();

//          to Print Current items in cart
            for (int i = 0; i < items.length; i++) {
                System.out.print("\n" + (i + 1) + ". " + items[i]);
            }
            System.out.print("\nChoose Product to Edit (0 to Go Back): ");
            choice = scanner.nextInt();

            if (choice == 0)
                continue;

//            to Edit weight based product
            if (cart.cartItems.get(items[choice - 1]).type == 0) {
                editWCart(cart, shop.productList.get(items[choice - 1]));
            }

//            to Edit Variant Based Products
            else if (cart.cartItems.get(items[choice - 1]).type == 1) {
                String key = items[choice - 1].toString();
                String[] arr = key.split(" ");
                int n = 0;

//              It is to Finds out which variant to edit
                for (int i = 0; i < shop.productList.get(arr[0]).variants.size(); i++) {
                    if (shop.productList.get(arr[0]).variants.get(i).name.equals(arr[1])) {
                        n = i;
                    }
                }

//              IT is to get key to access product from Myshop
                String item[] = items[choice-1].toString().split(" ");
                editVCart(cart,shop.productList.get(item[0]).variants.get(n),shop.productList.get(item[0]));
            }
        }
    }

//  To Edit Weight Based Product in the Cart
    private void editWCart(Cart cart, Product product) {
        System.out.print("Enter new quantity: ");
        float newQty = scanner.nextFloat();
        cart.add(product,newQty);
        System.out.println(Colors.GREEN + "Quantity Updated to " + newQty + " successfully!" + Colors.RESET);
    }
    //Edits VBP in cart
    public void editVCart(Cart cart,Variant variant,Product product){
        int choice = 1;
        while (choice!=0){
            System.out.println("0: tO Go Back\n1: to Increment \n2: to Decrement");
            choice = scanner.nextInt();

            //for the Increment
            if (choice == 1) {
                cart.add(product,variant);
                System.out.println(Colors.GREEN + "Successfully Incremented to " + cart.cartItems.get(product.name + " " + variant.name).qty + Colors.RESET);
            }

            //for the Decrement
            else if (choice == 2) {
                if (cart.cartItems.get(product.name + " " + variant.name).qty==1) {
                    System.out.println(Colors.RED + "Cannot Decrement, only 1 Left" + Colors.RESET);
                    continue;
                }
                cart.decrementVBP(product,variant);
                System.out.println(Colors.GREEN + "Successfully Decremented to " + cart.cartItems.get(product.name + " " + variant.name).qty + Colors.RESET);
            }
        }
    }
}

