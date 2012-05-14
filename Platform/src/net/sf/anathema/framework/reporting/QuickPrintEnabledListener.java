package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;

public class QuickPrintEnabledListener extends ItemManagementModelAdapter {

  private SmartAction action;
  private DefaultReportFinder finder;

  public QuickPrintEnabledListener(SmartAction action, DefaultReportFinder finder) {
    this.action = action;
    this.finder = finder;
  }

  @Override
  public void itemSelected(IItem item) {
    Report defaultReport = finder.getDefaultReport(item);
    boolean quickPrintPossible = defaultReport != null;
    action.setEnabled(quickPrintPossible);
  }
}
