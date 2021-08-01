package Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.dispositivos.moveis.listee.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

import Models.ProdutoModel;

public class ProdutoAdapter extends ArrayAdapter<ProdutoModel> {

    private final List<ProdutoModel> produtos;

    public static class ViewHolder{
        TextView nome;
        TextView quantidade;

        public ViewHolder(View view){
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

        return convertView;
    }

}
