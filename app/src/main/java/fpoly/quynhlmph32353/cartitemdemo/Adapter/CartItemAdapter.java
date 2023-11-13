package fpoly.quynhlmph32353.cartitemdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.quynhlmph32353.cartitemdemo.Dao.CartItemDao;
import fpoly.quynhlmph32353.cartitemdemo.Dao.ProductDao;
import fpoly.quynhlmph32353.cartitemdemo.Fragment.CartItemFragment;
import fpoly.quynhlmph32353.cartitemdemo.Model.CartItem;
import fpoly.quynhlmph32353.cartitemdemo.Model.OnTotalPriceChangeListener;
import fpoly.quynhlmph32353.cartitemdemo.Model.Product;
import fpoly.quynhlmph32353.cartitemdemo.R;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>
{
    List<CartItem> list;
    Context context;
    CartItemDao cartItemDao;
    ProductDao productDao;
    CartItemFragment cartItemFragment;

    private OnTotalPriceChangeListener onCartItemChangeListener;

    // Constructor and other methods...

    public void setOnCartItemChangeListener(OnTotalPriceChangeListener listener) {
        this.onCartItemChangeListener = listener;
    }

    public CartItemAdapter(List<CartItem> list, Context context) {
        this.list = list;
        this.context = context;
        productDao = new ProductDao(context);
        cartItemDao = new CartItemDao(context);
        cartItemFragment = new CartItemFragment();
    }
    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cartitem,parent,false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Product product = productDao.getProductById(String.valueOf(list.get(position).getProductId()));
        if(product != null){
            holder.tv_name.setText(product.getName());
            holder.tv_price.setText(product.getPrice()+"");
            holder.tv_quantity.setText(list.get(position).getQuantity()+"");
            holder.tv_sumPrice.setText((list.get(position).getQuantity() * product.getPrice())+"");
        }
        holder.tv_xoaSanPham.setOnClickListener(view -> {
            if(cartItemDao.deleteCartItem(list.get(position))){
                Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                list.remove(list.get(position));
                notifyDataSetChanged();
                if (onCartItemChangeListener != null) {
                    onCartItemChangeListener.onTotalPriceChanged();
                }
            }else{
                Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_price,tv_quantity,tv_sumPrice,tv_xoaSanPham;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_item_cartItem);
            tv_price = itemView.findViewById(R.id.tv_price_item_cartItem);
            tv_quantity = itemView.findViewById(R.id.tv_quantity_item_cartItem);
            tv_sumPrice = itemView.findViewById(R.id.tv_sumPrice_item_cartItem);
            tv_xoaSanPham = itemView.findViewById(R.id.tv_xoaSanPham);
        }
    }
}
