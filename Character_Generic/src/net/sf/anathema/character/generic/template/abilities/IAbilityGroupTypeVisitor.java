package net.sf.anathema.character.generic.template.abilities;

public interface IAbilityGroupTypeVisitor {

  public void visitLife(AbilityGroupType type);

  public void visitWisdom(AbilityGroupType type);

  public void visitWar(AbilityGroupType type);

}
