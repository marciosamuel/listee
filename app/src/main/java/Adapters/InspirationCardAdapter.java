package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dispositivos.moveis.listee.R;

import Models.InspirationCardModel;

public class InspirationCardAdapter extends ArrayAdapter<InspirationCardModel> {

    public InspirationCardAdapter(Context context){
        super(context, R.layout.activity_cardview_inspirations);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        InspirationCardAdapter.ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.activity_cardview_inspirations, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else{
            holder = (InspirationCardAdapter.ViewHolder) convertView.getTag();
        }

        InspirationCardModel inspirationCardModel = getItem(position);
        holder.title.setText(inspirationCardModel.getTitle());
        holder.subTitle.setText(inspirationCardModel.getSubTitle());
        holder.author.setText(inspirationCardModel.getAuthor());

        return convertView;
    }

    public static class ViewHolder{
        TextView title;
        TextView subTitle;
        TextView author;

        public ViewHolder(View view){
            title = (TextView) view.findViewById(R.id.title_inspiration);
            subTitle = (TextView) view.findViewById(R.id.sub_title);
            author = (TextView) view.findViewById(R.id.author);
        }
    }
}
