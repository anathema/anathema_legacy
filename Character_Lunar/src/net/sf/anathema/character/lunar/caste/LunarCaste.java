package net.sf.anathema.character.lunar.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum LunarCaste implements ICasteType {

  FullMoon, ChangingMoon, NoMoon, WaxingMoon, HalfMoon, WaningMoon;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
  
  public static ICasteType[] getFirstEditionValues()
  {
	  return new ICasteType[] { FullMoon, ChangingMoon, NoMoon };
  }
}