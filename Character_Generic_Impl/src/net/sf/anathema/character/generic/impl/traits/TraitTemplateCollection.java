package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class TraitTemplateCollection implements ITraitTemplateCollection {

  private final ITraitTemplateFactory templateFactory;

  public TraitTemplateCollection(ITraitTemplateFactory templateFactory) {
    this.templateFactory = templateFactory;
  }

  @Override
  public final ITraitTemplate getTraitTemplate(ITraitType traitType) {
    final ITraitTemplate[] traitTemplate = new ITraitTemplate[1];
    traitType.accept(new ITraitTypeVisitor() {
      @Override
      public void visitBackground(IBackgroundTemplate template) {
        traitTemplate[0] = templateFactory.createBackgroundTemplate(template);
      }

      @Override
      public void visitWillpower(OtherTraitType type) {
        traitTemplate[0] = templateFactory.createWillpowerTemplate();
      }

      @Override
      public void visitEssence(OtherTraitType type) {
        traitTemplate[0] = templateFactory.createEssenceTemplate();
      }

      @Override
      public void visitVirtue(VirtueType type) {
        traitTemplate[0] = templateFactory.createVirtueTemplate(type);
      }

      @Override
      public void visitAttribute(AttributeType type) {
        traitTemplate[0] = templateFactory.createAttributeTemplate(type);
      }

      @Override
      public void visitAbility(AbilityType type) {
        traitTemplate[0] = templateFactory.createAbilityTemplate(type);
      }

      @Override
      public void visitYozi(YoziType type) {
        traitTemplate[0] = templateFactory.createYoziTemplate(type);
      }

      @Override
      public void visitCustomTraitType(ITraitType visitedType) {
        throw new UnsupportedOperationException("Trait Template Collection can't handle custom trait types");
      }
    });
    return traitTemplate[0];
  }

  @Override
  public ITraitTemplateFactory getTraitTemplateFactory() {
    return templateFactory;
  }
}