package net.sf.anathema.charm.description.presenter;

import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.detail.RegisteredCharmDetailPresenterFactory;
import net.sf.anathema.charm.description.model.AutoSaveCharmDescriptionEditModel;
import net.sf.anathema.charm.description.model.CharmDescriptionEditModel;
import net.sf.anathema.charm.description.module.CharmDescriptionItemTypeConfiguration;
import net.sf.anathema.charm.description.persistence.CharmDescriptionDataBase;
import net.sf.anathema.charm.description.persistence.RepositoryCharmDescriptionDataBase;
import net.sf.anathema.charm.description.view.CharmDescriptionEditView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.repository.IRepository;

import static net.sf.anathema.charm.description.module.CharmDescriptionItemTypeConfiguration.CHARM_DESCRIPTION_ITEM_TYPE_ID;

@RegisteredCharmDetailPresenterFactory
public class CharmDescriptionEditPresenterFactory implements CharmDetailPresenterFactory {

  @Override
  public CharmDetailPresenter create(IAnathemaModel anathemaModel) {
    final CharmDescriptionEditView view = new CharmDescriptionEditView();
    CharmDescriptionDataBase dataBase = createDataBase(anathemaModel);
    final CharmDescriptionEditModel model = new AutoSaveCharmDescriptionEditModel(dataBase);
    return new CharmDescriptionEditPresenter(view, model);
  }

  private RepositoryCharmDescriptionDataBase createDataBase(IAnathemaModel anathemaModel) {
    IRepository repository = anathemaModel.getRepository();
    IItemType itemType = getItemType(anathemaModel);
    return new RepositoryCharmDescriptionDataBase(repository, itemType);
  }

  private IItemType getItemType(IAnathemaModel anathemaModel) {
    IItemTypeRegistry registry = anathemaModel.getItemTypeRegistry();
    return registry.getById(CHARM_DESCRIPTION_ITEM_TYPE_ID);
  }
}
