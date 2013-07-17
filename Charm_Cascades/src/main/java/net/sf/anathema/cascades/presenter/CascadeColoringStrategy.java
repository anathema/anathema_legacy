package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.hero.charms.display.coloring.CharmColoring;

public class CascadeColoringStrategy implements CharmColoring {
  private CascadeView view;

  public CascadeColoringStrategy(CascadeView view) {
    this.view = view;
  }

  @Override
  public void colorCharm(Charm charm) {
    view.setCharmVisuals(charm.getId(), RGBColor.White);
  }

  @Override
  public void setPrerequisiteVisuals(IndirectCharmRequirement requirement) {
    view.setCharmVisuals(requirement.getStringRepresentation(), RGBColor.White);
  }
}
