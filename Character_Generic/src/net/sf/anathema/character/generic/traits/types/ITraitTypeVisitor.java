package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface ITraitTypeVisitor {

  public void visitAbility(AbilityType type);

  public void visitAttribute(AttributeType type);

  public void visitVirtue(VirtueType type);

  public void visitEssence(OtherTraitType type);

  public void visitWillpower(OtherTraitType type);

  public void visitBackground(IBackgroundTemplate template);

  public void visitCustomTraitType(ITraitType visitedType);
}