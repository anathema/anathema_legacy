package net.sf.anathema.character.db;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.AbstractCharmPresentationProperties;
import net.sf.anathema.hero.charms.display.RegisteredCharmPresentationProperties;

@RegisteredCharmPresentationProperties
public class DbCharmPresentationProperties extends AbstractCharmPresentationProperties {

  public DbCharmPresentationProperties() {
    super(new RGBColor(139, 0, 0));
  }

  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return type instanceof DbCharacterType;
  }
}
