package net.sf.anathema.charm.description.module;

import net.sf.anathema.character.generic.magic.description.CharmDescription;

public class DirectCharmDescription implements CharmDescription {
  private String description;

  public DirectCharmDescription(String description) {
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
