package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dispositivos.moveis.listee.R;

import Models.HomeCardModel;

public class HomeCardAdapter extends ArrayAdapter<HomeCardModel> {
    public HomeCardAdapter(Context context){
        super(context, R.layout.activity_cardview_home);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.activity_cardview_home, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        HomeCardModel homeCardModel = getItem(position);
        holder.title_card.setText(homeCardModel.getTitle());
        holder.selectedItems.setText(homeCardModel.getSelectedItems());
        holder.remainingItems.setText(homeCardModel.getRemainingItems());

        return convertView;
    }

    public static class ViewHolder{
        TextView title_card;
        TextView selectedItems;
        TextView remainingItems;

        public ViewHolder(View view){
            title_card = (TextView) view.findViewById(R.id.title_card);
            selectedItems = (TextView) view.findViewById(R.id.selected_items);
            remainingItems = (TextView) view.findViewById(R.id.remaining_items);
        }
    }
}
