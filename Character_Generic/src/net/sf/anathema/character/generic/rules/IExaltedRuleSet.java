package net.sf.anathema.character.generic.rules;

import net.sf.anathema.character.generic.caste.ITypedDescriptionType;

public interface IExaltedRuleSet extends ITypedDescriptionType {

  void accept(IRuleSetVisitor visitor);

  IExaltedEdition getEdition();

  IExaltedRuleSet getBasicRuleset();
}