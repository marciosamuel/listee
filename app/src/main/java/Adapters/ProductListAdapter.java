package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.dispositivos.moveis.listee.ListaDeComprasActivity;
import com.dispositivos.moveis.listee.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import Models.ProductListModel;

public class ProductListAdapter extends ArrayAdapter<ProductListModel> {

    private final List<ProductListModel> produtos;

    public static class ViewHolder{
        TextView nome;
        TextView quantidade;
        CheckBox checkboxProduct;
        CardView cardProduct;

        public ViewHolder(View view){
            cardProduct = (CardView) view.findViewById(R.id.produto_cardview);
            nome = (TextView) view.findViewById(R.id.product_name);
            quantidade = (TextView) view.findViewById(R.id.product_quantity);
            checkboxProduct = (CheckBox) view.findViewById(R.id.checkbox_product);
        }
    }

    public ProductListAdapter(Context context, List<ProductListModel> produtos){
        super(context, R.layout.activity_lista_de_compras);
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public ProductListModel getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ProductListAdapter.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.product_list, parent, false);
            holder = new ProductListAdapter.ViewHolder(convertView);

            convertView.setTag(holder);
        }else{
            holder = (ProductListAdapter.ViewHolder) convertView.getTag();
        }

        ProductListModel produto = getItem(position);
        holder.nome.setText(produto.getNome());
        holder.quantidade.setText(produto.getQuantidade());
        holder.checkboxProduct.setChecked(produto.getChecked());

        holder.checkboxProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkboxProduct.isChecked()) {
                    holder.nome.setTextColor(Color.parseColor("#C3C3C3"));
                } else {
                    holder.nome.setTextColor(Color.parseColor("#FF5789"));
                }
            }
        });

        return convertView;
    }

}
