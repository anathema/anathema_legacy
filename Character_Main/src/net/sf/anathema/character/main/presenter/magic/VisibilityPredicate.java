package net.sf.anathema.character.main.presenter.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.CharmIdMap;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.magic.charmtree.view.CharmGroupInformer;

public class VisibilityPredicate implements Predicate<String> {

  private final CharmIdMap charmMap;
  private final CharmGroupInformer charmGroupInformer;

  public VisibilityPredicate(CharmIdMap charmMap, CharmGroupInformer informer) {
    this.charmMap = charmMap;
    this.charmGroupInformer = informer;
  }

  @Override
  public boolean apply(String charmId) {
    ICharm charm = charmMap.getCharmById(charmId);
    return isVisible(charm);
  }

  private boolean isVisible(ICharm charm) {
    if (!charmGroupInformer.hasGroupSelected()) {
      return false;
    }
    ICharmGroup group = charmGroupInformer.getCurrentGroup();
    return group.isCharmFromGroup(charm);
  }
}