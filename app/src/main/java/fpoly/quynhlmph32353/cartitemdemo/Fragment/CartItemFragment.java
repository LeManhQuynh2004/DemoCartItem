package fpoly.quynhlmph32353.cartitemdemo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Adapter.CartItemAdapter;
import fpoly.quynhlmph32353.cartitemdemo.Dao.CartItemDao;
import fpoly.quynhlmph32353.cartitemdemo.Dao.ProductDao;
import fpoly.quynhlmph32353.cartitemdemo.Model.CartItem;
import fpoly.quynhlmph32353.cartitemdemo.Model.OnTotalPriceChangeListener;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;
import fpoly.quynhlmph32353.cartitemdemo.R;

public class CartItemFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    public List<CartItem> list = new ArrayList<>();
    CartItemAdapter cartItemAdapter;
    CartItemDao cartItemDao;
    public TextView tv_tongTien;
    ProductDao productDao;
    int tongtien = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart_item, container, false);
        recyclerView = view.findViewById(R.id.RecyclerView_CartItem);
        cartItemDao = new CartItemDao(getContext());
        list = cartItemDao.getAllCartItem();
        productDao = new ProductDao(getContext());
        cartItemAdapter = new CartItemAdapter(list, getContext());
        cartItemAdapter.setOnCartItemChangeListener(new OnTotalPriceChangeListener() {
            @Override
            public void onTotalPriceChanged() {
                calculateTotalPrice();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cartItemAdapter);
        tv_tongTien = view.findViewById(R.id.tv_tongTien_cartItem);
        calculateTotalPrice();

        view.findViewById(R.id.bt_muaHang).setOnClickListener(view1 -> {

        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        calculateTotalPrice();
    }

    @Override
    public void onResume() {
        super.onResume();
        calculateTotalPrice();
    }
    public void calculateTotalPrice() {
        if (tv_tongTien == null) {
            tv_tongTien = view.findViewById(R.id.tv_tongTien_cartItem);
        }
        tongtien = 0;
        for (CartItem cartItem : list) {
            Product product = productDao.getProductById(String.valueOf(cartItem.getProductId()));
            tongtien += cartItem.getQuantity() * product.getPrice();
        }
        tv_tongTien.setText("Tong tien : " + tongtien + " VND");
    }
}