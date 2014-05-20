package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitTemplateCollection;
import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.ITraitTypeVisitor;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;

public class TraitTemplateCollection implements ITraitTemplateCollection {

  private final ITraitTemplateFactory templateFactory;

  public TraitTemplateCollection(ITraitTemplateFactory templateFactory) {
    this.templateFactory = templateFactory;
  }

  @Override
  public final ITraitTemplate getTraitTemplate(TraitType traitType) {
    final ITraitTemplate[] traitTemplate = new ITraitTemplate[1];
    traitType.accept(new ITraitTypeVisitor() {

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
        throw new UnsupportedOperationException("Use new template mechanism");
      }

      @Override
      public void visitCustomTraitType(TraitType visitedType) {
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