package net.sf.anathema.scribe.perspective.presenter;

import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.scribe.editor.presenter.UpdateTextInScroll;
import net.sf.anathema.scribe.editor.presenter.UpdateTextInScrollEditor;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.view.ScribeView;

public class ScribePresenter {

  private ScribeView view;
  private ScribeModel model;

  public ScribePresenter(ScribeView view, ScribeModel model) {
    this.view = view;
    this.model = model;
  }

  public void initPresentation() {
    initNavigation();
    view.scrollEditor.whenTextTyped(new UpdateTextInScroll(model.scrollModel));
    model.scrollModel.whenContentChanges(new UpdateTextInScrollEditor(view.scrollEditor));
  }

  private void initNavigation() {
    for (PrintNameFile file : model.collectAllNotes()) {
      view.scribeNavigation.addText(file.getPrintName());
    }
  }
}
