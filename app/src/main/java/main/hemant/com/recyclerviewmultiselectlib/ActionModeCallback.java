package main.hemant.com.recyclerviewmultiselectlib;

import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Hemant Saini on 11-06-2017.
 */

public class ActionModeCallback implements ActionMode.Callback {

    private ActionMode mActionMode;
    private MultiSelectManager mMultiSelectManager;
    private Activity mActivity;
    private int mActionModeMenu;
    private MenuItemClickCallback mMenuItemClickInterface;

    public ActionModeCallback(Activity activity, MultiSelectAdapter multiSelectAdapter, MultiSelectManager multiSelectManager, int actionModeMenu) {
        this.mMultiSelectManager = multiSelectManager;
        mMenuItemClickInterface = (MenuItemClickCallback) multiSelectAdapter;
        this.mActivity = activity;
        this.mActionModeMenu = actionModeMenu;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (mActionModeMenu == 0)
            return false;
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
        mMenuItemClickInterface.onMenuItemClick(menuItem.getItemId());
        onFinishActionMode();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        if (mMultiSelectManager != null)
            mMultiSelectManager.onDestroyActionMode();
    }

    private void setActionMode(ActionMode mActionMode) {
        this.mActionMode = mActionMode;
    }

    private void inflateMenu(Menu menu) {
        this.mActivity.getMenuInflater().inflate(mActionModeMenu, menu);
    }

    public void onFinishActionMode() {
        if (mActionMode != null)
            mActionMode.finish();
    }

}
