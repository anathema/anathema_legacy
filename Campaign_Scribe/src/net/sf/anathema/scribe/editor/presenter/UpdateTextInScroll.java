package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.scribe.editor.model.ScrollModel;
import net.sf.anathema.scribe.editor.model.WikiText;
import net.sf.anathema.scribe.editor.view.TextTypedListener;

public class UpdateTextInScroll implements TextTypedListener {
  private final ScrollModel scrollModel;

  public UpdateTextInScroll(ScrollModel scrollModel) {
    this.scrollModel = scrollModel;
  }

  @Override
  public void textChanged(String newText) {
    scrollModel.setText(new WikiText(newText));
  }
}
