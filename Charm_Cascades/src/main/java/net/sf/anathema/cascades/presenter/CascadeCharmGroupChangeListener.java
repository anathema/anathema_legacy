package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final CascadeSpecialCharmSet specialCharmSet;

  public CascadeCharmGroupChangeListener(CascadeSpecialCharmSet specialCharmSet,
                                         CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    super(new FriendlyCharmGroupArbitrator(), charmDisplayPropertiesMap);
    this.specialCharmSet = specialCharmSet;
  }

  @Override
  protected final void modifyCharmVisuals(Identifier type) {
    specialCharmSet.setType(type);
    RGBColor color = findColor(type);
    getTreeView().setCanvasBackground(color);
  }

  private RGBColor findColor(Identifier type) {
    if (type instanceof CharacterType) {
      TreePresentationProperties displayProperties = getDisplayProperties((CharacterType) type);
      return displayProperties.getColor();
    } else {
      return RGBColor.White;
    }
  }
}