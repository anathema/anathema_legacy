package net.sf.anathema.hero.platform;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtension;
import net.sf.anathema.hero.framework.HeroEnvironmentInitializer;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class CharacterGenericsExtension implements HeroEnvironmentExtension, AnathemaExtension {

  private HeroEnvironment environment;

  public void initialize(DataFileProvider dataFileProvider, ObjectFactory instantiater, ResourceLoader loader) {
    HeroEnvironmentInitializer initializer = new HeroEnvironmentInitializer(loader, instantiater);
    this.environment = initializer.initEnvironment(dataFileProvider);
  }

  @Override
  public HeroEnvironment getEnvironment() {
    return environment;
  }
}