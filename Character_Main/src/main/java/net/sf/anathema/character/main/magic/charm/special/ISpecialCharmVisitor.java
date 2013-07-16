package net.sf.anathema.character.main.magic.charm.special;

public interface ISpecialCharmVisitor {

  void visitMultiLearnableCharm(IMultiLearnableCharm charm);

  void visitOxBodyTechnique(IOxBodyTechniqueCharm charm);

  void visitPainToleranceCharm(IPainToleranceCharm charm);

  void visitSubEffectCharm(ISubEffectCharm charm);
  
  void visitUpgradableCharm(IUpgradableCharm charm);

  void visitMultipleEffectCharm(IMultipleEffectCharm charm);
  
  void visitPrerequisiteModifyingCharm(IPrerequisiteModifyingCharm charm);
  
  void visitTraitCapModifyingCharm(ITraitCapModifyingCharm charm);
}