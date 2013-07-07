package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.charmtree.presenter.SpecialCharmViewPresenter;
import net.sf.anathema.charmtree.view.CharmGroupInformer;

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