package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IItemDescription {

  public ITextualDescription getName();

  public IStyledTextualDescription getContent();

  public void setClean();

  public boolean isDirty();

  public void addDirtyListener(IChangeListener changeListener);

  public void removeDirtyListener(IChangeListener changeListener);
}