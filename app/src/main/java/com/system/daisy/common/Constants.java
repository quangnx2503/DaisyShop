package com.system.daisy.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.daisy.entity.CartItem;
import com.system.daisy.entity.PersonalCartItems;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class Constants {
    final static int random = new Random().nextInt(9999) + 1000; // [0, 60] + 20 => [20, 80]

    public static class getRandomNumber {
        public static final String numberRandom = String.valueOf(random);
        public static final int numberCheck = Integer.parseInt(numberRandom);
    }

    public static class statusLogin {
        public static boolean checkLogin;
    }

    public static class accountSave {
        public static String emailAccount = "";
    }

    public static class personalCart {

        public static PersonalCartItems getCartOfUser(String email) {
            PersonalCartItems pCart = new PersonalCartItems();
            ArrayList<PersonalCartItems> listPersonalCartItems = Constants.personalCart.listPersonalCartItems;
            if (listPersonalCartItems.size() != 0) {
                for (PersonalCartItems p : listPersonalCartItems) {
                    if (p.getEmail().equals(email)) {
                        pCart = p;
                    }
                }
            }
            return pCart;
        }

//        public static PersonalCartItems getCartOfUser(String email, Context context) {
//            ArrayList<PersonalCartItems> listPersonalCartItems = getListPersonalCartItems(context);
//            PersonalCartItems pCart = new PersonalCartItems();
//            if (listPersonalCartItems.size() != 0) {
//                for (PersonalCartItems p : listPersonalCartItems) {
//                    if (p.getEmail().equals(email)) {
//                        pCart = p;
//                    }
//                }
//            }
//            return pCart;
//        }

        public static ArrayList<PersonalCartItems> getListPersonalCartItems(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("dataStore", Context.MODE_PRIVATE);
            String serializedObject = sharedPreferences.getString("jsonListPersonalCart", "");
            ArrayList<PersonalCartItems> listPersonalCartItems = new ArrayList<>();
            if (serializedObject != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<PersonalCartItems>>() {
                }.getType();
                listPersonalCartItems = gson.fromJson(serializedObject, type);
            }
            return listPersonalCartItems;
        }

//        public static ArrayList<PersonalCartItems> setCartOfUser(CartItem item, String email, Context context) {
//            boolean isInCart = false;
//            ArrayList<PersonalCartItems> listPersonalCartItems = getListPersonalCartItems(context);
//            PersonalCartItems pCart = getCartOfUser(email, context);
//            ArrayList<CartItem> listItem = pCart.getCartItems();
//            if (listItem != null) {
//                for (CartItem cItem : listItem) {
//                    if (cItem.getId() == item.getId()) {
//                        isInCart = true;
//                        cItem.setQuantity(cItem.getQuantity() + 1);
//                        break;
//                    }
//                }
//                if (!isInCart) {
//                    listItem.add(item);
//                }
//                pCart.setCartItems(listItem);
//            } else {
//                ArrayList<CartItem> items = new ArrayList<>();
//                items.add(item);
//                pCart.setEmail(email);
//                pCart.setCartItems(items);
//            }
//            listPersonalCartItems.add(pCart);
//            return listPersonalCartItems;
//        }

        public static ArrayList<PersonalCartItems> addItemToCart(CartItem item, String email) {
            boolean isInCart = false;
            PersonalCartItems pCart = getCartOfUser(email);
            ArrayList<CartItem> listItem = pCart.getCartItems();
            if (listItem != null) {
                for (CartItem cItem : listItem) {
                    if (cItem.getId() == item.getId()) {
                        isInCart = true;
                        cItem.setQuantity(cItem.getQuantity() + 1);
                        break;
                    }
                }
                if (!isInCart) {
                    listItem.add(item);
                }
                pCart.setCartItems(listItem);
            } else {
                ArrayList<CartItem> items = new ArrayList<>();
                items.add(item);
                pCart.setEmail(email);
                pCart.setCartItems(items);
            }
            listPersonalCartItems.add(pCart);
            return listPersonalCartItems;
        }

        public static ArrayList<PersonalCartItems> updateCart(PersonalCartItems pCart, String email) {
            for (PersonalCartItems item : listPersonalCartItems) {
                if (item.getEmail() == email) {
                    listPersonalCartItems.remove(item);
                    listPersonalCartItems.add(pCart);
                }
            }
            return listPersonalCartItems;
        }

        public static void removeCartOfUser(String email) {
            for(PersonalCartItems pItem : listPersonalCartItems){
                if(pItem.getEmail().equals(email)){
                    pItem.getCartItems().clear();
                }
            }
        }

//        public static void removeCartOfUser(String email, Context context) {
//            ArrayList<CartItem> cItems = getCartOfUser(email, context).getCartItems();
//            cItems.clear();
//        }

        public static int totalCost(String email) {
            int totalPrice = 0;
            if (getCartOfUser(email).getCartItems() != null) {
                for (CartItem item : getCartOfUser(email).getCartItems()) {
                    totalPrice += item.getSale() * item.getQuantity();
                }
            }
            return totalPrice;
        }
//
//        public static int totalCost(String email, ArrayList<PersonalCartItems> listPersonalCartItems) {
//            int totalPrice = 0;
//            if (getCartOfUser(email, listPersonalCartItems).getCartItems() != null) {
//                for (CartItem item : getCartOfUser(email, listPersonalCartItems).getCartItems()) {
//                    totalPrice += item.getSale() * item.getQuantity();
//                }
//            }
//            return totalPrice;
//        }

//        public static int cartQuantity(String email, Context context) {
//            int quantity = 0;
//            ArrayList<CartItem> listItems = getCartOfUser(email, context).getCartItems();
//            if (listItems != null && listItems.size() > 0) {
//                quantity = getCartOfUser(email, context).getCartItems().size();
//            }
//            return quantity;
//        }

        public static int cartQuantity(String email) {
            int quantity = 0;
            ArrayList<CartItem> listItems = getCartOfUser(email).getCartItems();
            if (listItems != null && listItems.size() > 0) {
                quantity = getCartOfUser(email).getCartItems().size();
            }
            return quantity;
        }

        public <T> void setListPersonalCartItemsList(ArrayList<T> list, Context context) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            SharedPreferences sharedPreferences = context.getSharedPreferences("dataStore", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("jsonListPersonalCart", json);
            editor.commit();
        }

        public static ArrayList<PersonalCartItems> listPersonalCartItems = new ArrayList<>();
    }
}
