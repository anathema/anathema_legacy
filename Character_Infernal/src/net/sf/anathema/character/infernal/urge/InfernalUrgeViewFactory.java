package net.sf.anathema.character.infernal.urge;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.infernal.urge.presenter.InfernalUrgePresenter;
import net.sf.anathema.character.infernal.urge.view.InfernalUrgeView;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class InfernalUrgeViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    InfernalUrgeView view = new InfernalUrgeView(new MarkerIntValueDisplayFactory(resources, type));
    new InfernalUrgePresenter(resources, view, (IInfernalUrgeModel) model).initPresentation();
    return view;
  }
}