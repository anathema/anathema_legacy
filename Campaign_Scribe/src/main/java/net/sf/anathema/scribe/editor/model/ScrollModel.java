package net.sf.anathema.scribe.editor.model;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.markdown.HtmlConverter;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.platform.markdown.WikiText;
import net.sf.anathema.scribe.scroll.persistence.RepositoryId;
import net.sf.anathema.scribe.scroll.persistence.Scroll;
import net.sf.anathema.scribe.scroll.persistence.ScrollDto;
import net.sf.anathema.scribe.scroll.persistence.ScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;
import org.jmock.example.announcer.Announcer;

public class ScrollModel {

  private class SaveListener implements ScrollChangedListener {

    @Override
    public void contentChanged(WikiText wikiText, HtmlText htmlText) {
      persister.saveScroll(bindCurrentScroll());
    }

    @Override
    public void nameChanged(String text) {
      persister.saveScroll(bindCurrentScroll());
    }

    private Scroll bindCurrentScroll() {
      ScrollDto scrollDto = new ScrollDto(name, wikiText.getCanonicalText());
      return new Scroll(scrollDto, repositoryId);
    }
  }

  private final Announcer<ScrollChangedListener> contentChangeAnnouncer = new Announcer(ScrollChangedListener.class);
  private final Announcer<ScrollChangedListener> nameChangeAnnouncer = new Announcer(ScrollChangedListener.class);
  private final Announcer<ChangeListener> listChangeAnnouncer = new Announcer(ChangeListener.class);
  private final ScrollPersister persister;
  private WikiText wikiText;
  private String name;
  private RepositoryId repositoryId;

  public ScrollModel(ScrollPersister persister) {
    this.persister = persister;
    Scroll newScroll = persister.newScroll();
    this.repositoryId = newScroll.repositoryId;
    wikiText = new WikiText(newScroll.dto.wikiText);
    name = newScroll.dto.title;
    contentChangeAnnouncer.addListener(new SaveListener());
    nameChangeAnnouncer.addListener(new SaveListener());
  }

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
    listChangeAnnouncer.announce().changeOccurred();
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

  public void startNewScroll() {
    Scroll newScroll = persister.newScroll();
    setScroll(newScroll);
    listChangeAnnouncer.announce().changeOccurred();
  }

  public void loadScroll(ScrollReference reference) {
    Scroll scroll = persister.loadScroll(reference.repositoryId);
    setScroll(scroll);
  }

  private void setScroll(Scroll scroll) {
    this.repositoryId = scroll.repositoryId;
    setText(new WikiText(scroll.dto.wikiText));
    setName(scroll.dto.title);
  }

  public void addScrollListChangeListener(ChangeListener listener) {
    listChangeAnnouncer.addListener(listener);
  }
}
