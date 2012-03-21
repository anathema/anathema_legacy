package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public enum ExaltedRuleSet implements IExaltedRuleSet {

  SecondEdition(ExaltedEdition.SecondEdition);

  private final IExaltedEdition edition;

  private ExaltedRuleSet(IExaltedEdition edition) {
    this.edition = edition;
  }

  @Override
  public IExaltedEdition getEdition() {
    return edition;
  }

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}