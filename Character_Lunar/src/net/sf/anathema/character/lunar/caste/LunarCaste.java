package net.sf.anathema.character.lunar.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum LunarCaste implements ICasteType {

  FullMoon, ChangingMoon, NoMoon, WaxingMoon, HalfMoon, WaningMoon;

  @Override
  public String getId() {
    return name();
  }

  public static ICasteType[] getDreamsValues() {
    return new ICasteType[]{FullMoon, WaxingMoon, HalfMoon, WaningMoon, NoMoon};
  }

  public static ICasteType[] getCastelessValues() {
    return new ICasteType[0];
  }
}