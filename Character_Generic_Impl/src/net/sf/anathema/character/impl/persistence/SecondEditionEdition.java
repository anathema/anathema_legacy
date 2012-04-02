package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class SecondEditionEdition implements IExaltedEdition {
  public static final String SECOND_EDITION = "SecondEdition";

  @Override
  public String getId() {
    return SECOND_EDITION;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SecondEditionEdition;
  }

  @Override
  public int hashCode() {
    return 19;
  }
}
