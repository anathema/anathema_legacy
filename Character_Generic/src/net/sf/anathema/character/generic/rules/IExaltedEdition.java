package net.sf.anathema.character.generic.rules;

import net.sf.anathema.character.generic.caste.ITypedDescriptionType;

public interface IExaltedEdition extends ITypedDescriptionType {

  public void accept(IEditionVisitor visitor);

  public IExaltedRuleSet getDefaultRuleset();
}