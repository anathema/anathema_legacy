package net.sf.anathema.character.presenter.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.charmtree.view.CharmGroupInformer;

public class VisibilityPredicate implements Predicate<String> {

  private final CharmsModel charmConfiguration;
  private final CharmGroupInformer charmGroupInformer;

  public VisibilityPredicate(CharmsModel charmConfiguration, CharmGroupInformer informer) {
    this.charmConfiguration = charmConfiguration;
    this.charmGroupInformer = informer;
  }

  @Override
  public boolean apply(String charmId) {
    ICharm charm = charmConfiguration.getCharmById(charmId);
    return isVisible(charm);
  }

  private boolean isVisible(ICharm charm) {
    if (!charmGroupInformer.hasGroupSelected()) {
      return false;
    }
    ICharmGroup group = charmGroupInformer.getCurrentGroup();
    boolean isOfGroupType = charm.getCharacterType() == group.getCharacterType();
    return isOfGroupType && charm.getGroupId().equals(group.getId());
  }
}