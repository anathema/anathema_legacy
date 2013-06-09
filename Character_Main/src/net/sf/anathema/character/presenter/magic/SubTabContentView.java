package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;

import java.util.List;

public class SubTabContentView implements ContentView {
  private List<IContentPresenter> subPresenters;

  public SubTabContentView(List<IContentPresenter> subPresenters) {
    this.subPresenters = subPresenters;
  }

  @Override
  public void addTo(MultipleContentView view) {
    for (IContentPresenter presenter : subPresenters) {
      presenter.getTabContent().addTo(view);
    }
  }
}