package net.sf.anathema.character.dummy.trait;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
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
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyTraitContext implements ITraitContext {

  private class DummyLimitationContext implements ILimitationContext {

    @Override
    public ITraitLimitation getEssenceLimitation() {
      return new StaticTraitLimitation(7);
    }

    @Override
    public ICasteType getCasteType() {
      return null;
    }

    @Override
    public IGenericTraitCollection getTraitCollection() {
      return new IGenericTraitCollection() {
        @Override
        public IGenericTrait getTrait(ITraitType type) {
          return traitCollection.getTrait(type);
        }

        @Override
        public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
          throw new NotYetImplementedException();
        }

        @Override
        public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
          return traitCollection.getFavorableTrait(type);
        }

        @Override
        public boolean isFavoredOrCasteTrait(ITraitType type) {
          return traitCollection.isFavoredOrCasteTrait(type);
        }
      };
    }
    
    @Override
    public int getEssenceCap(boolean modified)
    {
  	  return 0;
    }
    
    @Override
    public int getAge() {
      return 0;
    }
  }

  private ITraitValueStrategy traitValueStrategy = new CreationTraitValueStrategy();
  private final ICoreTraitConfiguration traitCollection;
  private final ILimitationContext limitationContext = new DummyLimitationContext();

  public DummyTraitContext(ICoreTraitConfiguration traitCollection) {
    this.traitCollection = traitCollection;
  }

  @Override
  public ITraitValueStrategy getTraitValueStrategy() {
    return traitValueStrategy;
  }

  @Override
  public ILimitationContext getLimitationContext() {
    return limitationContext;
  }

  public ICoreTraitConfiguration getTraitCollection() {
    return traitCollection;
  }
}