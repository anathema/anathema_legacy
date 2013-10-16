package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.platform.tree.display.TreeView;

public class CharacterColoringStrategy implements CharmColoring {

  private static final int MAXIMUM_OPACITY = 255;
  private static final int REDUCED_OPACITY = 70;
  private static final RGBColor UNSELECTED_COLOR = RGBColor.White;

  private final RGBColor characterColor;
  private final CharmDisplayModel model;
  private TreeView treeView;

  public CharacterColoringStrategy(RGBColor characterColor, CharmDisplayModel model) {
    this.characterColor = characterColor;
    this.model = model;
  }

  @Override
  public void colorCharm(Charm charm) {
    String id = charm.getId();
    RGBColor color = getCharmConfiguration().isLearned(charm) ? characterColor : UNSELECTED_COLOR;
    int opacity = getCharmConfiguration().isLearnable(charm) ? MAXIMUM_OPACITY : REDUCED_OPACITY;
    treeView.colorNode(id, new RGBColor(color, opacity));
  }

  @Override
  public void setPrerequisiteVisuals(IndirectCharmLearnPrerequisite prerequisite) {
    String id = prerequisite.getStringLabel();
    boolean fulfilled = prerequisite.isFulfilled(getCharmConfiguration());
    RGBColor color = fulfilled ? characterColor.brighter() : UNSELECTED_COLOR;
    treeView.colorNode(id, new RGBColor(color, MAXIMUM_OPACITY));
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }

  private CharmsModel getCharmConfiguration() {
    return model.getCharmModel();
  }
}