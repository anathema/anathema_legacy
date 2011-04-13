package net.sf.anathema.character.infernal.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum InfernalCaste implements ICasteType {

  Slayer, Malefactor, Defiler, Scourge, Fiend, LateToTheParty;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
  
  public static ICasteType[] casteList()
  {
	  return new ICasteType[] { Slayer, Malefactor, Defiler, Scourge, Fiend };
  }
}