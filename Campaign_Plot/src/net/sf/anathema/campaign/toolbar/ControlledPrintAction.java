package net.sf.anathema.campaign.toolbar;

import net.sf.anathema.campaign.module.CampaignManagementExtension;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.campaign.item.IItemManagementModel;
import net.sf.anathema.campaign.item.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.reporting.ControlledPrintCommand;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import static javax.swing.KeyStroke.getKeyStroke;

public class ControlledPrintAction extends AbstractPrintAction {

  public static Action createToolAction(IApplicationModel model, Resources resources) {
    SmartAction action = new ControlledPrintAction(model, resources);
    action.setToolTipText(resources.getString("Anathema.Reporting.Menu.PrintItem.Name"));
    action.setIcon(new PlatformUI().getPDFTaskBarIcon());
    return action;
  }

  private ControlledPrintAction(IApplicationModel anathemaModel, Resources resources) {
    super(anathemaModel, resources);
  }

  @Override
  protected KeyStroke createKeyStroke() {
    return getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | Event.SHIFT_MASK);
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
    new ControlledPrintCommand(resources, anathemaModel, item).execute();
  }
}