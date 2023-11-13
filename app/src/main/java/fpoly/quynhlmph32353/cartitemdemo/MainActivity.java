package fpoly.quynhlmph32353.cartitemdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fpoly.quynhlmph32353.cartitemdemo.Fragment.ProductFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductFragment()).commit();
    }
}