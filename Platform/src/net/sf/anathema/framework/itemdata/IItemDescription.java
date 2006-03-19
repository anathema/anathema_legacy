package net.sf.anathema.framework.itemdata;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public interface IItemDescription {

  public ISimpleTextualDescription getName();

  public IStyledTextualDescription getContent();
}