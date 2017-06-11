package main.hemant.com.recyclerviewmultiselectlib;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public class MultiSelectViewHolder extends RecyclerView.ViewHolder {

    private MultiSelectManager multiSelectManager;

    public MultiSelectViewHolder(View itemView) {
        super(itemView);
    }

    public void setMultiSelectManager(MultiSelectManager multiSelectManager) {
        this.multiSelectManager = multiSelectManager;
    }

    protected void callOnClick() {
        if (multiSelectManager.isSelectable()) {
            multiSelectManager.setSelected(MultiSelectViewHolder.this);
        }
    }

    protected boolean callOnLongClick() {
        if (!multiSelectManager.isSelectable()) {
            multiSelectManager.setSelectable(true, MultiSelectViewHolder.this);
            return true;
        }
        return false;
    }

}
