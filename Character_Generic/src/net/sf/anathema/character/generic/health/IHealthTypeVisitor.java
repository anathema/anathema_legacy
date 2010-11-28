package net.sf.anathema.character.generic.health;

public interface IHealthTypeVisitor {

  void visitBashing(HealthType bashing);

  void visitLethal(HealthType lethal);

  void visitAggravated(HealthType aggrevated);

}
