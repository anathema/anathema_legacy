package net.sf.anathema.character.generic.rules;

import net.sf.anathema.character.generic.caste.ITypedDescriptionType;

public interface IExaltedRuleSet extends ITypedDescriptionType {

  public void accept(IRuleSetVisitor visitor);
  
  public IExaltedEdition getEdition();
}