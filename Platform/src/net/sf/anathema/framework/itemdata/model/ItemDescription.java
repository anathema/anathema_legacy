package net.sf.anathema.framework.itemdata.model;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.StyledTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class ItemDescription implements IItemDescription {

  private final ITextualDescription name;
  private final IStyledTextualDescription content;

  public ItemDescription() {
    this("");
  }

  public ItemDescription(String initialName) {
    this.name = new SimpleTextualDescription(initialName);
    this.content = new StyledTextualDescription();
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }

  @Override
  public IStyledTextualDescription getContent() {
    return content;
  }
}