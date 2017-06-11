package main.hemant.com.recyclerviewmultiselectlib;

import android.app.Activity;
import android.content.Context;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public class ActionModeCallback implements ActionMode.Callback {

    private TextView mSelectTv;
    private ActionMode mActionMode;
    private MultiSelectManager mMultiSelectManager;
    private Activity mActivity;
    private int mActionModeMenu;
    private MenuItemClickInterface mMenuItemClickInterface;

    public ActionModeCallback(Activity activity, MultiSelectAdapter multiSelectAdapter, MultiSelectManager multiSelectManager, int actionModeMenu) {
        this.mMultiSelectManager = multiSelectManager;
        mMenuItemClickInterface = (MenuItemClickInterface) multiSelectAdapter;
        this.mActivity = activity;
        this.mActionModeMenu = actionModeMenu;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (mActionModeMenu == 0)
            inflateDefaultMenu(menu);
        else
            inflateMenu(menu);
        setActionMode(actionMode);
        return true;
    }


    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.select_all) {
            mMenuItemClickInterface.onAllItemSelect();
            return true;
        }
        mMenuItemClickInterface.onMenuItemClick(menuItem.getItemId());
        onFinishActionMode();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        if (mMultiSelectManager != null)
            mMultiSelectManager.onDestroyActionMode();
    }

    private void setActionMode(final ActionMode mActionMode) {
        this.mActionMode = mActionMode;
        LayoutInflater inflator = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        mActionMode.setCustomView(v);
        mSelectTv = (TextView) v.findViewById(R.id.select_all_btn);
        mMenuItemClickInterface.setTextView(mSelectTv);
        mSelectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mActivity, mSelectTv);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.select_all) {
                            mMenuItemClickInterface.onAllItemSelect();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    private void inflateMenu(Menu menu) {
        this.mActivity.getMenuInflater().inflate(mActionModeMenu, menu);
    }

    private void inflateDefaultMenu(Menu menu) {
        this.mActivity.getMenuInflater().inflate(R.menu.default_menu, menu);
    }

    public void onFinishActionMode() {
        if (mActionMode != null)
            mActionMode.finish();
    }

}
