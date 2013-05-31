package net.sf.anathema.scribe.scroll.persistence;

import net.sf.anathema.framework.repository.access.printname.ReferenceAccess;

import java.util.Collection;
import java.util.Collections;

class DummyAccess implements ReferenceAccess {

  public static DummyAccess empty() {
    return new DummyAccess(true);
  }

  public static DummyAccess filled() {
    return new DummyAccess(false);
  }

  private boolean empty;

  DummyAccess(boolean isEmpty) {
    empty = isEmpty;
  }

  @Override
  public Collection collectAllItemReferences() {
    if (empty) {
      return Collections.emptyList();
    }
    else {
      return Collections.singletonList(new Object());
    }
  }
}