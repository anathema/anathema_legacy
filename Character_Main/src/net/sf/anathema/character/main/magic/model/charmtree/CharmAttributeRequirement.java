package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.ICharmAttribute;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.lib.lang.ReflectionEqualsObject;

public class CharmAttributeRequirement extends ReflectionEqualsObject implements IndirectCharmRequirement {

  private final ICharmAttribute attribute;
  private final int count;

  public CharmAttributeRequirement(ICharmAttribute attribute, int count) {
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
  public boolean isFulfilled(ICharm[] learnedCharms) {
    int amount = 0;
    for (ICharm charm : learnedCharms) {
      if (charm.hasAttribute(attribute)) {
        amount++;
      }
    }
    return amount >= count;
  }
}