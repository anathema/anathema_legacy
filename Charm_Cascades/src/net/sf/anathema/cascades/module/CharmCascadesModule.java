package net.sf.anathema.cascades.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharmCascadesModule extends AbstractAnathemaModule {

  private AbstractItemTypeConfiguration charmCascadeConfiguration = new CharmCascadeItemTypeConfiguration();

  public CharmCascadesModule() {
    addItemTypeConfiguration(charmCascadeConfiguration);
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    super.initPresentation(resources, model, view);
    new CharmCascadesModulePresenter(view, getAnathemaModel(), resources);
  }
}