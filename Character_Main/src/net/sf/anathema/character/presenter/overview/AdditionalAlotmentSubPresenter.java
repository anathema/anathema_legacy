package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.character.library.overview.IAdditionalAlotmentView;

public class AdditionalAlotmentSubPresenter implements IOverviewSubPresenter {

  private final IAdditionalAlotmentView view;
  private final IAdditionalSpendingModel model;
  private final int baseAlotment;
  private final FontParameterSetter setter;

  public AdditionalAlotmentSubPresenter(IAdditionalSpendingModel model, IAdditionalAlotmentView view, int baseAlotment) {
    this.model = model;
    this.view = view;
    this.baseAlotment = baseAlotment;
    this.setter = new FontParameterSetter(model, view, baseAlotment);
  }

  public void update() {
    view.setValue(model.getValue(), model.getAdditionalValue());
    view.setAlotment(baseAlotment + model.getAdditionalUnrestrictedAlotment(), model.getAdditionalRestrictedAlotment());
    setter.setFontParameters();
  }
}