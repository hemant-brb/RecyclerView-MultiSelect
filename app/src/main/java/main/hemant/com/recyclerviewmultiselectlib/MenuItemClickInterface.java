package main.hemant.com.recyclerviewmultiselectlib;

import android.widget.TextView;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public interface MenuItemClickInterface {

    public void onMenuItemClick(int itemId);

    public void onAllItemSelect();

    public void setTextView(TextView textView);

}
