package fpoly.quynhlmph32353.cartitemdemo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Database.MySql;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;

public class ProductDao {
    MySql mySql;

    public static final String TAG = "PRODUCTDAO";
    public ProductDao(Context context) {
        mySql = new MySql(context);
    }

    public boolean addProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        long check = sqLiteDatabase.insert("Product", null, values);
        product.setId((int) check);
        return check != -1;
    }

    public boolean deleteProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        String dk[] = {String.valueOf(product.getId())};
        long check = sqLiteDatabase.delete("Product", "id = ?", dk);
        return check != -1;
    }

    public boolean updateProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        String dk[] = {String.valueOf(product.getId())};
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        long check = sqLiteDatabase.update("Product", values, "id = ?", dk);
        return check != -1;
    }

    public List<Product> getAllProducts() {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Product";
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    product.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    product.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow("price")));
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    public Product getProductById(String id) {
        SQLiteDatabase sqLiteDatabase = mySql.getWritableDatabase();
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE id = ?";
        String[] dk = {id};

        Cursor cursor = sqLiteDatabase.rawQuery(query, dk);
            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    product.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                    product.setPrice(cursor.getInt(cursor.getColumnIndexOrThrow("price")));
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        if (!productList.isEmpty()) {
            return productList.get(0);
        } else {
            Log.e(TAG, "getProductById: "+"ERR");
            return null;
        }
    }
}
