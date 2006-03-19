package net.sf.anathema.namegenerator.anathema;

import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;

public class NameGeneratorModule extends AbstractAnathemaModule {

  private AbstractItemTypeConfiguration nameGeneratorConfiguration = new NameGeneratorItemTypeConfiguration();

  public NameGeneratorModule() {
    if (AnathemaEnvironment.isDevelopment()) {
      addItemTypeConfiguration(nameGeneratorConfiguration);
    }
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("NameGenerator", resources.getLocale())); //$NON-NLS-1$    
  }
}