package net.sf.anathema.magic.description.presenter;

import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenterFactory;
import net.sf.anathema.character.presenter.magic.detail.RegisteredMagicDetailPresenterFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.magic.description.model.AutoSaveMagicDescriptionEditModel;
import net.sf.anathema.magic.description.model.MagicDescriptionEditModel;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.magic.description.view.MagicDescriptionEditView;

@RegisteredMagicDetailPresenterFactory
public class MagicDescriptionEditPresenterFactory implements MagicDetailPresenterFactory {

  @Override
  public MagicDetailPresenter create(IApplicationModel anathemaModel, IResources resources) {
    MagicDescriptionEditView view = new MagicDescriptionEditView();
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(anathemaModel);
    MagicDescriptionEditModel model = new AutoSaveMagicDescriptionEditModel(dataBase);
    return new MagicDescriptionEditPresenter(view, model, resources);
  }
}
