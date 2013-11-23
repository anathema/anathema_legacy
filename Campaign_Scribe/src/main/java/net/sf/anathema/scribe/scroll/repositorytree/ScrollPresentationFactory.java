package net.sf.anathema.scribe.scroll.repositorytree;


import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.initialization.ForItemType;

import static net.sf.anathema.scribe.scroll.ScrollItemType.SCROLL_ITEM_TYPE_ID;

@ForItemType(SCROLL_ITEM_TYPE_ID)
public class ScrollPresentationFactory implements ItemTypePresentationFactory {
  @Override
  public IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    return new ScrollPresentation();
  }
}
