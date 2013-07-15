package net.sf.anathema.scribe.perspective.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.scribe.editor.model.ScrollModel;
import net.sf.anathema.scribe.scroll.persistence.Clock;
import net.sf.anathema.scribe.scroll.persistence.RepositoryScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.ScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;
import net.sf.anathema.scribe.scroll.persistence.SystemClock;

import java.util.Collection;

public class ScribeModel {
  public final ScrollPersister scrollPersister;
  public final ScrollModel scrollModel;

  public ScribeModel(IApplicationModel applicationModel) {
    Clock clock = new SystemClock();
    this.scrollPersister =  new RepositoryScrollPersister(applicationModel.getRepository(), clock);
    this.scrollModel = new ScrollModel(scrollPersister);
  }

  public Collection<ScrollReference> collectAllScrolls() {
    return scrollPersister.listAll();
  }
}
