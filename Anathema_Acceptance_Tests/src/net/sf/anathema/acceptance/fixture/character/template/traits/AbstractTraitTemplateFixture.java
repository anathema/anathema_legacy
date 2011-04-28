package net.sf.anathema.acceptance.fixture.character.template.traits;

import net.sf.anathema.acceptance.fixture.character.template.AbstractTemplateColumnFixture;
import net.sf.anathema.acceptance.fixture.character.template.CharacterTemplateSummary;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public abstract class AbstractTraitTemplateFixture extends AbstractTemplateColumnFixture {

  public String traitType;

  public final int absoluteMaximumValue() {
    final CharacterTemplateSummary templateSummary = new CharacterTemplateSummary(summary);
    ITraitTemplate traitTemplate = templateSummary.getTraitTemplate(getTraitType());
    return traitTemplate.getLimitation().getAbsoluteLimit(new ILimitationContext() {
      public ITraitLimitation getEssenceLimitation() {
        return templateSummary.getTemplate()
            .getTraitTemplateCollection()
            .getTraitTemplate(OtherTraitType.Essence)
            .getLimitation();
      }

      public ICasteType getCasteType() {
        throw new UnsupportedOperationException("No character defined in template tests."); //$NON-NLS-1$
      }

      public IGenericTraitCollection getTraitCollection() {
        return new IGenericTraitCollection() {
          public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
            throw new UnsupportedOperationException("No character defined in template tests."); //$NON-NLS-1$
          }

          public IGenericTrait getTrait(ITraitType type) {
            throw new UnsupportedOperationException("No character defined in template tests."); //$NON-NLS-1$
          }

          public boolean isFavoredOrCasteTrait(ITraitType type) {
            throw new UnsupportedOperationException("No character defined in template tests."); //$NON-NLS-1$
          }

          public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
            throw new UnsupportedOperationException("No character defined in template tests."); //$NON-NLS-1$          }
          }

        };
      }
      
      public int getAge() {
    	  throw new UnsupportedOperationException("No character defined in template tests."); //$NON-NLS-1$;
      }
    });
  }

  protected abstract ITraitType getTraitType();
}
