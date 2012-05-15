package net.sf.anathema.character.generic.template.abilities;

public interface IAbilityGroupTypeVisitor {

  void visitLife(AbilityGroupType type);

  void visitWisdom(AbilityGroupType type);

  void visitWar(AbilityGroupType type);

}
