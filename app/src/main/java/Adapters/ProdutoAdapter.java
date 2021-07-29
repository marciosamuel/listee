package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.dispositivos.moveis.listee.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import Models.ProdutoModel;

public class ProdutoAdapter extends ArrayAdapter<ProdutoModel> {

    private final List<ProdutoModel> produtos;

    public static class ViewHolder{
        TextView nome;
        TextView quantidade;
        CardView cardProduct;

        public ViewHolder(View view){
            cardProduct = (CardView) view.findViewById(R.id.produto_cardview);
            nome = (TextView) view.findViewById(R.id.product_name);
            quantidade = (TextView) view.findViewById(R.id.product_quantity);
        }
    }

    public ProdutoAdapter(Context context, List<ProdutoModel> produtos){
        super(context, R.layout.activity_lista_de_compras);
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public ProdutoModel getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ProdutoAdapter.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.produto, parent, false);
            holder = new ProdutoAdapter.ViewHolder(convertView);

            convertView.setTag(holder);
        }else{
            holder = (ProdutoAdapter.ViewHolder) convertView.getTag();
        }

        ProdutoModel produto = getItem(position);
        holder.nome.setText(produto.getNome());
        holder.quantidade.setText(produto.getQuantidade());

        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.popupInspirationTheme);
                View popupEditProduct = LayoutInflater.from(getContext()).inflate(R.layout.activity_popup_edit_product, v.findViewById(R.id.popup_edit_product_container));

                EditText newProductName = v.findViewById(R.id.edit_product_name);
                EditText newProductQuantity = v.findViewById(R.id.edit_product_quantity);
                Button addProduct = v.findViewById(R.id.btn_edit_product);

                bottomSheetDialog.setContentView(popupEditProduct);
                bottomSheetDialog.show();
            }
        });

        return convertView;
    }

}
