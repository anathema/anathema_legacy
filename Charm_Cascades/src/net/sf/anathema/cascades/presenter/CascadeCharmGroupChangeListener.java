package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.charmtree.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.character.main.charmtree.view.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final ICascadeView cascadeView;
  private final CascadeCharmTreeViewProperties viewProperties;

  public CascadeCharmGroupChangeListener(ICascadeView cascadeView, CascadeCharmTreeViewProperties viewProperties, CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
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
    if (type instanceof ICharacterType) {
      ITreePresentationProperties displayProperties = getDisplayProperties((ICharacterType) type);
      return displayProperties.getColor();
    } else {
      return RGBColor.White;
    }
  }
}