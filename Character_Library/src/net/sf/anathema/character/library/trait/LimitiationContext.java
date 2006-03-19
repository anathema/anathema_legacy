package net.sf.anathema.character.library.trait;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class LimitiationContext implements ILimitationContext {

  private final IGenericCharacter genericCharacter;

  public LimitiationContext(IGenericCharacter genericCharacter) {
    this.genericCharacter = genericCharacter;
  }

  public ITraitLimitation getEssenceLimitation() {
    return genericCharacter.getTemplate()
        .getTraitTemplateCollection()
        .getTraitTemplate(OtherTraitType.Essence)
        .getLimitation();
  }

  public IGenericTrait getEssence() {
    return genericCharacter.getTrait(OtherTraitType.Essence);
  }

  public IGenericTrait getTrait(ITraitType type) {
    return genericCharacter.getTrait(type);
  }

  public ICasteType getCasteType() {
    return genericCharacter.getCasteType();
  }

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    IGenericTrait trait = getTrait(type);
    Ensure.ensureArgumentTrue("No favorable trait " + type, trait instanceof IFavorableGenericTrait); //$NON-NLS-1$
    return (IFavorableGenericTrait) trait;
  }
}