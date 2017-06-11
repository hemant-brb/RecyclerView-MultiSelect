package main.hemant.com.recyclerviewmultiselectlib;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public abstract class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectViewHolder> implements ActionModeInterface, MenuItemClickCallback {


    private Activity mActivity;
    private MultiSelectManager mMultiSelectManager;
    private ActionModeCallback mActionModeCallback;


    public MultiSelectAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        mMultiSelectManager = new MultiSelectManager(this);
        mMultiSelectManager.setActiveItemColor(setActiveCellColor());
        mActionModeCallback = new ActionModeCallback(mActivity, this, mMultiSelectManager, getActionModeMenuRes());
    }

    @Override
    public MultiSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = getViewHolder(parent, viewType);
        if (viewHolder instanceof MultiSelectViewHolder) {
            MultiSelectViewHolder hViewHolder = (MultiSelectViewHolder) viewHolder;
            hViewHolder.setMultiSelectManager(mMultiSelectManager);
            return hViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MultiSelectViewHolder holder, int position) {
        mMultiSelectManager.getSelectedHolderManager().bindHolder(holder, position);
        mMultiSelectManager.refreshView(position, holder.itemView);
        onBindHViewHolder(holder, position);
    }


    @Override
    public int getItemCount() {
        return getItemsCount();
    }


    protected abstract int setActiveCellColor();

    protected abstract int setActionBarActiveModeColor();

    protected abstract int getActionModeMenuRes();

    protected abstract int getItemsCount();

    protected abstract MultiSelectViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindHViewHolder(MultiSelectViewHolder holder, int position);

    @Override
    public void setActionModeEnable() {
        if (mActivity == null)
            return;
        mActivity.startActionMode(mActionModeCallback);
    }

    @Override
    public void setActionModeDisable() {
        mActionModeCallback.onFinishActionMode();
    }


    protected List<Integer> getListOfSelectedItems() {
        return mMultiSelectManager.getSelectedPositions();
    }

    @Override
    public void onAllItemSelect(){
        mMultiSelectManager.selectAllItems(getItemCount());
    }

}
