package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.reporting.AbstractPrintAction;
import net.sf.anathema.framework.reporting.ControlledPrintAction;
import net.sf.anathema.framework.reporting.QuickPrintAction;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;

public class CampaignPerspectiveTool {

  public void add(IResources resources, IAnathemaModel model, ToolBar toolbar) {
    addNewAction(resources, model, toolbar);
    addLoadAction(resources, model, toolbar);
    Action save = AnathemaSaveAction.createToolAction(model, resources);
    Action saveAll = AnathemaSaveAllAction.createToolAction(model, resources);
    Action quickPrint = getPrintAction(resources, model);
    toolbar.addTools(save, saveAll, quickPrint);
  }

  private void addLoadAction(IResources resources, IAnathemaModel model, ToolBar toolbar) {
    toolbar.addMenu(ItemTypeLoadAction.getButtonIcon(resources), ItemTypeLoadAction.createToolActions(model, resources),
            ItemTypeLoadAction.createToolTip(resources));
  }

  private void addNewAction(IResources resources, IAnathemaModel model, ToolBar toolbar) {
    toolbar.addMenu(ItemTypeNewAction.getButtonIcon(resources), ItemTypeNewAction.createToolActions(model, resources),
            ItemTypeNewAction.createToolTip(resources));
  }

  private Action getPrintAction(IResources resources, IAnathemaModel model) {
    if (AbstractPrintAction.isAutoOpenSupported()) {
      return QuickPrintAction.createToolAction(model, resources);
    } else {
      return ControlledPrintAction.createToolAction(model, resources);
    }
  }
}