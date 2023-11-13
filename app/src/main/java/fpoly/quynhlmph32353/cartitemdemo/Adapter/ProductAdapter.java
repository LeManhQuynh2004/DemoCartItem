package fpoly.quynhlmph32353.cartitemdemo.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Dao.CartItemDao;
import fpoly.quynhlmph32353.cartitemdemo.Fragment.DetailsFragment;
import fpoly.quynhlmph32353.cartitemdemo.MainActivity;
import fpoly.quynhlmph32353.cartitemdemo.Model.CartItem;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;
import fpoly.quynhlmph32353.cartitemdemo.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> list;

    CartItemDao cartItemDao;

    private FragmentManager fragmentManager;

    public ProductAdapter(Context context, List<Product> list,FragmentManager fragmentManager) {
        this.context = context;
        this.list = list;
        cartItemDao = new CartItemDao(context);
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_price.setText(list.get(position).getPrice() + "");
        holder.itemView.setOnClickListener(view -> {
            DetailsFragment show_detail_fragment = new DetailsFragment();
            Product product = list.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("product", product);
            show_detail_fragment.setArguments(bundle);
            ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, show_detail_fragment).commit();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price;
        Button button;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tvProductName);
            tv_price = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}
