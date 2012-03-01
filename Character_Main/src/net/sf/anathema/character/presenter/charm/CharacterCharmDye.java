package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.CharmDye;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;
import net.sf.anathema.charmtree.presenter.view.ICharmView;

import java.awt.*;

public class CharacterCharmDye implements CharmDye{

  private final CharmGroupInformer informer;
  private final Color characterColor;
  private final CharacterCharmModel model;
  private final ICharmView view;

  public CharacterCharmDye(CharacterCharmModel model, CharmGroupInformer informer, Color characterColor, ICharmView view) {
    this.informer = informer;
    this.characterColor = characterColor;
    this.model = model;
    this.view = view;
  }

  public void setCharmVisuals(ICharm charm) {
    ICharmConfiguration charmConfiguration = model.getCharmConfiguration();
    ICharmGroup selectedGroup = informer.getCurrentGroup();
    if (selectedGroup == null || !charm.getGroupId().equals(selectedGroup.getId())) {
      return;
    }
    Color fillColor = charmConfiguration.isLearned(charm) ? characterColor : Color.WHITE;
    int opacity = charmConfiguration.isLearnable(charm) ? 255 : 70;
    view.setCharmVisuals(charm.getId(), fillColor, opacity);
  }
}