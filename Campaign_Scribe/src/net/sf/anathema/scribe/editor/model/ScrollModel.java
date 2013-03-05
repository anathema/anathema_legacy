package net.sf.anathema.scribe.editor.model;

import org.jmock.example.announcer.Announcer;

public class ScrollModel {

  private final Announcer<ScrollChangedListener> contentChangeAnnouncer = new Announcer(ScrollChangedListener.class);

  private WikiText wikiText = new WikiText("");

  public void setText(WikiText wikiText) {
    if (this.wikiText.equals(wikiText)) {
      return;
    }
    this.wikiText = wikiText;
    HtmlText htmlText = new HtmlConverter().convert(wikiText);
    contentChangeAnnouncer.announce().contentChanged(wikiText, htmlText);
  }

  public void whenContentChanges(ScrollChangedListener listener) {
    contentChangeAnnouncer.addListener(listener);
  }
}
