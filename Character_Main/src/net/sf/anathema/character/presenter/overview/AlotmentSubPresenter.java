package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class AlotmentSubPresenter implements IOverviewSubPresenter {

  private final ILabelledAlotmentView view;
  private final ISpendingModel model;
  private final FontParameterSetter setter;

  public AlotmentSubPresenter(ISpendingModel model, ILabelledAlotmentView view) {
    this.model = model;
    this.view = view;
    this.setter = new FontParameterSetter(model, view);
  }

  public void update() {
    view.setValue(model.getValue());
    view.setAlotment(model.getAlotment());
    setter.setFontParameters();
  }
}