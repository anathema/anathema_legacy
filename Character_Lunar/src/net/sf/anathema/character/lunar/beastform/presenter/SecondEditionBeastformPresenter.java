package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.view.SecondEditionBeastformView;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.presenter.MutationsPresenter;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionBeastformPresenter implements Presenter {

  private final Resources resources;
  private final IBeastformView view;
  private final IBeastformModel model;
  private final IMutationsModel mutationModel;
  private final IMutationsView mutationView;

  public SecondEditionBeastformPresenter(Resources resources, IBeastformView view, IBeastformModel model,
                                         IMutationsModel mutationModel, IMutationsView mutationView) {
    this.resources = resources;
    this.view = view;
    this.model = model;
    this.mutationModel = mutationModel;
    this.mutationView = mutationView;
  }

  @Override
  public void initPresentation() {
    initAttributePresentation();
    initGiftPresentation();
    initSpiritFormPresentation();
  }

  private void initSpiritFormPresentation() {
    ((SecondEditionBeastformView) view).setSpiritListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        ((SecondEditionBeastformModel) model).setSpiritForm(newValue);
      }
    });
  }

  private void initGiftPresentation() {
    MutationsPresenter presenter = new BeastformPresenter(mutationView, mutationModel, resources);
    presenter.initPresentation();
    view.addMutationsView(presenter.getView());
  }

  private void initAttributePresentation() {
    for (IBeastformAttribute attribute : model.getAttributes()) {
      IDefaultTrait trait = attribute.getTrait();
      IIntValueView traitView = view.addAttributeValueView(resources.getString(trait.getType().getId()),
              trait.getCurrentValue(), trait.getMaximalValue());
      new TraitPresenter(trait, traitView).initPresentation();
    }
    for (IBeastformAttribute attribute : ((SecondEditionBeastformModel) model).getSpiritAttributes()) {
      IDefaultTrait trait = attribute.getTrait();
      IIntValueView traitView = ((SecondEditionBeastformView) view).addSpiritAttributeValueView(
              resources.getString(trait.getType().getId()), trait.getCurrentValue(), trait.getMaximalValue());
      new TraitPresenter(trait, traitView).initPresentation();

    }
  }
}
