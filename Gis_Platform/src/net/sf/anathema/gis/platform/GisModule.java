package net.sf.anathema.gis.platform;

import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;

public class GisModule extends AbstractAnathemaModule {

  private AbstractItemTypeConfiguration mapConfiguration = new GisItemTypeConfiguration();

  public GisModule() {
    addItemTypeConfiguration(mapConfiguration);
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("CharmEntry", resources.getLocale())); //$NON-NLS-1$    
  }
}