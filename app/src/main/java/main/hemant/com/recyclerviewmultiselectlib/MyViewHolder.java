package main.hemant.com.recyclerviewmultiselectlib;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hemant.multiselect_recyclerview.view.MultiSelectViewHolder;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public class MyViewHolder extends MultiSelectViewHolder {

    private TextView textView;

    public MyViewHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callOnClick();
                Toast.makeText(itemView.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(itemView.getContext(), "Item Long Clicked", Toast.LENGTH_SHORT).show();
                return callOnLongClick();
                // to set MultiSelect on Long Click.
            }
        });
        textView = (TextView) itemView.findViewById(R.id.textview);
    }

    public void onBind(String s) {
        textView.setText(s);
    }


}
