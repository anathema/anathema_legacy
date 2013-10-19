package net.sf.anathema.scribe.scroll;


import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.initialization.RegisteredItemTypePresentation;
import net.sf.anathema.scribe.scroll.persistence.RepositoryScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.SystemClock;

@RegisteredItemTypePresentation(itemType = ScrollItemType.SCROLL_ITEM_TYPE_ID)
public class ScrollPresentationFactory implements ItemTypePresentationFactory {
  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    IRepositoryFileResolver fileResolver = anathemaModel.getRepository().getRepositoryFileResolver();
    RepositoryScrollPersister persister = new RepositoryScrollPersister(anathemaModel.getRepository(), new SystemClock());
    return new ScrollPresentation(resources, persister);
  }
}
