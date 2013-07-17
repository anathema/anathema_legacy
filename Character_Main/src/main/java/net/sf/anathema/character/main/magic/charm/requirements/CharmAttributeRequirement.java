package net.sf.anathema.character.main.magic.charm.requirements;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.model.attribute.MagicAttribute;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class CharmAttributeRequirement extends ReflectionEqualsObject implements IndirectCharmRequirement {

  private final MagicAttribute attribute;
  private final int count;

  public CharmAttributeRequirement(MagicAttribute attribute, int count) {
    this.attribute = attribute;
    this.count = count;
  }

  @Override
  public String getStringRepresentation() {
    return "Requirement." + attribute.getId() + "." + count;
  }

  @Override
  public String toString() {
    return attribute.toString() + "x" + count;
  }

  @Override
  public boolean isFulfilled(Charm[] learnedCharms) {
    int amount = 0;
    for (Charm charm : learnedCharms) {
      if (charm.hasAttribute(attribute)) {
        amount++;
      }
    }
    return amount >= count;
  }
}