package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.scribe.editor.model.ScrollModel;

public class ScrollPresenter {

  private ScrollModel model;
  private ScrollEditor editor;
  private ScrollPreview preview;
  private IResources resources;

  public ScrollPresenter(ScrollModel model, ScrollEditor editor, ScrollPreview preview, IResources resources) {
    this.model = model;
    this.editor = editor;
    this.preview = preview;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeEditorListening();
    initializeModelListening();
  }

  private void initializeEditorListening() {
    editor.whenContentTyped(new UpdateScrollContent(model));
    editor.whenTitleTyped(new UpdateScrollName(model));
  }

  private void initializeModelListening() {
    UpdateScrollEditor updateScrollEditor = new UpdateScrollEditor(editor, preview, resources);
    model.initContent(updateScrollEditor);
    model.whenContentChanges(updateScrollEditor);
    model.initName(updateScrollEditor);
    model.whenNameChanges(updateScrollEditor);
  }
}
