package net.sf.anathema.scribe.scroll.persistence;

import java.util.Collection;

public interface ScrollPersister {

  void saveScroll(Scroll scroll);

  Scroll loadScroll(RepositoryId id);

  Scroll newScroll();

  Collection<ScrollReference> listAll();
}
