package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupInformer;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.SpecialCharmList;
import net.sf.anathema.platform.tree.display.TreeView;

public class CharacterSpecialCharmPresenter implements SpecialCharmViewPresenter {
  private final SpecialCharmList list;
  private final CharmGroupInformer charmGroupInformer;
  private final CharmDisplayModel charmModel;

  public CharacterSpecialCharmPresenter(CharmGroupInformer informer, CharmDisplayModel charmModel, SpecialCharmList specialCharmList) {
    this.charmGroupInformer = informer;
    this.charmModel = charmModel;
    this.list = specialCharmList;
    VisibilityPredicate predicate = new VisibilityPredicate(charmModel.getCharmModel(), informer);
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

  @Override
  public void operateOn(TreeView treeView) {
    list.operateOn(treeView);
  }

  private CharmsModel getCharmConfiguration() {
    return charmModel.getCharmModel();
  }
}