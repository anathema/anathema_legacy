package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.charmtree.presenter.AbstractCharmDye;
import net.sf.anathema.charmtree.view.CharmGroupInformer;
import net.sf.anathema.charmtree.view.ICharmView;
import net.sf.anathema.framework.ui.RGBColor;

public class CharacterCharmDye extends AbstractCharmDye {

  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final RGBColor UNSELECTED_COLOR = RGBColor.White;
  private final RGBColor characterColor;
  private final CharacterCharmModel model;
  private final ICharmView view;

  public CharacterCharmDye(CharacterCharmModel model, CharmGroupInformer informer, RGBColor characterColor, ICharmView view) {
    super(informer);
    this.characterColor = characterColor;
    this.model = model;
    this.view = view;
  }

  @Override
  public void colorCharm(ICharm charm) {
    String id = charm.getId();
    RGBColor fillColor = getCharmConfiguration().isLearned(charm) ? characterColor : UNSELECTED_COLOR;
    int opacity = getCharmConfiguration().isLearnable(charm) ? MAXIMUM_OPACITY : REDUCED_OPACITY;
    view.setCharmVisuals(id, fillColor, opacity);
  }

  @Override
  protected void setPrerequisiteVisuals(IndirectCharmRequirement requirement) {
    String id = requirement.getStringRepresentation();
    boolean fulfilled = requirement.isFulfilled(getCharmConfiguration().getLearnedCharms(true));
    RGBColor fillColor = fulfilled ? characterColor.brighter() : UNSELECTED_COLOR;
    view.setCharmVisuals(id, fillColor, MAXIMUM_OPACITY);
  }

  private CharmsModel getCharmConfiguration() {
    return model.getCharmConfiguration();
  }
}