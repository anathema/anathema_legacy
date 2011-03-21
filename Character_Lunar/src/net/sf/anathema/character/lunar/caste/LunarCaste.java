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
  
  public static ICasteType[] getModernValues()
  {
	  return new ICasteType[] { FullMoon, ChangingMoon, NoMoon };
  }
  
  public static ICasteType[] getDreamsValues()
  {
	  return new ICasteType[] { FullMoon, WaxingMoon, HalfMoon, WaningMoon, NoMoon };
  }
}