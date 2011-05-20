package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.view.SecondEditionBeastformView;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.presenter.MutationsPresenter;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionBeastformPresenter implements IPresenter {

  private final IResources resources;
  private final IBeastformView view;
  private final IBeastformModel model;
  private final IMutationsModel mutationModel;
  private final IMutationsView mutationView;

  public SecondEditionBeastformPresenter(IResources resources,
		  IBeastformView view,
		  IBeastformModel model,
		  IMutationsModel mutationModel,
		  IMutationsView mutationView) {
    this.resources = resources;
    this.view = view;
    this.model = model;
    this.mutationModel = mutationModel;
    this.mutationView = mutationView;
  }

  public void initPresentation() {
    initAttributePresentation();
    initGiftPresentation();
    initSpiritFormPresentation();
  }
  
  private void initSpiritFormPresentation()
  {
	  ((SecondEditionBeastformView)view).setSpiritListener(new IObjectValueChangedListener<String>()
	  {

		@Override
		public void valueChanged(String newValue)
		{
			((SecondEditionBeastformModel)model).setSpiritForm(newValue);
		}
	  });
  }

  private void initGiftPresentation()
  {
	  MutationsPresenter presenter = new MutationsPresenter(mutationView, mutationModel, resources);
	  presenter.initPresentation();
	  view.addMutationsView(presenter.getView());
  }

  private void initAttributePresentation() {
    for (IBeastformAttribute attribute : model.getAttributes()) {
      IDefaultTrait trait = attribute.getTrait();
      IIntValueView traitView = view.addAttributeValueView(
          resources.getString(trait.getType().getId()),
          trait.getCurrentValue(),
          trait.getMaximalValue());
      new TraitPresenter(trait, traitView).initPresentation();
    }
    for (IBeastformAttribute attribute : ((SecondEditionBeastformModel)model).getSpiritAttributes()) {
        IDefaultTrait trait = attribute.getTrait();
        IIntValueView traitView = ((SecondEditionBeastformView)view).addSpiritAttributeValueView(
                resources.getString(trait.getType().getId()),
                trait.getCurrentValue(),
                trait.getMaximalValue());
        new TraitPresenter(trait, traitView).initPresentation();
        
      }
  }
}