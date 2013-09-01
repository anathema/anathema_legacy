package net.sf.anathema.framework.module;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.environment.Resources;

public interface ItemTypePresentationFactory {

  IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources);
}
