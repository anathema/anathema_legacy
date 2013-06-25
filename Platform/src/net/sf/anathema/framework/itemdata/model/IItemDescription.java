package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IItemDescription {

  ITextualDescription getName();

  IStyledTextualDescription getContent();

  void setClean();

  boolean isDirty();

  void addDirtyListener(ChangeListener changeListener);

  void removeDirtyListener(ChangeListener changeListener);
}