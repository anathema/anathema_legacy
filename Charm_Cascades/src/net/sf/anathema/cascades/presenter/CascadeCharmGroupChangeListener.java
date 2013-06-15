package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.CharmFilterSet;
import net.sf.anathema.charmtree.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.view.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.tree.util.RGBColor;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final ICascadeView cascadeView;
  private final CascadeCharmTreeViewProperties viewProperties;

  public CascadeCharmGroupChangeListener(ICascadeView cascadeView, CascadeCharmTreeViewProperties viewProperties, CharmFilterSet charmFilterSet,
                                         CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    super(new FriendlyCharmGroupArbitrator(), charmFilterSet, cascadeView.getCharmTreeRenderer(), charmDisplayPropertiesMap);
    this.cascadeView = cascadeView;
    this.viewProperties = viewProperties;
  }

  @Override
  protected final void modifyCharmVisuals(Identified type) {
    viewProperties.setCharmType(type);
    RGBColor color = findColor(type);
    cascadeView.setBackgroundColor(color);
  }

  private RGBColor findColor(Identified type) {
    if (type instanceof ICharacterType) {
      ITreePresentationProperties displayProperties = getDisplayProperties((ICharacterType) type);
      return displayProperties.getColor();
    } else {
      return RGBColor.White;
    }
  }
}