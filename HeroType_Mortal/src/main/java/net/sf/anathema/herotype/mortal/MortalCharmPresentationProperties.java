package net.sf.anathema.herotype.mortal;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmPresentationProperties;
import net.sf.anathema.hero.charms.display.presenter.RegisteredCharmPresentationProperties;

@RegisteredCharmPresentationProperties
public class MortalCharmPresentationProperties extends AbstractCharmPresentationProperties {

  public MortalCharmPresentationProperties() {
    super(new RGBColor(177,177,253));
  }

  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return type instanceof MortalCharacterType;
  }
}
