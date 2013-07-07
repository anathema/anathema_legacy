package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import net.sf.anathema.hero.magic.display.coloring.CharmColoring;
import net.sf.anathema.framework.ui.RGBColor;

public class CascadeColoringStrategy implements CharmColoring {
  private ICascadeView view;

  public CascadeColoringStrategy(ICascadeView view) {
    this.view = view;
  }

  @Override
  public void colorCharm(ICharm charm) {
    view.setCharmVisuals(charm.getId(), RGBColor.White);
  }

  @Override
  public void setPrerequisiteVisuals(IndirectCharmRequirement requirement) {
    view.setCharmVisuals(requirement.getStringRepresentation(), RGBColor.White);
  }
}
