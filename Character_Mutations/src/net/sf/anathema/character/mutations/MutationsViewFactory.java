package net.sf.anathema.character.mutations;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.mutations.model.MutationsAdditionalModel;
import net.sf.anathema.character.mutations.presenter.MutationsPresenter;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.character.mutations.view.MutationsView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.Resources;

public class MutationsViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, final Resources resources, ICharacterType type) {
    IMutationsView view = new MutationsView();
    MutationsPresenter presenter = new MutationsPresenter(view, ((MutationsAdditionalModel) model).getModel(),
            resources);
    presenter.initPresentation();
    return view;
  }
}
