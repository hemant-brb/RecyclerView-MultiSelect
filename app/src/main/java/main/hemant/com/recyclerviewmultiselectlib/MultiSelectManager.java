package main.hemant.com.recyclerviewmultiselectlib;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hemant Saini on 02-06-2017.
 */

public class MultiSelectManager {

    private SelectedHolderManager mSelectedHolderManager;
    private SparseBooleanArray mSelections = new SparseBooleanArray();
    private ActionModeInterface mActionModeInterface;

    private boolean mIsSelectable;
    private int mSelectedCellCount;
    private int mColor;


    private MultiSelectManager() {
        mSelectedCellCount = 0;
        mSelectedHolderManager = new SelectedHolderManager();
    }


    public MultiSelectManager(MultiSelectAdapter adapter) {
        this();
        mActionModeInterface = (ActionModeInterface) adapter;
    }


    public boolean isSelectable() {
        return mIsSelectable;
    }

    public void setSelectable(boolean selectable, MultiSelectViewHolder holder) {
        setSelectable(selectable);
        setSelected(holder);
    }

    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
        if (selectable) {
            mActionModeInterface.setActionModeEnable();
        }
    }

    public void setSelected(MultiSelectViewHolder holder) {
        int position = holder.getAdapterPosition();
        View v = holder.itemView;
        if (isSelected(position)) {
            mSelectedCellCount--;
            setSelected(position, false);
        } else {
            mSelectedCellCount++;
            setSelected(position, true);
        }
        if (mSelectedCellCount == 0) {
            setSelectable(false);
            mActionModeInterface.setActionModeDisable();
            return;
        }
        refreshView(position, v);
    }


    public void refreshView(int position, View view) {
        if (isSelected(position)) {
            if (mColor == 0)
                mColor = Color.LTGRAY;
            view.setBackgroundColor(mColor);
        } else {
            view.setBackgroundColor(Color.WHITE);
        }
    }

    private void refreshAllHolders() {
        for (RecyclerView.ViewHolder holder : mSelectedHolderManager.getTrackedHolders()) {
            refreshView(holder.getAdapterPosition(), holder.itemView);
        }
    }


    private void setSelected(int position, boolean isSelected) {
        mSelections.put(position, isSelected);
    }

    private boolean isSelected(int position) {
        return mSelections.get(position);
    }

    private void clearSelections() {
        mSelections.clear();
    }

    public List<Integer> getSelectedPositions() {
        ArrayList positions = new ArrayList();
        for (int i = 0; i < this.mSelections.size(); ++i) {
            if (this.mSelections.valueAt(i)) {
                positions.add(Integer.valueOf(this.mSelections.keyAt(i)));
            }
        }
        return positions;
    }

    public SelectedHolderManager getSelectedHolderManager() {
        return mSelectedHolderManager;
    }

    public void onDestroyActionMode() {
        mSelectedCellCount = 0;
        setSelectable(false);
        clearSelections();
        refreshAllHolders();
    }

    public void setActiveItemColor(int mColor) {
        this.mColor = mColor;
    }

    public void selectAllItems(int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            setSelected(i, true);
        }
        refreshAllHolders();
        mSelectedCellCount = itemCount;
    }
}
