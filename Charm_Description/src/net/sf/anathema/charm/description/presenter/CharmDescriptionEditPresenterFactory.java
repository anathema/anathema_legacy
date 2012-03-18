package net.sf.anathema.charm.description.presenter;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.detail.RegisteredCharmDetailPresenterFactory;
import net.sf.anathema.charm.description.model.AutoSaveCharmDescriptionEditModel;
import net.sf.anathema.charm.description.model.CharmDescriptionEditModel;
import net.sf.anathema.charm.description.persistence.CharmDescriptionDataBase;
import net.sf.anathema.charm.description.persistence.InMemoryCharmDescriptionDataBase2;
import net.sf.anathema.charm.description.view.CharmDescriptionEditView;

@RegisteredCharmDetailPresenterFactory
public class CharmDescriptionEditPresenterFactory implements CharmDetailPresenterFactory {

  @Override
  public CharmDetailPresenter create(ICharacterGenerics generics) {
    final CharmDescriptionEditView view = new CharmDescriptionEditView();
    CharmDescriptionDataBase dataBase = new InMemoryCharmDescriptionDataBase2();// new MongoCharmDescriptionDataBase();
    final CharmDescriptionEditModel model = new AutoSaveCharmDescriptionEditModel(dataBase);
    return new CharmDescriptionEditPresenter(view, model);
  }
}
