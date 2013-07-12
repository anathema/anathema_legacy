package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.view.labelledvalue.ILabelledAllotmentView;
import net.sf.anathema.hero.points.overview.SpendingModel;

public class AllotmentSubPresenter implements IOverviewSubPresenter {

  private final ILabelledAllotmentView view;
  private final SpendingModel model;
  private final FontParameterSetter setter;

  public AllotmentSubPresenter(SpendingModel model, ILabelledAllotmentView view) {
    this.model = model;
    this.view = view;
    this.setter = new FontParameterSetter(model, view);
  }

  @Override
  public void update() {
    view.setValue(model.getValue());
    view.setAlotment(model.getAllotment());
    setter.setFontParameters();
  }
}