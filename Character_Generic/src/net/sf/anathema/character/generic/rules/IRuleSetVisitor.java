package net.sf.anathema.character.generic.rules;

public interface IRuleSetVisitor {

  void visitCoreRules(IExaltedRuleSet set);

  void visitSecondEdition(IExaltedRuleSet set);
}