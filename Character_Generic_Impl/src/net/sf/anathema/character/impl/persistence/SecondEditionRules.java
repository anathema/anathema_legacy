package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.impl.persistence.SecondEditionEdition.SECOND_EDITION;

public class SecondEditionRules implements IExaltedRuleSet {

  @Override
  public IExaltedEdition getEdition() {
    return SecondEdition;
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