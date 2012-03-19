package net.sf.anathema.character.presenter.charm.detail;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public interface CharmDetailPresenterFactory {

  CharmDetailPresenter create(IAnathemaModel anathemaModel, IResources resources);
}
