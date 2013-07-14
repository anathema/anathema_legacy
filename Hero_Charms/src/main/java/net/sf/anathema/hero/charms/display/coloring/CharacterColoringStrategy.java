package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.model.CharacterCharmModel;
import net.sf.anathema.hero.charms.model.CharmsModel;

public class CharacterColoringStrategy implements CharmColoring {


  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final RGBColor UNSELECTED_COLOR = RGBColor.White;

  private final RGBColor characterColor;
  private final CharmView view;
  private final CharacterCharmModel model;

  public CharacterColoringStrategy(RGBColor characterColor, CharmView view, CharacterCharmModel model) {
    this.characterColor = characterColor;
    this.view = view;
    this.model = model;
  }

  @Override
  public void colorCharm(Charm charm) {
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