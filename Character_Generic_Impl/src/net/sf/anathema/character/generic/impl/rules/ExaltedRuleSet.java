package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;

public enum ExaltedRuleSet implements IExaltedRuleSet {

  SecondEdition(ExaltedEdition.SecondEdition) {
    @Override
    public void accept(IRuleSetVisitor visitor) {
      visitor.visitSecondEdition(this);
    }
  };

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

  public static IExaltedRuleSet[] getRuleSetsByEdition(IExaltedEdition edition) {
    final IExaltedRuleSet[][] rules = new IExaltedRuleSet[1][];
    edition.accept(new IEditionVisitor() {
      @Override
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        rules[0] = new IExaltedRuleSet[0];
      }

      @Override
      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        rules[0] = new IExaltedRuleSet[] { SecondEdition };
      }
    });
    return rules[0];
  }

  @Override
  public String toString() {
    return getId();
  }
}