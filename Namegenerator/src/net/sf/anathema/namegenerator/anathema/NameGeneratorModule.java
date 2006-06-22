package net.sf.anathema.namegenerator.anathema;

import net.sf.anathema.framework.environment.AnathemaEnvironment;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;

public class NameGeneratorModule extends AbstractAnathemaModule {

  private AbstractItemTypeConfiguration nameGeneratorConfiguration = new NameGeneratorItemTypeConfiguration();

  public NameGeneratorModule() {
    if (AnathemaEnvironment.isDevelopment()) {
      addItemTypeConfiguration(nameGeneratorConfiguration);
    }
  }
}