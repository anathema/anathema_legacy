package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class TraitTemplateCollection implements ITraitTemplateCollection {

  private final ITraitTemplateFactory templateFactory;

  public TraitTemplateCollection(ITraitTemplateFactory templateFactory) {
    this.templateFactory = templateFactory;
  }

  public final ITraitTemplate getTraitTemplate(ITraitType traitType) {
    final ITraitTemplate[] traitTemplate = new ITraitTemplate[1];
    traitType.accept(new ITraitTypeVisitor() {
      public void visitBackground(IBackgroundTemplate template) {
        traitTemplate[0] = templateFactory.createBackgroundTemplate(template);
      }

      public void visitWillpower(OtherTraitType type) {
        traitTemplate[0] = templateFactory.createWillpowerTemplate();
      }

      public void visitEssence(OtherTraitType type) {
        traitTemplate[0] = templateFactory.createEssenceTemplate();
      }

      public void visitVirtue(VirtueType type) {
        traitTemplate[0] = templateFactory.createVirtueTemplate(type);
      }

      public void visitAttribute(AttributeType type) {
        traitTemplate[0] = templateFactory.createAttributeTemplate(type);
      }

      public void visitAbility(AbilityType type) {
        traitTemplate[0] = templateFactory.createAbilityTemplate(type);
      }

      public void visitCustomTraitType(ITraitType visitedType) {
        throw new UnsupportedOperationException("Trait Template Collection can't handle custom trait types"); //$NON-NLS-1$
      }
    });
    return traitTemplate[0];
  }
}