package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.AbstractCharmDye;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;
import net.sf.anathema.charmtree.presenter.view.ICharmView;

import java.awt.Color;

public class CharacterCharmDye extends AbstractCharmDye {

  private final CharmGroupInformer informer;
  private final Color characterColor;
  private final CharacterCharmModel model;
  private final ICharmView view;

  public CharacterCharmDye(CharacterCharmModel model, CharmGroupInformer informer, Color characterColor,
                           ICharmView view) {
    super(informer);
    this.informer = informer;
    this.characterColor = characterColor;
    this.model = model;
    this.view = view;
  }

  @Override
  public void setCharmVisuals(ICharm charm) {
    ICharmConfiguration charmConfiguration = model.getCharmConfiguration();
    if (!informer.hasGroupSelected() || isNotPartOfCurrentGroup(charm)) {
      return;
    }
    Color fillColor = charmConfiguration.isLearned(charm) ? characterColor : Color.WHITE;
    int opacity = charmConfiguration.isLearnable(charm) ? 255 : 70;
    view.setCharmVisuals(charm.getId(), fillColor, opacity);
  }

  private boolean isNotPartOfCurrentGroup(ICharm charm) {
    return !isPartOfCurrentGroup(charm);
  }

  private boolean isPartOfCurrentGroup(ICharm charm) {
    return charm.getGroupId().equals(informer.getCurrentGroup().getId());
  }
}