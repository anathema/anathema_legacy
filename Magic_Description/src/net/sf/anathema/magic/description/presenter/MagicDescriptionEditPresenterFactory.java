package net.sf.anathema.magic.description.presenter;

import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.detail.RegisteredCharmDetailPresenterFactory;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.magic.description.model.AutoSaveMagicDescriptionEditModel;
import net.sf.anathema.magic.description.model.MagicDescriptionEditModel;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.magic.description.view.MagicDescriptionEditView;

@RegisteredCharmDetailPresenterFactory
public class MagicDescriptionEditPresenterFactory implements CharmDetailPresenterFactory {

  @Override
  public CharmDetailPresenter create(IAnathemaModel anathemaModel, IResources resources) {
    final MagicDescriptionEditView view = new MagicDescriptionEditView();
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(anathemaModel);
    final MagicDescriptionEditModel model = new AutoSaveMagicDescriptionEditModel(dataBase);
    return new MagicDescriptionEditPresenter(view, model, resources);
  }
}
