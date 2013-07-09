package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.CharacterCharmModel;
import net.sf.anathema.hero.charms.model.special.SpecialCharmList;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupInformer;

public class CharacterSpecialCharmPresenter implements SpecialCharmViewPresenter {
  private final SpecialCharmList list;
  private final CharmGroupInformer charmGroupInformer;
  private final CharacterCharmModel charmModel;

  public CharacterSpecialCharmPresenter(CharmGroupInformer informer, CharacterCharmModel charmModel, SpecialCharmList specialCharmList) {
    this.charmGroupInformer = informer;
    this.charmModel = charmModel;
    this.list = specialCharmList;
    VisibilityPredicate predicate = new VisibilityPredicate(charmModel.getCharmConfiguration(), informer);
    list.setVisibilityPredicate(predicate);
  }

  @Override
  public void initPresentation() {
    for (ISpecialCharm charm : getCharmConfiguration().getSpecialCharms()) {
      list.add(charm);
    }
  }

  @Override
  public void showSpecialViews() {
    if (!charmGroupInformer.hasGroupSelected()) {
      return;
    }
    list.showViews();
  }

  private CharmsModel getCharmConfiguration() {
    return charmModel.getCharmConfiguration();
  }
}