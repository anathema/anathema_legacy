package net.sf.anathema.magic.description.display;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Resources;

public interface MagicDetailPresenterFactory {

  MagicDetailPresenter create(IApplicationModel anathemaModel, Resources resources);
}
