package net.sf.anathema.character.ghost.fetters;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.ghost.fetters.presenter.GhostFettersPresenter;
import net.sf.anathema.character.ghost.fetters.view.GhostFettersConfigurationView;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class GhostFettersViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    GhostFettersConfigurationView view = new GhostFettersConfigurationView(new MarkerIntValueDisplayFactory(resources, type));
    new GhostFettersPresenter(resources, view, (IGhostFettersModel) model).initPresentation();
    return view;
  }
}