package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.utilities.ForCharacterType;

@DoNotInstantiateAutomatically
@ForCharacterType("Dummy")
public class DummyCharmPresentationProperties implements CharmPresentationProperties {
  @Override
  public boolean supportsCharacterType(CharacterType type) {
    return true;
  }

  @Override
  public Area getNodeDimension() {
    return new Area(0, 0);
  }

  @Override
  public Area getGapDimension() {
    return new Area(0, 0);
  }

  @Override
  public Area getVerticalLineDimension() {
    return new Area(0, 0);
  }

  @Override
  public RGBColor getColor() {
    return new RGBColor(0, 0, 0);
  }
}
