package net.sf.anathema.character.generic.magic.charms.special;

public interface ISpecialCharmVisitor {

  public void visitMultiLearnableCharm(IMultiLearnableCharm charm);

  public void acceptOxBodyTechnique(IOxBodyTechniqueCharm charm);

  public void visitPainToleranceCharm(IPainToleranceCharm charm);
}