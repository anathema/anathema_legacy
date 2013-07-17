package net.sf.anathema.hero.charms.model.special.charms;

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