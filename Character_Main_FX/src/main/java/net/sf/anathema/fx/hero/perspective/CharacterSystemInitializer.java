package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.platform.CharacterGenericsExtension;

public class CharacterSystemInitializer {
  private final IApplicationModel model;
  private final Environment environment;

  public CharacterSystemInitializer(IApplicationModel model, Environment environment) {
    this.model = model;
    this.environment = environment;
  }

  public void initializeCharacterSystem() {
      CharacterGenericsExtension extension = new CharacterGenericsExtension();
      extension.initialize(model.getRepository(), environment, environment);
      model.getExtensionPointRegistry().register(CharacterGenericsExtension.ID, extension);
  }
}