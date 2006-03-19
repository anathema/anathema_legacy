package net.sf.anathema.cascades.module;

import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharmCascadesModule extends AbstractAnathemaModule {

  private AbstractItemTypeConfiguration charmCascadeConfiguration = new CharmCascadeItemTypeConfiguration();

  public CharmCascadesModule() {
    addItemTypeConfiguration(charmCascadeConfiguration);
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaView view) {
    super.initPresentation(resources, view);
    new CharmCascadesModulePresenter(view, getAnathemaModel(), resources);
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("Cascades", resources.getLocale())); //$NON-NLS-1$    
    resources.addStringResourceHandler(createStringProvider("Charms_DragonKing", resources.getLocale())); //$NON-NLS-1$
  }
}