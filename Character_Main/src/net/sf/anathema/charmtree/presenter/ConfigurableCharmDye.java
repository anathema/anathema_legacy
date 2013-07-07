package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.presenter.magic.CharmColoring;
import net.sf.anathema.charmtree.view.CharmGroupInformer;

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

  public void colorCharm(ICharm charm) {
    coloring.colorCharm(charm);
  }

  public void setCharmVisuals() {
    if (!groupInformer.hasGroupSelected()) {
      return;
    }
    for (ICharm charm : getAllCharmsFromCurrentGroup()) {
      for (CharmColorer colorer : colorers) {
        colorer.color(charm);
      }
    }
  }

  private ICharm[] getAllCharmsFromCurrentGroup() {
    return groupInformer.getCurrentGroup().getAllCharms();
  }
}