package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.presenter.MutationsPresenter;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.lib.resources.IResources;

public class BeastformPresenter extends MutationsPresenter {

  public BeastformPresenter(IMutationsView view, IMutationsModel model, IResources resources) {
    super(view, model, resources);
  }

  @Override
  public void updateOverview() {
   getModel().updateOverview();
  }
}