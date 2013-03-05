package net.sf.anathema.scribe.perspective.presenter;

import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.scribe.editor.presenter.UpdateScrollContent;
import net.sf.anathema.scribe.editor.presenter.UpdateScrollEditor;
import net.sf.anathema.scribe.editor.presenter.UpdateScrollName;
import net.sf.anathema.scribe.perspective.model.ScribeModel;
import net.sf.anathema.scribe.perspective.view.ScribeView;

public class ScribePresenter {

  private ScribeView view;
  private ScribeModel model;
  private IResources resources;

  public ScribePresenter(ScribeView view, ScribeModel model, IResources resources) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  public void initPresentation() {
    initNavigation();
    view.scrollView.scrollEditor.whenContentTyped(new UpdateScrollContent(model.scrollModel));
    view.scrollView.scrollEditor.whenTitleTyped(new UpdateScrollName(model.scrollModel));
    UpdateScrollEditor updateScrollEditor = new UpdateScrollEditor(view.scrollView);
    model.scrollModel.initContent(updateScrollEditor);
    model.scrollModel.whenContentChanges(updateScrollEditor);
    model.scrollModel.initName(updateScrollEditor);
    model.scrollModel.whenNameChanges(updateScrollEditor);
  }

  private void initNavigation() {
    for (PrintNameFile file : model.collectAllScrolls()) {
      view.scribeNavigation.addText(file.getPrintName());
    }
  }
}
