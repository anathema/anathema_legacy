package net.sf.anathema.scribe.scroll.repositorytree;


import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.initialization.RegisteredItemTypePresentation;
import net.sf.anathema.scribe.scroll.ScrollItemType;

@RegisteredItemTypePresentation(itemType = ScrollItemType.SCROLL_ITEM_TYPE_ID)
public class ScrollPresentationFactory implements ItemTypePresentationFactory {
  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    return new ScrollPresentation();
  }
}
