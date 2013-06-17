package net.sf.anathema.character.main.testing.dummy.trait;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.main.traits.model.GenericTraitCollectionFacade;
import net.sf.anathema.character.main.traits.model.TraitMap;

public class DummyTraitContext implements TraitContext {

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
      return new GenericTraitCollectionFacade(traitCollection);
     }

    @Override
    public int getEssenceCap(boolean modified) {
      return 0;
    }

    @Override
    public int getAge() {
      return 0;
    }
  }

  private ITraitValueStrategy traitValueStrategy = new CreationTraitValueStrategy();
  private final TraitMap traitCollection;
  private final ILimitationContext limitationContext = new DummyLimitationContext();

  public DummyTraitContext(TraitMap traitCollection) {
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
}