package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public enum ExaltedEdition implements IExaltedEdition {
  SecondEdition;

  @Override
  public String getId() {
    return name();
  }
}