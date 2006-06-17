package net.sf.anathema.dummy.character.trait;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class DummyTraitContext implements ITraitContext {
  
  private class DummyLimitationContext implements ILimitationContext {

    public ITraitLimitation getEssenceLimitation() {
      return new StaticTraitLimitation(7);
    }

    public ICasteType getCasteType() {
      return null;
    }

    public IGenericTrait getTrait(ITraitType type) {
      return traitCollection.getTrait(type);
    }
    
    public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
      return traitCollection.getFavorableTrait(type);
    }
  }
  
  private ITraitValueStrategy traitValueStrategy = new CreationTraitValueStrategy();
  private final ICoreTraitConfiguration traitCollection;
  private final ILimitationContext limitationContext = new DummyLimitationContext();
  
  public DummyTraitContext(ICoreTraitConfiguration traitCollection) {
    this.traitCollection = traitCollection;
  }

  public ITraitValueStrategy getTraitValueStrategy() {
    return traitValueStrategy;
  }

  public ILimitationContext getLimitationContext() {
    return limitationContext;
  }
}