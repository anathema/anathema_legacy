package net.sf.anathema.character.generic.rules;

public interface IRuleSetVisitor {

  public void visitCoreRules(IExaltedRuleSet set);

  public void visitPowerCombat(IExaltedRuleSet set);

  public void visitSecondEdition(IExaltedRuleSet set);
}