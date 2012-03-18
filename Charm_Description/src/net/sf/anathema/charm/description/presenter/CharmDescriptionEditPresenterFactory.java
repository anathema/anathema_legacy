package net.sf.anathema.charm.description.presenter;

import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.detail.RegisteredCharmDetailPresenterFactory;
import net.sf.anathema.charm.description.model.AutoSaveCharmDescriptionEditModel;
import net.sf.anathema.charm.description.model.CharmDescriptionEditModel;
import net.sf.anathema.charm.description.persistence.CharmDescriptionDataBase;
import net.sf.anathema.charm.description.persistence.RepositoryCharmDescriptionDataBase;
import net.sf.anathema.charm.description.view.CharmDescriptionEditView;
import net.sf.anathema.framework.IAnathemaModel;

@RegisteredCharmDetailPresenterFactory
public class CharmDescriptionEditPresenterFactory implements CharmDetailPresenterFactory {

  @Override
  public CharmDetailPresenter create(IAnathemaModel anathemaModel) {
    final CharmDescriptionEditView view = new CharmDescriptionEditView();
    CharmDescriptionDataBase dataBase = RepositoryCharmDescriptionDataBase.CreateFrom(anathemaModel);
    final CharmDescriptionEditModel model = new AutoSaveCharmDescriptionEditModel(dataBase);
    return new CharmDescriptionEditPresenter(view, model);
  }
}
