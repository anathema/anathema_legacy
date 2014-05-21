package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.platform.tree.display.TreeView;

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

  @Override
  public void operateOn(TreeView treeView) {
    coloring.operateOn(treeView);
  }

  private Charm[] getAllCharmsFromCurrentGroup() {
    return groupInformer.getCurrentGroup().getAllCharms();
  }
}