package net.sf.anathema.character.main.traits.types;

import net.sf.anathema.character.main.traits.TraitType;

public interface ITraitTypeVisitor {

  void visitAbility(AbilityType type);

  void visitAttribute(AttributeType type);

  void visitVirtue(VirtueType type);

  void visitEssence(OtherTraitType type);

  void visitWillpower(OtherTraitType type);

  void visitCustomTraitType(TraitType visitedType);
}