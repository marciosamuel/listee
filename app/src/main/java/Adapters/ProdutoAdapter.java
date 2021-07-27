package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dispositivos.moveis.listee.R;

import java.util.List;

import Models.ProdutoModel;

public class ProdutoAdapter extends ArrayAdapter<ProdutoModel> {

    private final List<ProdutoModel> produtos;

    public static class ViewHolder{
        TextView nome;
        TextView quantidadeTotal;

        public ViewHolder(View view){
            nome = (TextView) view.findViewById(R.id.product_name);
            quantidadeTotal = (TextView) view.findViewById(R.id.product_quantity);
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
        holder.quantidadeTotal.setText(produto.getTotalString());

        return convertView;
    }

}
