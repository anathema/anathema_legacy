package net.sf.anathema.character.main.magic.model.source;

public class SourceList implements ISourceList {

  private SourceBook primarySource;

  @Override
  public SourceBook getPrimarySource() {
    return primarySource;
  }

  @Override
  public boolean isEmpty() {
    return primarySource == null;
  }

  public void addSource(SourceBook source) {
    if (primarySource == null) {
      this.primarySource = source;
    }
  }
}