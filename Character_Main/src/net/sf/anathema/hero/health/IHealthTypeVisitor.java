package net.sf.anathema.hero.health;

public interface IHealthTypeVisitor {

  void visitBashing(HealthType bashing);

  void visitLethal(HealthType lethal);

  void visitAggravated(HealthType aggrevated);
}