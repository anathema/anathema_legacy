package net.sf.anathema.magic.description.module;

import net.sf.anathema.character.magic.description.MagicDescription;

public class DirectMagicDescription implements MagicDescription {
  private String description;

  public DirectMagicDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean isEmpty() {
    return description == null || description.isEmpty();
  }

  @Override
  public String[] getParagraphs() {
    return isEmpty() ? new String[0] : splitInParagraphs();
  }

  private String[] splitInParagraphs() {
    return description.split("\\r?\\n");
  }
}
