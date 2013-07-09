package net.sf.anathema.hero.charms.display.special;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupInformer;

public class VisibilityPredicate implements Predicate<String> {

  private final CharmIdMap charmMap;
  private final CharmGroupInformer charmGroupInformer;

  public VisibilityPredicate(CharmIdMap charmMap, CharmGroupInformer informer) {
    this.charmMap = charmMap;
    this.charmGroupInformer = informer;
  }

  @Override
  public boolean apply(String charmId) {
    Charm charm = charmMap.getCharmById(charmId);
    return isVisible(charm);
  }

  private boolean isVisible(Charm charm) {
    if (!charmGroupInformer.hasGroupSelected()) {
      return false;
    }
    ICharmGroup group = charmGroupInformer.getCurrentGroup();
    return group.isCharmFromGroup(charm);
  }
}