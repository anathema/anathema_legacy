package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.scribe.editor.model.WikiText;

public interface ScrollEditor {

  void setWikiText(WikiText text);

  void setTitlePrompt(String prompt);

  void setTitle(String title);

  void whenContentTyped(TextTypedListener listener);

  void whenTitleTyped(TextTypedListener listener);

}
