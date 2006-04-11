package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class AlotmentSubPresenter implements IOverviewSubPresenter {

  private final ILabelledAlotmentView view;
  private final ISpendingModel model;
  private final int alotment;
  private final FontParameterSetter setter;

  public AlotmentSubPresenter(ISpendingModel model, ILabelledAlotmentView view, int alotment) {
    this.model = model;
    this.view = view;
    this.alotment = alotment;
    this.setter = new FontParameterSetter(model, view, alotment);
  }

  public void update() {
    view.setValue(model.getValue());
    view.setAlotment(alotment);
    setter.setFontParameters();
  }
}