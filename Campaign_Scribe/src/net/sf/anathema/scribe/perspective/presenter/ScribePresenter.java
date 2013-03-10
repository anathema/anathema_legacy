package net.sf.anathema.scribe.perspective.presenter;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.scribe.editor.presenter.ScrollPresenter;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.view.ScribeView;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;

public class ScribePresenter {

  private ScribeView view;
  private ScribeModel model;
  private IResources resources;

  public ScribePresenter(ScribeModel model, ScribeView view, IResources resources) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeNavigationPresentation();
    initializeScrollPresentation();
    model.scrollPersister.addScrollListChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        view.scribeNavigation.clear();
        addAllReferences();
      }
    });
    Tool tool = view.scribeNavigation.addTool();
    tool.setIcon("icons/Scroll20.png");
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.scrollModel.startNewScroll();
      }
    });
  }

  private void initializeScrollPresentation() {
    ScrollPresenter scrollPresenter = new ScrollPresenter(model.scrollModel, view.scrollView.scrollEditor, view.scrollView.scrollPreview, resources);
    scrollPresenter.initPresentation();
  }

  private void initializeNavigationPresentation() {
    addAllReferences();
  }

  private void addAllReferences() {
    for (final ScrollReference reference : model.collectAllScrolls()) {
      view.scribeNavigation.addScroll(reference, new Command() {
        @Override
        public void execute() {
          model.scrollModel.loadScroll(reference);
        }
      });
    }
  }
}
