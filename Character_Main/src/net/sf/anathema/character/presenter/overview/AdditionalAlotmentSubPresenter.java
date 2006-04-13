package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.character.library.overview.IAdditionalAlotmentView;

public class AdditionalAlotmentSubPresenter implements IOverviewSubPresenter {

  private final IAdditionalAlotmentView view;
  private final IAdditionalSpendingModel model;
  private final FontParameterSetter setter;

  public AdditionalAlotmentSubPresenter(IAdditionalSpendingModel model, IAdditionalAlotmentView view) {
    this.model = model;
    this.view = view;
    this.setter = new FontParameterSetter(model, view);
  }

  public void update() {
    view.setValue(model.getValue(), model.getAdditionalValue());
    view.setAlotment(model.getAlotment(), model.getAdditionalRestrictedAlotment());
    setter.setFontParameters();
  }
}