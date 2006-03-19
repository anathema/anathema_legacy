package net.sf.anathema.character.lunar.caste;

import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface ILunarCasteVisitor extends ICasteTypeVisitor {

  public void visitNoMoon(LunarCaste visitedCaste);

  public void visitChangingMoon(LunarCaste visitedCaste);

  public void visitFullMoon(LunarCaste visitedCaste);
}