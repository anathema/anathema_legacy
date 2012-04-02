package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class SecondEditionRules implements IExaltedRuleSet {
  private static final String SECOND_EDITION = "SecondEdition";

  @Override
  public IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  @Override
  public String getId() {
    return SECOND_EDITION;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SecondEditionRules;
  }

  @Override
  public int hashCode() {
    return 18;
  }
}