package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.charmtree.filters.CharmFilterSettingsPage;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;

public class CharmFilterDefinitionView {
  private final Resources resources;
  private final CharmFilterSet filterSet;
  private FilterDefinitionListener onFinish;

  public CharmFilterDefinitionView(Resources resources, CharmFilterSet filterSet) {
    this.resources = resources;
    this.filterSet = filterSet;
  }

  public void show() {
    CharmFilterSettingsPage page = new CharmFilterSettingsPage(resources, filterSet);
    UserDialog userDialog = new UserDialog(SwingApplicationFrame.getParentComponent(), page);
    DialogResult result = userDialog.show();
    if (result.isCanceled()){
      onFinish.changeAborted();
    }
    else{
      onFinish.changeConfirmed();
    }
  }

  public void whenEditIsFinished(FilterDefinitionListener onFinish) {
    this.onFinish = onFinish;
  }
}