package net.sf.anathema.scribe.perspective.presenter;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.view.ScribeNavigation;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;

public class NavigationPresenter {
  private ScribeModel model;
  private ScribeNavigation view;

  public NavigationPresenter(ScribeModel model, ScribeNavigation view) {
    this.model = model;
    this.view = view;
  }

  public void initializeNavigationPresentation() {
    addAllReferences();
    model.scrollPersister.addScrollListChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        view.clear();
        addAllReferences();
      }
    });
  }

  private void addAllReferences() {
    for (final ScrollReference reference : model.collectAllScrolls()) {
      view.addScroll(reference, new Command() {
        @Override
        public void execute() {
          model.scrollModel.loadScroll(reference);
        }
      });
    }
  }
}