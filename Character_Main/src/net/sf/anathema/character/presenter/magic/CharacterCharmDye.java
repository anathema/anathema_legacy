package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.AbstractCharmDye;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;
import net.sf.anathema.charmtree.presenter.view.ICharmView;

import java.awt.Color;

public class CharacterCharmDye extends AbstractCharmDye {

  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final Color UNSELECTED_COLOR = Color.WHITE;
  private final Color characterColor;
  private final CharacterCharmModel model;
  private final ICharmView view;

  public CharacterCharmDye(CharacterCharmModel model, CharmGroupInformer informer, Color characterColor,
                           ICharmView view) {
    super(informer);
    this.characterColor = characterColor;
    this.model = model;
    this.view = view;
  }

  @Override
  public void colorCharm(ICharm charm) {
    String id = charm.getId();
    Color fillColor = getCharmConfiguration().isLearned(charm) ? characterColor : UNSELECTED_COLOR;
    int opacity = getCharmConfiguration().isLearnable(charm) ? MAXIMUM_OPACITY : REDUCED_OPACITY;
    view.setCharmVisuals(id, fillColor, opacity);
  }

  @Override
  protected void setPrerequisiteVisuals(ICharmAttributeRequirement requirement) {
    String id = requirement.getStringRepresentation();
    boolean fulfilled = requirement.isFulfilled(getCharmConfiguration().getLearnedCharms(true));
    Color fillColor = fulfilled ? characterColor.brighter() : UNSELECTED_COLOR;
    view.setCharmVisuals(id, fillColor, MAXIMUM_OPACITY);
  }

  private ICharmConfiguration getCharmConfiguration() {
    return model.getCharmConfiguration();
  }
}