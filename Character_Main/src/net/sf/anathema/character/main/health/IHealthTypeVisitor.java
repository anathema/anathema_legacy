package net.sf.anathema.character.main.health;

public interface IHealthTypeVisitor {

  void visitBashing(HealthType bashing);

  void visitLethal(HealthType lethal);

  void visitAggravated(HealthType aggrevated);
}