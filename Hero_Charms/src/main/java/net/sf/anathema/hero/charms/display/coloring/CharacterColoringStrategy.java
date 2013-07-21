package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.model.CharmsModel;

public class CharacterColoringStrategy implements CharmColoring {


  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final RGBColor UNSELECTED_COLOR = RGBColor.White;

  private final RGBColor characterColor;
  private final CharmView view;
  private final CharmDisplayModel model;

  public CharacterColoringStrategy(RGBColor characterColor, CharmView view, CharmDisplayModel model) {
    this.characterColor = characterColor;
    this.view = view;
    this.model = model;
  }

  @Override
  public void colorCharm(Charm charm) {
    String id = charm.getId();
    RGBColor color = getCharmConfiguration().isLearned(charm) ? characterColor : UNSELECTED_COLOR;
    int opacity = getCharmConfiguration().isLearnable(charm) ? MAXIMUM_OPACITY : REDUCED_OPACITY;
    view.colorNode(id, new RGBColor(color, opacity));
  }

  @Override
  public void setPrerequisiteVisuals(IndirectCharmRequirement requirement) {
    String id = requirement.getStringRepresentation();
    boolean fulfilled = requirement.isFulfilled(getCharmConfiguration().getLearnedCharms(true));
    RGBColor color = fulfilled ? characterColor.brighter() : UNSELECTED_COLOR;
    view.colorNode(id, new RGBColor(color, MAXIMUM_OPACITY));
  }

  private CharmsModel getCharmConfiguration() {
    return model.getCharmModel();
  }
}