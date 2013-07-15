package net.sf.anathema.scribe.scroll.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.initialitems.RepositoryItemInitializationStrategy;
import net.sf.anathema.scribe.scroll.gson.ScrollGson;
import net.sf.anathema.scribe.scroll.persistence.RepositoryScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.Scroll;
import net.sf.anathema.scribe.scroll.persistence.SystemClock;

public class ScrollInitializationStrategy implements RepositoryItemInitializationStrategy {
  private final static String SCROLL_REGEX = "^.*\\.scroll$";

  private final ScrollGson gson = new ScrollGson();
  private final RepositoryScrollPersister persister;

  public ScrollInitializationStrategy(IApplicationModel anathemaModel) {
    persister = new RepositoryScrollPersister(anathemaModel.getRepository(), new SystemClock());
  }

  public boolean shouldInitializeData() {
    return !persister.hasAny();
  }

  public void storeInRepository(String itemJSON) {
    Scroll scroll = gson.fromJson(itemJSON);
    persister.saveScroll(scroll);
  }

  public String getMessageKey() {
    return "Scribe.Initialization.CopyManualMessage";
  }

  public String getItemPattern() {
    return SCROLL_REGEX;
  }
}