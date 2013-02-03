package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.SpecialCharmViewPresenter;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;

import javax.swing.ToolTipManager;

public class CharacterSpecialCharmPresenter implements SpecialCharmViewPresenter {
  private final SpecialCharmList list;
  private final CharmGroupInformer charmGroupInformer;
  private final CharacterCharmModel charmModel;

  public CharacterSpecialCharmPresenter(CharmGroupInformer informer, CharacterCharmModel charmModel,
                                        SpecialCharmList specialCharmList) {
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
  public void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea() {
    list.hideAllViews();
    ToolTipManager.sharedInstance().setEnabled(false);
    ToolTipManager.sharedInstance().setEnabled(true);
  }

  @Override
  public void showSpecialViews() {
    if (!charmGroupInformer.hasGroupSelected()) {
      return;
    }
    list.showViews();
  }

  private ICharmConfiguration getCharmConfiguration() {
    return charmModel.getCharmConfiguration();
  }
}