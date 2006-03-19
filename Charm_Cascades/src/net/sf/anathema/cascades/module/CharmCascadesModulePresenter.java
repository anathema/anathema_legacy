package net.sf.anathema.cascades.module;

import javax.swing.Action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharmCascadesModulePresenter {

  public CharmCascadesModulePresenter(IAnathemaView view, IAnathemaModel model, IResources resources) {
    view.addTools(new Action[] { ShowCascadesAction.createToolAction(resources, model) });
  }
}