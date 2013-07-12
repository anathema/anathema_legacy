package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.view.labelledvalue.LabelledAllotmentView;
import net.sf.anathema.hero.points.overview.SpendingModel;

public class AllotmentSubPresenter implements IOverviewSubPresenter {

  private final LabelledAllotmentView view;
  private final SpendingModel model;
  private final FontParameterSetter setter;

  public AllotmentSubPresenter(SpendingModel model, LabelledAllotmentView view) {
    this.model = model;
    this.view = view;
    this.setter = new FontParameterSetter(model, view);
  }

  @Override
  public void update() {
    view.setValue(model.getValue());
    view.setAllotment(model.getAllotment());
    setter.setFontParameters();
  }
}