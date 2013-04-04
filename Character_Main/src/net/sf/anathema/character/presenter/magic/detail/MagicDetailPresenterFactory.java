package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

public interface MagicDetailPresenterFactory {

  MagicDetailPresenter create(IApplicationModel anathemaModel, Resources resources);
}
