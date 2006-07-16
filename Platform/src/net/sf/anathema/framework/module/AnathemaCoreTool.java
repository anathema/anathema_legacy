package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAction;
import net.sf.anathema.framework.presenter.itemmanagement.AnathemaSaveAllAction;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.reporting.controller.AnathemaPrintAction;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaCoreTool implements IAnathemaTool {

  public void add(IResources resources, IAnathemaModel model, IAnathemaToolbar toolbar) {
    toolbar.addTools(AnathemaSaveAction.createToolAction(model, resources));
    toolbar.addTools(AnathemaSaveAllAction.createToolAction(model, resources));
    toolbar.addTools(AnathemaPrintAction.createToolAction(model, resources));
  }
}