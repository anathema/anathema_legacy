package net.sf.anathema.character.infernal.patron;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronModel;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronViewProperties;
import net.sf.anathema.character.infernal.patron.presenter.InfernalPatronPresenter;
import net.sf.anathema.character.infernal.patron.presenter.InfernalPatronViewProperties;
import net.sf.anathema.character.infernal.patron.view.InfernalPatronView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class InfernalPatronViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    IInfernalPatronViewProperties properties = new InfernalPatronViewProperties(resources);
    InfernalPatronView view = new InfernalPatronView(properties);
    new InfernalPatronPresenter(resources, view, (IInfernalPatronModel) model).initPresentation();
    return view;
  }
}