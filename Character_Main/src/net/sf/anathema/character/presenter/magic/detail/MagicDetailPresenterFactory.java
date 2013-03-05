package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.IResources;

public interface MagicDetailPresenterFactory {

  MagicDetailPresenter create(IApplicationModel anathemaModel, IResources resources);
}
