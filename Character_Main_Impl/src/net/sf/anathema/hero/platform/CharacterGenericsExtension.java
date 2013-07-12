package net.sf.anathema.hero.platform;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtension;
import net.sf.anathema.hero.framework.HeroEnvironmentInitializer;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.DataFileProvider;

@Extension(id = HeroEnvironmentExtension.ID)
public class CharacterGenericsExtension implements HeroEnvironmentExtension, IAnathemaExtension {

  private HeroEnvironment environment;

  @Override
  public void initialize(DataFileProvider dataFileProvider, ObjectFactory instantiater, ResourceLoader loader) {
    HeroEnvironmentInitializer initializer = new HeroEnvironmentInitializer(loader, instantiater);
    this.environment = initializer.initEnvironmnent(dataFileProvider);
  }

  @Override
  public HeroEnvironment getEnvironment() {
    return environment;
  }
}