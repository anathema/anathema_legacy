package net.sf.anathema.character.impl.model.traits.essence;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.FactorizedTraitSumCalculator;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.model.traits.essence.IEssencePoolStrategy;
import net.sf.anathema.character.model.traits.essence.IPoolValueListener;

public class EssencePoolStrategy implements IEssencePoolStrategy {

  private final List<IPoolValueListener> poolListeners = new ArrayList<IPoolValueListener>();
  private final IEssenceTemplate essenceTemplate;
  private final IAdditionalRules additionalRules;
  private final IGenericTraitCollection traitCollection;
  private final IMagicCollection magicCollection;

  public EssencePoolStrategy(
      IEssenceTemplate essenceTemplate,
      ICharacterModelContext context,
      IGenericTraitCollection traitCollection,
      IMagicCollection magicCollection,
      IAdditionalRules additionalRules) {
    this.traitCollection = traitCollection;
    this.magicCollection = magicCollection;
    this.additionalRules = additionalRules;
    context.getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        firePoolsChanged();
      }
    });
    this.essenceTemplate = essenceTemplate;
  }

  private void firePoolsChanged() {
    List<IPoolValueListener> cloneList = new ArrayList<IPoolValueListener>(poolListeners);
    for (IPoolValueListener listener : cloneList) {
      listener.poolsChanged();
    }
  }

  public void addPoolListener(IPoolValueListener listener) {
    poolListeners.add(listener);
  }

  public int getExtendedPersonalPool() {
    int additionalPool = 0;
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      additionalPool += pool.getAdditionaPersonalPool(traitCollection, magicCollection);
    }
    return getStandardPersonalPool() + additionalPool;
  }

  public int getStandardPersonalPool() {
    return getPool(essenceTemplate.getPersonalTraits(getWillpower(), getVirtues(), getEssence()));
  }

  public int getStandardPeripheralPool() {
    return getPool(essenceTemplate.getPeripheralTraits(getWillpower(), getVirtues(), getEssence()));
  }

  private IGenericTrait[] getVirtues() {
    return new IGenericTrait[] {
        traitCollection.getTrait(VirtueType.Compassion),
        traitCollection.getTrait(VirtueType.Conviction),
        traitCollection.getTrait(VirtueType.Temperance),
        traitCollection.getTrait(VirtueType.Valor) };
  }

  private IGenericTrait getWillpower() {
    return traitCollection.getTrait(OtherTraitType.Willpower);
  }

  private IGenericTrait getEssence() {
    return traitCollection.getTrait(OtherTraitType.Essence);
  }

  public int getExtendedPeripheralPool() {
    int additionalPool = 0;
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      additionalPool += pool.getAdditionaPeripheralPool(traitCollection, magicCollection);
    }
    return getStandardPeripheralPool() + additionalPool;
  }

  private int getPool(FactorizedTrait[] factorizedTraits) {
    return new FactorizedTraitSumCalculator().calculateSum(factorizedTraits);
  }
}