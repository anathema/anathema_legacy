package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public class SourceList implements ISourceList {

  private IExaltedSourceBook primarySource;

  @Override
  public IExaltedSourceBook getPrimarySource() {
    return primarySource;
  }

  @Override
  public boolean isEmpty() {
    return primarySource == null;
  }

  public void addSource(IExaltedSourceBook source) {
    if (primarySource == null) {
      this.primarySource = source;
    }
  }
}