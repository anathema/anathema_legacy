package net.sf.anathema.scribe.editor;

import net.sf.anathema.scribe.editor.model.WikiText;
import net.sf.anathema.scribe.editor.presenter.ScrollEditor;
import net.sf.anathema.scribe.editor.presenter.TextTypedListener;
import org.jmock.example.announcer.Announcer;

public class ScrollEditorDummy implements ScrollEditor {

  public final Announcer<TextTypedListener> contentChangedAnnouncer = new Announcer<TextTypedListener>(TextTypedListener.class);
  public final Announcer<TextTypedListener> titleChangedAnnouncer = new Announcer<TextTypedListener>(TextTypedListener.class);
  public WikiText text;
  public String title;

  @Override
  public void setWikiText(WikiText text) {
    this.text = text;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void whenContentTyped(TextTypedListener listener) {
    contentChangedAnnouncer.addListener(listener);
  }

  @Override
  public void whenTitleTyped(TextTypedListener listener) {
    titleChangedAnnouncer.addListener(listener);
  }
}
