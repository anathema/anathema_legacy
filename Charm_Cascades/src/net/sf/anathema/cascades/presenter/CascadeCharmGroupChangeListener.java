package net.sf.anathema.cascades.presenter;

import java.awt.Color;
import java.awt.SystemColor;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class CascadeCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  private final ICascadeView cascadeView;
  private final CascadeCharmTreeViewProperties viewProperties;
  private final ITemplateRegistry templateRegistry;
  private final ICascadePresenter presenter;
  private IExaltedEdition edition = ExaltedEdition.FirstEdition;

  public CascadeCharmGroupChangeListener(
      ICascadeView cascadeView,
      CascadeCharmTreeViewProperties viewProperties,
      ICascadePresenter presenter,
      ITemplateRegistry templateRegistry) {
    super(cascadeView.getCharmTreeView(), templateRegistry, new FriendlyCharmGroupArbitrator());
    this.cascadeView = cascadeView;
    this.viewProperties = viewProperties;
    this.presenter = presenter;
    this.templateRegistry = templateRegistry;
  }

  @Override
  protected IExaltedEdition getEdition() {
    return edition;
  }

  @Override
  protected final void modifyCharmVisuals(IIdentificate type) {
    viewProperties.setCharmTree(presenter.getCharmTree(type));
    if (type instanceof CharacterType) {
      IPresentationProperties presentationProperties = templateRegistry.getDefaultTemplate(
          (CharacterType) type,
          getEdition()).getPresentationProperties();
      cascadeView.setBackgroundColor(presentationProperties.getColor());
    }
    else {
      cascadeView.setBackgroundColor(SystemColor.controlHighlight);
    }
  }

  @Override
  public void updateColors() {
    for (ICharm charm : getCurrentGroup().getAllCharms()) {
      cascadeView.setCharmVisuals(charm.getId(), Color.WHITE);
    }
  }

  public void setEdition(IExaltedEdition edition) {
    this.edition = edition;
  }
}