package fpoly.quynhlmph32353.cartitemdemo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySql extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shopping_cart_db";
    private static final int DATABASE_VERSION = 3;

    public MySql(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Table Product
        String CreateTableProduct = "CREATE TABLE Product(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(CreateTableProduct);
        String insertDefaultProduct = "INSERT INTO Product (id,name,price) VALUES (1, 'product',20000)";
        sqLiteDatabase.execSQL(insertDefaultProduct);
        String insertDefaultProduct2 = "INSERT INTO Product (id,name,price) VALUES (2, 'product2',20000)";
        sqLiteDatabase.execSQL(insertDefaultProduct2);
        String insertDefaultProduct3 = "INSERT INTO Product (id,name,price) VALUES (3, 'product3',30000)";
        sqLiteDatabase.execSQL(insertDefaultProduct3);
        String insertDefaultProduct4 = "INSERT INTO Product (id,name,price) VALUES (4, 'product4',40000)";
        sqLiteDatabase.execSQL(insertDefaultProduct4);
        String insertDefaultProduct5 = "INSERT INTO Product (id,name,price) VALUES (5, 'product5',20000)";
        sqLiteDatabase.execSQL(insertDefaultProduct5);
        String insertDefaultProduct6 = "INSERT INTO Product (id,name,price) VALUES (6, 'product6',2000)";
        sqLiteDatabase.execSQL(insertDefaultProduct6);

        //Table CartItem
        String CreateTableCartItem = "CREATE TABLE CartItem(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "product_id INTEGER NOT NULL," +
                "quantity INTEGER NOT NULL," +
                "FOREIGN KEY(product_id) REFERENCES Product(id))";
        //Table Cart
        sqLiteDatabase.execSQL(CreateTableCartItem);
        String CreateTableCart = "CREATE TABLE Cart(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL)";
        sqLiteDatabase.execSQL(CreateTableCart);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Product");
        db.execSQL("DROP TABLE IF EXISTS CartItem");
        db.execSQL("DROP TABLE IF EXISTS Cart");
        onCreate(db);
    }
}
