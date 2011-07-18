package net.sf.anathema.character.generic.magic.charms.special;

public interface ISpecialCharmVisitor {

  public void visitMultiLearnableCharm(IMultiLearnableCharm charm);

  public void visitOxBodyTechnique(IOxBodyTechniqueCharm charm);

  public void visitPainToleranceCharm(IPainToleranceCharm charm);

  public void visitSubeffectCharm(ISubeffectCharm charm);
  
  public void visitUpgradableCharm(IUpgradableCharm charm);

  public void visitMultipleEffectCharm(IMultipleEffectCharm charm);
  
  public void visitPrerequisiteModifyingCharm(IPrerequisiteModifyingCharm charm);
  
  public void visitTraitCapModifyingCharm(ITraitCapModifyingCharm charm);
}