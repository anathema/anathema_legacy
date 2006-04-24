package net.sf.anathema.gis.platform;

import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.gis.platform.menu.GisModuleMenuFactory;
import net.sf.anathema.lib.resources.IResources;

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
  
  @Override
  public void initPresentation(IResources resources, IAnathemaView view) {
    super.initPresentation(resources, view);
    view.getMenuBar().addMenu(new GisModuleMenuFactory().createMapMenu(resources, getAnathemaModel()));
  }
}