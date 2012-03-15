package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.action.ItemTypeLoadAction;
import net.sf.anathema.framework.presenter.action.ItemTypeNewAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.reporting.AbstractPrintAction;
import net.sf.anathema.framework.reporting.AnathemaPrintAction;
import net.sf.anathema.framework.reporting.AnathemaQuickPrintAction;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.initialization.Tool;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;

@Tool
public class AnathemaCoreTool implements IAnathemaTool {

  @Override
  public void add(IResources resources, IAnathemaModel model, IAnathemaToolbar toolbar) {
    toolbar.addSeparator();
    toolbar.addMenu(ItemTypeNewAction.getButtonIcon(resources), ItemTypeNewAction.createToolActions(model, resources),
            ItemTypeNewAction.createToolTip(resources));
    toolbar.addMenu(ItemTypeLoadAction.getButtonIcon(resources), ItemTypeLoadAction.createToolActions(model, resources),
            ItemTypeLoadAction.createToolTip(resources));
    Action save = AnathemaSaveAction.createToolAction(model, resources);
    Action saveAll = AnathemaSaveAllAction.createToolAction(model, resources);
    Action quickPrint = getPrintAction(resources, model);
    toolbar.addTools(save, saveAll, quickPrint);
  }

  private Action getPrintAction(IResources resources, IAnathemaModel model) {
    if (AbstractPrintAction.isAutoOpenSupported()) {
      return AnathemaQuickPrintAction.createToolAction(model, resources);
    }
    else {
      return AnathemaPrintAction.createToolAction(model, resources);
    }
  }
}