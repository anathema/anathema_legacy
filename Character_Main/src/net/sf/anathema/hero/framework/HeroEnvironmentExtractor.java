package net.sf.anathema.hero.framework;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.lib.registry.IRegistry;

public class HeroEnvironmentExtractor {

  public static HeroEnvironment getGenerics(IApplicationModel model) {
    IRegistry<String, IAnathemaExtension> registry = model.getExtensionPointRegistry();
    HeroEnvironmentExtension genericsExtension = (HeroEnvironmentExtension) registry.get(HeroEnvironmentExtension.ID);
    return genericsExtension.getEnvironment();
  }
}
