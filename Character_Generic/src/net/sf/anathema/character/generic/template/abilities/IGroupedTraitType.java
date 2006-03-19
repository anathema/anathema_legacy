package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface IGroupedTraitType {

  public ITraitType geTraitType();
  
  public String getGroupId();
  
  public String getCasteId();
}