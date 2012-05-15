package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitTypeVisitor {

  void visitAbility(AbilityType type);

  void visitAttribute(AttributeType type);

  void visitVirtue(VirtueType type);

  void visitEssence(OtherTraitType type);

  void visitWillpower(OtherTraitType type);
  
  void visitYozi(YoziType type);

  void visitBackground(IBackgroundTemplate template);

  void visitCustomTraitType(ITraitType visitedType);
}