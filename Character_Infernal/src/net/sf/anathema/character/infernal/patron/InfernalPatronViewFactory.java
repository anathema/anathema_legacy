package net.sf.anathema.character.infernal.patron;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronModel;
import net.sf.anathema.character.infernal.patron.presenter.InfernalPatronPresenter;
import net.sf.anathema.character.infernal.patron.view.InfernalPatronView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.Resources;

public class InfernalPatronViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    InfernalPatronView view = new InfernalPatronView();
    new InfernalPatronPresenter(resources, view, (IInfernalPatronModel) model).initPresentation();
    return view;
  }
}