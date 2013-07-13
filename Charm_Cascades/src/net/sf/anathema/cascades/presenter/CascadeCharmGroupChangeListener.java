package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.magic.display.view.charmtree.AbstractCharmGroupChangeListener;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final CascadeView cascadeView;
  private final CascadeCharmTreeViewProperties viewProperties;

  public CascadeCharmGroupChangeListener(CascadeView cascadeView, CascadeCharmTreeViewProperties viewProperties, CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    super(new FriendlyCharmGroupArbitrator(), cascadeView.getCharmTreeRenderer(), charmDisplayPropertiesMap);
    this.cascadeView = cascadeView;
    this.viewProperties = viewProperties;
  }

  @Override
  protected final void modifyCharmVisuals(Identifier type) {
    viewProperties.setCharmType(type);
    RGBColor color = findColor(type);
    cascadeView.setBackgroundColor(color);
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