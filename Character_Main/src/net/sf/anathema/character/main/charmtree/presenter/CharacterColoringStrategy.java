package net.sf.anathema.character.main.charmtree.presenter;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.main.presenter.magic.CharmColoring;
import net.sf.anathema.character.main.charmtree.view.ICharmView;
import net.sf.anathema.framework.ui.RGBColor;

public class CharacterColoringStrategy implements CharmColoring {


  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final RGBColor UNSELECTED_COLOR = RGBColor.White;

  private final RGBColor characterColor;
  private final ICharmView view;
  private final CharacterCharmModel model;

  public CharacterColoringStrategy(RGBColor characterColor, ICharmView view, CharacterCharmModel model) {
    this.characterColor = characterColor;
    this.view = view;
    this.model = model;
  }

  @Override
  public void colorCharm(ICharm charm) {
    String id = charm.getId();
    RGBColor fillColor = getCharmConfiguration().isLearned(charm) ? characterColor : UNSELECTED_COLOR;
    int opacity = getCharmConfiguration().isLearnable(charm) ? MAXIMUM_OPACITY : REDUCED_OPACITY;
    view.setCharmVisuals(id, fillColor, opacity);
  }

  @Override
  public void setPrerequisiteVisuals(IndirectCharmRequirement requirement) {
    String id = requirement.getStringRepresentation();
    boolean fulfilled = requirement.isFulfilled(getCharmConfiguration().getLearnedCharms(true));
    RGBColor fillColor = fulfilled ? characterColor.brighter() : UNSELECTED_COLOR;
    view.setCharmVisuals(id, fillColor, MAXIMUM_OPACITY);
  }

  private CharmsModel getCharmConfiguration() {
    return model.getCharmConfiguration();
  }
}