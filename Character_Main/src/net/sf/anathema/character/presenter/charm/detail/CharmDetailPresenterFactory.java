package net.sf.anathema.character.presenter.charm.detail;

import net.sf.anathema.framework.IAnathemaModel;

public interface CharmDetailPresenterFactory {

  CharmDetailPresenter create(IAnathemaModel anathemaModel);
}
