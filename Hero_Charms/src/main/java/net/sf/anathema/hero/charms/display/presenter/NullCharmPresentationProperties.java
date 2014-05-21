package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;

@DoNotInstantiateAutomatically
public class NullCharmPresentationProperties extends AbstractCharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return true;
  }
}