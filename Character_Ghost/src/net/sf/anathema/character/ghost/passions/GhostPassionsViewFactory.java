package net.sf.anathema.character.ghost.passions;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.ghost.passions.presenter.GhostPassionsPresenter;
import net.sf.anathema.character.ghost.passions.view.GhostPassionsConfigurationView;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class GhostPassionsViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    GhostPassionsConfigurationView view = new GhostPassionsConfigurationView(new MarkerIntValueDisplayFactory(resources, type));
    new GhostPassionsPresenter(resources, view, (IGhostPassionsModel) model).initPresentation();
    return view;
  }
}