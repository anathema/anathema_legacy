package net.sf.anathema.scribe.editor.model;

import org.jmock.example.announcer.Announcer;

public class ScrollModel {

  private final Announcer<ScrollChangedListener> contentChangeAnnouncer = new Announcer(ScrollChangedListener.class);
  private final Announcer<ScrollChangedListener> nameChangeAnnouncer = new Announcer(ScrollChangedListener.class);
  private WikiText wikiText = new WikiText("");
  private String name = "";

  public void setText(WikiText wikiText) {
    if (this.wikiText.equals(wikiText)) {
      return;
    }
    this.wikiText = wikiText;
    HtmlText htmlText = new HtmlConverter().convert(wikiText);
    contentChangeAnnouncer.announce().contentChanged(wikiText, htmlText);
  }

  public void setName(String name) {
    if (this.name.equals(name)) {
      return;
    }
    this.name = name;
    nameChangeAnnouncer.announce().nameChanged(name);
  }

  public void whenContentChanges(ScrollChangedListener listener) {
    contentChangeAnnouncer.addListener(listener);
  }

  public void whenNameChanges(ScrollChangedListener listener) {
    nameChangeAnnouncer.addListener(listener);
  }

  public void initContent(ScrollChangedListener listener) {
    HtmlText htmlText = new HtmlConverter().convert(wikiText);
    listener.contentChanged(wikiText, htmlText);
  }

  public void initName(ScrollChangedListener listener) {
    listener.nameChanged(name);
  }
}
