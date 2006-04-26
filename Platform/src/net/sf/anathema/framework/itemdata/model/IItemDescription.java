package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IItemDescription {

  public ITextualDescription getName();

  public IStyledTextualDescription getContent();
}