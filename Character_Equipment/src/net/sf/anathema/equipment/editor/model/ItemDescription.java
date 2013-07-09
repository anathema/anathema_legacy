package net.sf.anathema.equipment.editor.model;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

public class ItemDescription implements IItemDescription {

  private final ITextualDescription name;
  private final ITextualDescription content;

  public ItemDescription() {
    this("");
  }

  public ItemDescription(String initialName) {
    this.name = new SimpleTextualDescription(initialName);
    this.content = new SimpleTextualDescription();
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }

  @Override
  public ITextualDescription getContent() {
    return content;
  }
}