package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.scribe.editor.model.ScrollModel;
import net.sf.anathema.platform.markdown.WikiText;

public class UpdateScrollContent implements TextTypedListener {
  private final ScrollModel scrollModel;

  public UpdateScrollContent(ScrollModel scrollModel) {
    this.scrollModel = scrollModel;
  }

  @Override
  public void textChanged(String newText) {
    scrollModel.setText(new WikiText(newText));
  }
}
