package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
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
    return "Requirement." + attribute.getId() + "." + count; //$NON-NLS-1$//$NON-NLS-2$
  }

  @Override
  public String toString() {
    return attribute.toString() + "x" + count; //$NON-NLS-1$
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