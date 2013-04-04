package net.sf.anathema.character.ghost.passions;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.ghost.passions.presenter.GhostPassionsPresenter;
import net.sf.anathema.character.ghost.passions.view.GhostPassionsConfigurationView;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.Resources;

public class GhostPassionsViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    GhostPassionsConfigurationView view = new GhostPassionsConfigurationView(
            IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type));
    new GhostPassionsPresenter(resources, view, (IGhostPassionsModel) model).initPresentation();
    return view;
  }
}