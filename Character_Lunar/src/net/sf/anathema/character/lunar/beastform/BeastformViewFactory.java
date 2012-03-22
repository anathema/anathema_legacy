package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformView;
import net.sf.anathema.character.lunar.beastform.presenter.SecondEditionBeastformPresenter;
import net.sf.anathema.character.lunar.beastform.view.IBeastformViewProperties;
import net.sf.anathema.character.lunar.beastform.view.SecondEditionBeastformView;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.character.mutations.view.IMutationsViewProperties;
import net.sf.anathema.character.mutations.view.MutationsView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class BeastformViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, final IResources resources, ICharacterType type) {
    MarkerIntValueDisplayFactory intValueDisplayFactory = new MarkerIntValueDisplayFactory(resources, type);
    SecondEditionBeastformModel secondmodel = (SecondEditionBeastformModel) model;
    IBeastformViewProperties properties = new SecondEditionBeastformViewProperties(resources, secondmodel);
    IBeastformView view = new SecondEditionBeastformView(intValueDisplayFactory, properties);
    IMutationsView mutationView = new MutationsView(new IMutationsViewProperties() {
      @Override
      public String getMutationsString() {
        return resources.getString("Mutations.Label");
      }
    });
    SecondEditionBeastformPresenter presenter = new SecondEditionBeastformPresenter(resources, view,
            (IBeastformModel) model, secondmodel.getMutationModel(), mutationView);
    presenter.initPresentation();
    return view;
  }

}