package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.hero.charms.model.special.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.oxbody.IOxBodyTechniqueCharm;
import net.sf.anathema.hero.charms.model.special.paintolerance.IPainToleranceCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.model.special.traitcap.ITraitCapModifyingCharm;
import net.sf.anathema.hero.charms.model.special.upgradable.IUpgradableCharm;

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