package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public interface MagicDetailPresenterFactory {

  MagicDetailPresenter create(IAnathemaModel anathemaModel, IResources resources);
}
