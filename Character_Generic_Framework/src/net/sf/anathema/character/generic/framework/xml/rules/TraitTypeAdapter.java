package net.sf.anathema.character.generic.framework.xml.rules;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class TraitTypeAdapter implements ITraitTypeVisitor {

  @Override
  public void visitAbility(AbilityType type) {
    // Nothing to do
  }

  @Override
  public void visitAttribute(AttributeType type) {
    // Nothing to do
  }

  @Override
  public void visitVirtue(VirtueType type) {
    // Nothing to do
  }

  @Override
  public void visitEssence(OtherTraitType type) {
    // Nothing to do
  }

  @Override
  public void visitWillpower(OtherTraitType type) {
    // Nothing to do
  }
  
  @Override
  public void visitYozi(YoziType type) {
	    // Nothing to do
	  }

  @Override
  public void visitBackground(IBackgroundTemplate template) {
    // Nothing to do
  }

  @Override
  public void visitCustomTraitType(ITraitType visitedType) {
    // Nothing to do
  }
}