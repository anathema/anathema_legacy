package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.scribe.editor.model.ScrollModel;

public class UpdateScrollName implements TextTypedListener {
  private final ScrollModel scrollModel;

  public UpdateScrollName(ScrollModel scrollModel) {
    this.scrollModel = scrollModel;
  }

  @Override
  public void textChanged(String newText) {
    scrollModel.setName(newText);
  }
}
