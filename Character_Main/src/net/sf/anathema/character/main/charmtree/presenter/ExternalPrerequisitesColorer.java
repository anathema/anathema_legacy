package net.sf.anathema.character.main.charmtree.presenter;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.presenter.magic.CharmColoring;
import net.sf.anathema.character.main.charmtree.view.CharmGroupInformer;

public class ExternalPrerequisitesColorer implements CharmColorer{
  private final CharmGroupInformer groupInformer;
  private final CharmColoring coloring;

  public ExternalPrerequisitesColorer(CharmGroupInformer groupInformer, CharmColoring coloring) {
    this.groupInformer = groupInformer;
    this.coloring = coloring;
  }

  public void color(ICharm charm) {
    for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (isPartOfCurrentGroup(prerequisite)) {
        return;
      }
      coloring.colorCharm(prerequisite);

    }
  }

  private boolean isPartOfCurrentGroup(ICharm charm) {
    return groupInformer.getCurrentGroup().isCharmFromGroup(charm);
  }
}