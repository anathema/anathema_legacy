package net.sf.anathema.character.generic.health;

public interface IHealthLevelTypeVisitor {

  public void visitZero(HealthLevelType type);

  public void visitOne(HealthLevelType type);

  public void visitTwo(HealthLevelType type);

  public void visitFour(HealthLevelType type);

  public void visitIncapacitated(HealthLevelType type);
}