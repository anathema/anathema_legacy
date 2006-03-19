package net.sf.anathema.framework.itemdata;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.StyledTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ItemDescription implements IItemDescription {

  private final ISimpleTextualDescription name;
  private final IStyledTextualDescription content;

  public ItemDescription() {
    this(""); //$NON-NLS-1$
  }

  public ItemDescription(String name) {
    this.name = new SimpleTextualDescription(name);
    this.content = new StyledTextualDescription();
  }

  public ISimpleTextualDescription getName() {
    return name;
  }

  public IStyledTextualDescription getContent() {
    return content;
  }
}