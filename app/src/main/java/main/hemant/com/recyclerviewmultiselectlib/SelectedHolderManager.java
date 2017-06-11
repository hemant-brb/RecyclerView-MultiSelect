package main.hemant.com.recyclerviewmultiselectlib;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hemant Saini on 02-06-2017.
 */

public class SelectedHolderManager {

    private SparseArray<WeakReference<RecyclerView.ViewHolder>> mHoldersByPosition;


    public SelectedHolderManager() {
        this.mHoldersByPosition = new SparseArray<>();
    }



    public RecyclerView.ViewHolder getHolder(int position) {
        WeakReference<RecyclerView.ViewHolder> holderRef = mHoldersByPosition.get(position);
        if (holderRef == null) {
            return null;
        }

        RecyclerView.ViewHolder holder = holderRef.get();
        if (holder == null || holder.getAdapterPosition() != position) {
            mHoldersByPosition.remove(position);
            return null;
        }

        return holder;
    }

    public void bindHolder(RecyclerView.ViewHolder holder, int position) {
        mHoldersByPosition.put(position, new WeakReference<>(holder));
    }

    public List<RecyclerView.ViewHolder> getTrackedHolders() {
        List<RecyclerView.ViewHolder> holders = new ArrayList<RecyclerView.ViewHolder>();

        for (int i = 0; i < mHoldersByPosition.size(); i++) {
            int key = mHoldersByPosition.keyAt(i);
            RecyclerView.ViewHolder holder = getHolder(key);

            if (holder != null) {
                holders.add(holder);
            }
        }

        return holders;
    }

}
