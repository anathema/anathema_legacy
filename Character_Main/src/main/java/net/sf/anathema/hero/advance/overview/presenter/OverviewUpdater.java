package net.sf.anathema.hero.advance.overview.presenter;

import java.util.ArrayList;
import java.util.List;

public class OverviewUpdater {

  private final List<IOverviewSubPresenter> presenters = new ArrayList<>();

  public void update() {
    for (IOverviewSubPresenter presenter : presenters) {
      presenter.update();
    }
  }

  public void add(IOverviewSubPresenter presenter) {
    presenters.add(presenter);
  }
}
