package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.TreeRenderer;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final CascadeSpecialCharmSet specialCharmSet;
  private TreeView treeView;

  public CascadeCharmGroupChangeListener(TreeRenderer treeRenderer, CascadeSpecialCharmSet specialCharmSet,
                                         CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    super(new FriendlyCharmGroupArbitrator(), treeRenderer, charmDisplayPropertiesMap);
    this.specialCharmSet = specialCharmSet;
  }

  @Override
  protected final void modifyCharmVisuals(Identifier type) {
    specialCharmSet.setType(type);
    RGBColor color = findColor(type);
    treeView.setCanvasBackground(color);
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
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