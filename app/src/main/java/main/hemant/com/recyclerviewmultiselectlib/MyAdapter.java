package main.hemant.com.recyclerviewmultiselectlib;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public class MyAdapter extends MultiSelectAdapter {


    private ArrayList<String> arrayList;
    private Activity a;

    public MyAdapter(Activity activity, ArrayList<String> arrayList) {
        super(activity);
        this.a = activity;
        this.arrayList = arrayList;
    }

    @Override
    protected MultiSelectViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycler_view_cell, parent, false));
    }

    @Override
    protected void onBindHViewHolder(MultiSelectViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.onBind(arrayList.get(position));
        }
    }

    @Override
    protected int getItemsCount() {
        return arrayList.size();
    }

    @Override
    protected int setActiveCellColor() {
        return 0;
    }

    @Override
    protected int setActionBarActiveModeColor() {
        return 0;
    }

    @Override
    protected int getActionModeMenuRes() {
        return 0;
    }

    @Override
    public void onMenuItemClick(int itemId) {
        //TODO : handle on Click of Menu Item Here..
        //TODO : call getListOfSelectedItems() to get List of Positions of Selected Items
    }


    @Override  //Override this method to get the List of Positions of Selected Objects.
    protected List<Integer> getListOfSelectedItems() {
        return super.getListOfSelectedItems();
    }

}
