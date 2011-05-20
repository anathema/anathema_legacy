package net.sf.anathema.character.mutations;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.mutations.model.MutationsAdditionalModel;
import net.sf.anathema.character.mutations.presenter.MutationsPresenter;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.character.mutations.view.IMutationsViewProperties;
import net.sf.anathema.character.mutations.view.MutationsView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class MutationsViewFactory implements IAdditionalViewFactory {

  public IView createView(final IAdditionalModel model, final IResources resources, ICharacterType type) {
	IMutationsViewProperties properties = new IMutationsViewProperties()
	{
		@Override
		public String getMutationsString()
		{
			return resources.getString("Mutations.Label");
		}
	};
	
    IMutationsView view = new MutationsView(properties); 
    new MutationsPresenter(view, ((MutationsAdditionalModel) model).getModel(), resources).initPresentation();
    
    return view;
  }
}