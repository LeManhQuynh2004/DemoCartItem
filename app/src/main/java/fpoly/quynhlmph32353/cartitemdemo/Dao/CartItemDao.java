package fpoly.quynhlmph32353.cartitemdemo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Database.MySql;
import fpoly.quynhlmph32353.cartitemdemo.Model.CartItem;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;

public class CartItemDao {
    MySql mySql;

    public CartItemDao(Context context) {
        mySql = new MySql(context);
    }

    public boolean addCartItem(CartItem cartItem) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", cartItem.getProductId());
        values.put("quantity",cartItem.getQuantity());
        long check = sqLiteDatabase.insert("CartItem", null, values);
        cartItem.setId((int) check);
        return check != -1;
    }

    public boolean deleteCartItem(CartItem cartItem) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        String dk[] = {String.valueOf(cartItem.getId())};
        long check = sqLiteDatabase.delete("CartItem", "id = ?", dk);
        return check != -1;
    }

    public boolean updateCartItem(CartItem cartItem) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        String dk[] = {String.valueOf(cartItem.getId())};
        ContentValues values = new ContentValues();
        values.put("product_id", cartItem.getProductId());
        values.put("quantity",cartItem.getQuantity());
        long check = sqLiteDatabase.update("CartItem", values, "id = ?", dk);
        return check != -1;
    }

    public List<CartItem> getAllCartItem() {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        List<CartItem> productList = new ArrayList<>();
        String query = "SELECT * FROM CartItem";
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    cartItem.setProductId(cursor.getInt(cursor.getColumnIndexOrThrow("product_id")));
//                    cartItem.setCartId(cursor.getInt(cursor.getColumnIndexOrThrow("cart_id")));
                    cartItem.setQuantity(cursor.getInt(
                            cursor.getColumnIndexOrThrow("quantity")
                    ));
                    productList.add(cartItem);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
