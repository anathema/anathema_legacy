package net.sf.anathema.character.sidereal.caste;

import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface ISiderealCasteVisitor extends ICasteTypeVisitor {

  public void visitJourneys(SiderealCaste visitedCaste);

  public void visitSerenity(SiderealCaste visitedCaste);

  public void visitBattles(SiderealCaste visitedCaste);

  public void visitSecrets(SiderealCaste visitedCaste);

  public void visitEndings(SiderealCaste visitedCaste);
}