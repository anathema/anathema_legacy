package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.hero.points.overview.IValueModel;

public class ValueSubPresenter implements IOverviewSubPresenter {

  private final IValueModel<Integer> model;
  private final IValueView<Integer> view;

  public ValueSubPresenter(IValueModel<Integer> model, IValueView<Integer> view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void update() {
    view.setValue(model.getValue());
  }
}