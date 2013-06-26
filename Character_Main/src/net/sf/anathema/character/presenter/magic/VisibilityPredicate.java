package net.sf.anathema.character.presenter.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;

public class VisibilityPredicate implements Predicate<String> {

  private final ICharmConfiguration charmConfiguration;
  private final CharmGroupInformer charmGroupInformer;

  public VisibilityPredicate(ICharmConfiguration charmConfiguration, CharmGroupInformer informer) {
    this.charmConfiguration = charmConfiguration;
    this.charmGroupInformer = informer;
  }

  @Override
  public boolean apply(String charmId) {
    ICharm charm = charmConfiguration.getCharmById(charmId);
    return isVisible(charm);
  }

  //TODO (#343) CharacterType Identity => Equality
  private boolean isVisible(ICharm charm) {
    if (!charmGroupInformer.hasGroupSelected()) {
      return false;
    }
    ICharmGroup group = charmGroupInformer.getCurrentGroup();
    boolean isOfGroupType = charm.getCharacterType() == group.getCharacterType();
    return isOfGroupType && charm.getGroupId().equals(group.getId());
  }
}