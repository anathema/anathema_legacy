package net.sf.anathema.character.thaumaturgy.view;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyAdditionalModel;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyModel;
import net.sf.anathema.character.thaumaturgy.presenter.ThaumaturgyPresenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class ThaumaturgyViewFactory implements IAdditionalViewFactory {

  public IView createView(final IAdditionalModel model, final IResources resources, ICharacterType type) {
	    IThaumaturgyModel thaumaturgyModel = ((IThaumaturgyAdditionalModel) model).getThaumaturgyModel();
	    ThaumaturgyView view = new ThaumaturgyView(new MarkerIntValueDisplayFactory(resources, type));
	    new ThaumaturgyPresenter(thaumaturgyModel, view, resources).initPresentation();
	    return view;
  }
}