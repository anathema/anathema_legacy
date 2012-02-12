package net.sf.anathema.cascades.presenter;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.List;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final ICascadeView cascadeView;
  private final CascadeCharmTreeViewProperties viewProperties;
  private final ITemplateRegistry templateRegistry;

  public CascadeCharmGroupChangeListener(
          ICascadeView cascadeView,
          CascadeCharmTreeViewProperties viewProperties,
          ITemplateRegistry templateRegistry,
          List<ICharmFilter> charmFilterSet) {
    super(templateRegistry, new FriendlyCharmGroupArbitrator(), charmFilterSet, ExaltedEdition.FirstEdition, cascadeView.getCharmTreeRenderer());
    this.cascadeView = cascadeView;
    this.viewProperties = viewProperties;
    this.templateRegistry = templateRegistry;
  }

  @Override
  protected final void modifyCharmVisuals(IIdentificate type) {
    viewProperties.setCharmType(type);
    Color color;
    if (type instanceof ICharacterType) {
      color = templateRegistry.getDefaultTemplate((ICharacterType) type, getEdition())
              .getPresentationProperties()
              .getColor();
    } else {
      color = SystemColor.controlHighlight;
    }
    cascadeView.setBackgroundColor(color);
  }
}