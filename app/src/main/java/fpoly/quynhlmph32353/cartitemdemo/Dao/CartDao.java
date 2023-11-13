package fpoly.quynhlmph32353.cartitemdemo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Database.MySql;
import fpoly.quynhlmph32353.cartitemdemo.Model.Cart;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;

public class CartDao {
    MySql mySql;

    public CartDao(Context context) {
        mySql = new MySql(context);
    }

    public boolean addCart(Cart cart) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", cart.getDate());
        long check = sqLiteDatabase.insert("Cart", null, values);
        cart.setId((int) check);
        return check != -1;
    }

    public boolean deleteCart(Cart cart) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        String dk[] = {String.valueOf(cart.getId())};
        long check = sqLiteDatabase.delete("Cart", "id = ?", dk);
        return check != -1;
    }

    public boolean updateCart(Cart cart) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        String dk[] = {String.valueOf(cart.getId())};
        ContentValues values = new ContentValues();
        values.put("date", cart.getDate());
        long check = sqLiteDatabase.update("Cart", values, "id = ?", dk);
        return check != -1;
    }

    public List<Cart> getAllCart() {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        List<Cart> productList = new ArrayList<>();
        String query = "SELECT * FROM Cart";
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Cart cart = new Cart();
                    cart.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    cart.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                    productList.add(cart);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

}
