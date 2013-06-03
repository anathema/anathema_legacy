package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.module.CampaignManagementExtension;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.campaign.item.IItemManagementModel;
import net.sf.anathema.campaign.item.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.reporting.QuickPrintCommand;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import static javax.swing.KeyStroke.getKeyStroke;

public class QuickPrintAction extends AbstractPrintAction {

  public static Action createToolAction(IApplicationModel model, Resources resources) {
    SmartAction action = new QuickPrintAction(model, resources);
    action.setToolTipText(resources.getString("Anathema.Reporting.Menu.QuickPrint.Tooltip"));
    action.setIcon(new PlatformUI().getPDFTaskBarIcon());
    return action;
  }

  private QuickPrintAction(IApplicationModel anathemaModel, Resources resources) {
    super(anathemaModel, resources);
  }

  @Override
  protected KeyStroke createKeyStroke() {
    return getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
  }

  @Override
  protected IItemManagementModelListener createEnablingListener() {
    return new PrintEnabledListener(this, anathemaModel.getReportRegistry());
  }

  @Override
  protected void execute(Component parentComponent) {
    IItemManagementModel itemManagement = CampaignManagementExtension.getItemManagement(anathemaModel);
    IItem item = itemManagement.getSelectedItem();
    if (item == null) {
      return;
    }
    FirstReportFinder reportFinder = new FirstReportFinder(anathemaModel);
    new QuickPrintCommand(resources, item, reportFinder).execute();
  }
}