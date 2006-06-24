package net.sf.anathema.cascades.module;

import javax.swing.Action;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharmCascadesModule extends AbstractAnathemaModule {

  @Override
  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    super.initPresentation(resources, model, view);
    view.addTools(new Action[] { ShowCascadesAction.createToolAction(resources, model) });
  }
}