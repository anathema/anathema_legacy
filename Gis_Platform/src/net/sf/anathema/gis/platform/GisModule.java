package net.sf.anathema.gis.platform;

import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;

public class GisModule extends AbstractAnathemaModule {

  private AbstractItemTypeConfiguration gisItemConfiguration = new GisItemTypeConfiguration();

  public GisModule() {
    addItemTypeConfiguration(gisItemConfiguration);
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    //resources.addStringResourceHandler(createStringProvider("Gis", resources.getLocale())); //$NON-NLS-1$    
  }
}