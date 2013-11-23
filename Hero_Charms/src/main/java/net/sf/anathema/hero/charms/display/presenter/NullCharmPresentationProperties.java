package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
public class NullCharmPresentationProperties extends AbstractCharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return true;
  }
}