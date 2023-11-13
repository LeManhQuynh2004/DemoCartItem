package fpoly.quynhlmph32353.cartitemdemo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Dao.CartItemDao;
import fpoly.quynhlmph32353.cartitemdemo.Model.CartItem;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;
import fpoly.quynhlmph32353.cartitemdemo.R;

public class DetailsFragment extends Fragment {
    View view;
    TextView tv_name, tv_price, tv_quantity, tv_tongTien;
    Product product;
    List<CartItem> list = new ArrayList<>();

    TextView tv_SoLuong;
    int quantity = 1, product_id;
    CartItemDao cartItemDao;
    ImageView img_sendCartItem;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details, container, false);
        toolbar = view.findViewById(R.id.Toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("CartItem");
        tv_name = view.findViewById(R.id.tv_name);
        img_sendCartItem = view.findViewById(R.id.img_sendCartItem);
        tv_price = view.findViewById(R.id.tv_price);
        tv_quantity = view.findViewById(R.id.tv_quantity);
        tv_tongTien = view.findViewById(R.id.tv_tongTien);
        cartItemDao = new CartItemDao(getContext());
        list = cartItemDao.getAllCartItem();
        tv_SoLuong = view.findViewById(R.id.tv_SoLuong);
        tv_SoLuong.setText(list.size() + "");
        Bundle bundle = getArguments();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
            if (product != null) {
                product_id = product.getId();
                tv_name.setText(product.getName());
                tv_price.setText(product.getPrice() + "");
            }
        }
        tv_quantity.setText(quantity + "");
        tv_tongTien.setText((quantity * product.getPrice()) + "");

        view.findViewById(R.id.bt_cong).setOnClickListener(view1 -> {
            quantity++;
            tv_quantity.setText(quantity + "");
            tv_tongTien.setText((quantity * product.getPrice()) + "");
        });
        view.findViewById(R.id.bt_tru).setOnClickListener(view1 -> {
            if (quantity > 0) {
                quantity--;
                tv_quantity.setText(quantity + "");
                tv_tongTien.setText((quantity * product.getPrice()) + "");
            } else {
                Toast.makeText(getContext(), "Không thực hiện được", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.bt_addCartItem).setOnClickListener(view1 -> {
            if(quantity > 0){
                CartItem cartItem = new CartItem();
                cartItem.setProductId(product_id);
                cartItem.setQuantity(quantity);
                if (cartItemDao.addCartItem(cartItem)) {
                    Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                    list.add(cartItem);
                    tv_SoLuong.setText(list.size() + "");
                } else {
                    Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(), "Vui log chon so luong", Toast.LENGTH_SHORT).show();
            }

        });
        img_sendCartItem.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CartItemFragment()).commit();
        });
        return view;
    }
}