package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.CharmFilterSet;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import java.awt.Color;
import java.awt.SystemColor;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final ICascadeView cascadeView;
  private final CascadeCharmTreeViewProperties viewProperties;

  public CascadeCharmGroupChangeListener(ICascadeView cascadeView, CascadeCharmTreeViewProperties viewProperties,
                                         CharmFilterSet charmFilterSet,
                                         CharmDisplayPropertiesMap charmDisplayPropertiesMap) {
    super(new FriendlyCharmGroupArbitrator(), charmFilterSet, cascadeView.getCharmTreeRenderer(),
            charmDisplayPropertiesMap);
    this.cascadeView = cascadeView;
    this.viewProperties = viewProperties;
  }

  @Override
  protected final void modifyCharmVisuals(IIdentificate type) {
    viewProperties.setCharmType(type);
    Color color = findColor(type);
    cascadeView.setBackgroundColor(color);
  }

  private Color findColor(IIdentificate type) {
    if (type instanceof ICharacterType) {
      ITreePresentationProperties displayProperties = getDisplayProperties((ICharacterType) type);
      return displayProperties.getColor();
    } else {
      return SystemColor.controlHighlight;
    }
  }
}