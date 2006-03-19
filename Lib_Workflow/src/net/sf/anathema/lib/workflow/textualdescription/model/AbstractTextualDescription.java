package net.sf.anathema.lib.workflow.textualdescription.model;

public abstract class AbstractTextualDescription implements ITextualDescription {

  private boolean dirty = true;

  public boolean isDirty() {
    return dirty;
  }

  public void setDirty(boolean isDirty) {
    this.dirty = isDirty;
  }
}