package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupInformer;
import net.sf.anathema.character.main.magic.model.charm.Charm;

import java.util.ArrayList;
import java.util.List;

public class ConfigurableCharmDye implements CharmDye {

  private final CharmGroupInformer groupInformer;
  private final CharmColoring coloring;
  private final List<CharmColorer> colorers = new ArrayList<>();

  public ConfigurableCharmDye(CharmGroupInformer informer, CharmColoring coloring) {
    this.groupInformer = informer;
    this.coloring = coloring;
    colorers.add(new SimpleCharmColorer(coloring));
    colorers.add(new ExternalPrerequisitesColorer(groupInformer, coloring));
    colorers.add(new NonCharmPrerequisitesColorer(coloring));
  }

  public void colorCharm(Charm charm) {
    coloring.colorCharm(charm);
  }

  public void setCharmVisuals() {
    if (!groupInformer.hasGroupSelected()) {
      return;
    }
    for (Charm charm : getAllCharmsFromCurrentGroup()) {
      for (CharmColorer colorer : colorers) {
        colorer.color(charm);
      }
    }
  }

  private Charm[] getAllCharmsFromCurrentGroup() {
    return groupInformer.getCurrentGroup().getAllCharms();
  }
}