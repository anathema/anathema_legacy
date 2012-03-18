package net.sf.anathema.character.generic.magic.description;

import java.util.List;

public class AggregatedCharmDescription implements CharmDescription {
  private List<CharmDescription> descriptions;

  public AggregatedCharmDescription(List<CharmDescription> descriptions) {
    this.descriptions = descriptions;
  }

  @Override
  public boolean isEmpty() {
    for (CharmDescription description : descriptions) {
      if (!description.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String[] getParagraphs() {
    for (CharmDescription description : descriptions) {
      if (!description.isEmpty()) {
        return description.getParagraphs();
      }
    }
    return new String[0];
  }
}
