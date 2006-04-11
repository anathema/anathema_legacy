package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.character.library.overview.IAdditionalAlotmentView;

public class AdditionalAlotmentSubPresenter implements IOverviewSubPresenter {

  private final IAdditionalAlotmentView view;
  private final IAdditionalSpendingModel model;
  private final int alotment;
  private final FontParameterSetter setter;

  public AdditionalAlotmentSubPresenter(IAdditionalSpendingModel model, IAdditionalAlotmentView view, int alotment) {
    this.model = model;
    this.view = view;
    this.alotment = alotment;
    this.setter = new FontParameterSetter(model, view, alotment);
  }

  public void update() {
    view.setValue(model.getValue(), model.getAdditionalValue());
    view.setAlotment(alotment, model.getAdditionalAlotment());
    setter.setFontParameters();
  }
}