package net.sf.anathema.character.generic.template.abilities;

import java.util.List;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface IGroupedTraitType {

  public ITraitType getTraitType();

  public String getGroupId();

  public String getGroupCasteId();
  
  public List<String> getTraitCasteSet();
}