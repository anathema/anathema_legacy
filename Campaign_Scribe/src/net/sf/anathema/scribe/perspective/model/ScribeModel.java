package net.sf.anathema.scribe.perspective.model;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.scribe.editor.model.ScrollModel;
import net.sf.anathema.scribe.scroll.persistence.InMemoryScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.ScrollPersister;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;

import java.util.Collection;

public class ScribeModel {
  public final ScrollPersister scrollPersister = new InMemoryScrollPersister();
  public final ScrollModel scrollModel = new ScrollModel(scrollPersister);
  private final IApplicationModel applicationModel;

  public ScribeModel(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  public Collection<ScrollReference> collectAllScrolls() {
    return scrollPersister.listAll();
  }
}
