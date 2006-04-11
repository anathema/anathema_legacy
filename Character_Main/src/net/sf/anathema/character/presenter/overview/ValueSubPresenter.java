package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class ValueSubPresenter implements IOverviewSubPresenter {

  private final IValueSpendingModel model;
  private final IValueView<Integer> view;

  public ValueSubPresenter(IValueSpendingModel model, IValueView<Integer> view) {
    this.model = model;
    this.view = view;
  }

  public void update() {
    view.setValue(model.getValue());
  }
}