package Streamliners.Model;

import java.util.HashMap;

public class Cart {

    public HashMap<String, CartItem> cartItems = new HashMap<>();
    public float total, noOfItems;

//  adding weight based products
    public void add(Product product, float qty) {
//        If item already exist in cart
        if(cartItems.containsKey(product.name)){
            total -= cartItems.get(product.name).cost();
            cartItems.get(product.name).qty = qty;
        }

//        Adding for the first time
        else{
            CartItem item = new CartItem(product.name, product.pricePerKg, qty);
            cartItems.put(product.name, item);
            noOfItems ++;
        }

//        Update cart summary
        total += product.pricePerKg * qty;

    }

    //    adding variant based product
    public void add(Product product, Variant variant) {

        // Key for VBD concatenating product and variant name
        String key = product.name + " " + variant.name;

        if(cartItems.containsKey(key)){
            cartItems.get(key).qty++;
        }

//        Adding for the first time
        else{
            CartItem item = new CartItem(product.name, variant.price, 1);
            cartItems.put(key , item);
        }

//        Update cart summary
        noOfItems++;
        total += variant.price;

    }

    //  remove weight based products
    public void remove(Product product){
        if(product.type == ProductType.TYPE_WB){
        removeWBP(product);
        }else {
            removeAllVAriantsOfVBP(product);
        }

    }
    // Remove WBP from the cart
    private void removeWBP(Product product) {
        //    Update cart summary
        total -= cartItems.get(product.name).cost();
        noOfItems--;
        cartItems.remove(product.name);
    }

    //    removing all the variants of Variants based product
    public void removeAllVAriantsOfVBP(Product product) {
        for (Variant variant : product.variants) {
            String key = product.name + " " + variant.name;

            if (cartItems.containsKey(key)) {
                //    Update cart summary
                total -= cartItems.get(key).cost();
                noOfItems -= cartItems.get(key).qty;

                cartItems.remove(key);
            }
        }
    }

//    Decrement quantity
    public  void decrementVBP(Product product, Variant variant){
        String key = product.name + " " + variant.name;

//        Update qty
        cartItems.get(key).qty--;

            //    Update cart summary
            total -= variant.price;
            noOfItems --;

//            Removing the item when the quantity is zero
            if(cartItems.get(key).qty == 0)
                cartItems.remove(key);

    }

    @Override
    public String toString() {
        return cartItems.values() + "\n" + "Total Items = " + noOfItems + "(" + total + ")";
    }
}
