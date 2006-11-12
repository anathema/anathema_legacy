package net.sf.anathema.character.sidereal.caste;

public interface ISiderealCasteVisitor {

  public void visitJourneys(SiderealCaste visitedCaste);

  public void visitSerenity(SiderealCaste visitedCaste);

  public void visitBattles(SiderealCaste visitedCaste);

  public void visitSecrets(SiderealCaste visitedCaste);

  public void visitEndings(SiderealCaste visitedCaste);
}