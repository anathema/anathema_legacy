package net.sf.anathema.character.abyssal.additional;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalTraitRules;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.data.Range;

public class AbyssalAdditionalTraitRules extends DefaultAdditionalTraitRules {

  @Override
  public boolean isAllowedTraitValue(final IGenericTrait trait, final ILimitationContext limitationContext) {
    final boolean[] allowed = new boolean[1];
    trait.getType().accept(new ITraitTypeVisitor() {
      public void visitAbility(AbilityType type) {
        allowed[0] = true;
      }

      public void visitAttribute(AttributeType type) {
        if (type != AttributeType.Appearance) {
          allowed[0] = true;
        }
        else {
          IGenericTrait essence = limitationContext.getTraitCollection().getTrait(OtherTraitType.Essence);
          allowed[0] = checkAppearanceEssenceRelation(essence, trait);
        }
      }

      public void visitVirtue(VirtueType type) {
        allowed[0] = true;
      }

      public void visitEssence(OtherTraitType type) {
        IGenericTrait appearance = limitationContext.getTraitCollection().getTrait(AttributeType.Appearance);
        allowed[0] = checkAppearanceEssenceRelation(trait, appearance);
      }

      public void visitWillpower(OtherTraitType type) {
        allowed[0] = true;
      }

      public void visitBackground(IBackgroundTemplate template) {
        allowed[0] = true;
      }

      public void visitCustomTraitType(ITraitType type) {
        allowed[0] = true;
      }
    });
    return allowed[0];
  }

  private boolean checkAppearanceEssenceRelation(final IGenericTrait essence, final IGenericTrait appearance) {
    if (essence.getCurrentValue() == 4 && appearance.getCurrentValue() == 2) {
      return false;
    }
    if (essence.getCurrentValue() == 5 && new Range(1, 3).contains(appearance.getCurrentValue())) {
      return false;
    }
    return true;
  }
}