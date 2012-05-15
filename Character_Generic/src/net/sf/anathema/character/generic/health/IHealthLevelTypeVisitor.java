package net.sf.anathema.character.generic.health;

public interface IHealthLevelTypeVisitor {

  void visitZero(HealthLevelType type);

  void visitOne(HealthLevelType type);

  void visitTwo(HealthLevelType type);

  void visitFour(HealthLevelType type);

  void visitIncapacitated(HealthLevelType type);
  
  void visitDying(HealthLevelType type);
}