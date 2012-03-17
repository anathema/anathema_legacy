package net.sf.anathema.charm.description.presenter;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenterFactory;
import net.sf.anathema.character.presenter.charm.detail.RegisteredCharmDetailPresenterFactory;

@RegisteredCharmDetailPresenterFactory
public class CharmDescriptionEditPresenterFactory implements CharmDetailPresenterFactory {

  @Override
  public CharmDetailPresenter create(ICharacterGenerics generics) {
    return new CharmDescriptionEditPresenter();
  }
}
