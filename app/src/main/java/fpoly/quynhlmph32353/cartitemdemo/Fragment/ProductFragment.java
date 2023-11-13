package fpoly.quynhlmph32353.cartitemdemo.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Adapter.ProductAdapter;
import fpoly.quynhlmph32353.cartitemdemo.Dao.CartItemDao;
import fpoly.quynhlmph32353.cartitemdemo.Dao.ProductDao;
import fpoly.quynhlmph32353.cartitemdemo.Model.CartItem;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;
import fpoly.quynhlmph32353.cartitemdemo.R;

public class ProductFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView tv_SoLuong;
    ProductDao productDao;
    List<Product> list = new ArrayList<>();
    Toolbar toolbar;
    CartItemDao cartItemDao;
    List<CartItem> list_CartItem = new ArrayList<>();
    ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product, container, false);
        cartItemDao = new CartItemDao(getContext());
        list_CartItem = cartItemDao.getAllCartItem();
        toolbar = view.findViewById(R.id.Toolbar_Product);
        tv_SoLuong = view.findViewById(R.id.tv_SoLuong_product);
        tv_SoLuong.setText(list_CartItem.size()+"");
        recyclerView = view.findViewById(R.id.RecyclerView);
        view.findViewById(R.id.img_sendCartItem_product).setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CartItemFragment()).commit();
        });
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Product");
        productDao = new ProductDao(getContext());
        list = productDao.getAllProducts();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        productAdapter = new ProductAdapter(getContext(),list,fragmentManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(productAdapter);
        return view;
    }
}