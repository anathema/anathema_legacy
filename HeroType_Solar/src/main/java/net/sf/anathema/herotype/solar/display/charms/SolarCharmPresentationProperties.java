package net.sf.anathema.herotype.solar.display.charms;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.AbstractCharmPresentationProperties;
import net.sf.anathema.hero.charms.display.presenter.RegisteredCharmPresentationProperties;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;

@RegisteredCharmPresentationProperties
public class SolarCharmPresentationProperties extends AbstractCharmPresentationProperties {

  public SolarCharmPresentationProperties() {
    super(new RGBColor(247, 234, 130));
  }

  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return type instanceof SolarCharacterType;
  }
}
