package net.sf.anathema.framework.module;

import javax.swing.Action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.action.ItemTypeLoadAction;
import net.sf.anathema.framework.presenter.action.ItemTypeNewAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.reporting.AnathemaPrintAction;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.initialization.Tool;
import net.sf.anathema.lib.resources.IResources;

@Tool
public class AnathemaCoreTool implements IAnathemaTool {

  public void add(IResources resources, IAnathemaModel model, IAnathemaToolbar toolbar) {
    toolbar.addSeparator();
    toolbar.addMenu(
        ItemTypeNewAction.getButtonIcon(resources),
        ItemTypeNewAction.createToolActions(model, resources),
        ItemTypeNewAction.createToolTip(resources));
    toolbar.addMenu(
        ItemTypeLoadAction.getButtonIcon(resources),
        ItemTypeLoadAction.createToolActions(model, resources),
        ItemTypeLoadAction.createToolTip(resources));
    Action save = AnathemaSaveAction.createToolAction(model, resources);
    Action saveAll = AnathemaSaveAllAction.createToolAction(model, resources);
    Action print = AnathemaPrintAction.createToolAction(model, resources);
    toolbar.addTools(save, saveAll, print);
  }
}