package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.framework.essence.IEssencePoolModifier;
import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.FactorizedTraitSumCalculator;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.main.model.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.character.main.model.essencepool.OverdrivePool;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.magic.MagicCollection;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;
import org.jmock.example.announcer.Announcer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class EssencePoolStrategyImpl implements EssencePoolStrategy {

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final IEssenceTemplate essenceTemplate;
  private OverdrivePool overdrivePool;
  private final IAdditionalRules additionalRules;
  private Hero hero;
  private final TraitMap traitMap;
  private final MagicCollection magicCollection;
  private final InitializationContext context;

  public EssencePoolStrategyImpl(Hero hero, IEssenceTemplate essenceTemplate, InitializationContext context, TraitMap traitMap,
                                 MagicCollection magicCollection, OverdrivePool overdrivePool, IAdditionalRules additionalRules) {
    this.hero = hero;
    this.traitMap = traitMap;
    this.magicCollection = magicCollection;
    this.overdrivePool = overdrivePool;
    this.additionalRules = additionalRules;
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        control.announce().changeOccurred();
      }
    });
    this.context = context;
    this.essenceTemplate = essenceTemplate;
  }

  @Override
  public void addPoolChangeListener(IChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public int getFullPersonalPool() {
    int additionalPool = 0;
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      additionalPool += pool.getAdditionalPersonalPool(traitMap, magicCollection);
    }
    return getUnmodifiedPersonalPool() + additionalPool;
  }

  @Override
  public int getExtendedPersonalPool() {
    int additionalPool = 0;
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      if (!pool.modifiesBasePool()) {
        additionalPool += pool.getAdditionalPersonalPool(traitMap, magicCollection);
      }
    }
    return getStandardPersonalPool() + additionalPool;
  }

  @Override
  public int getStandardPersonalPool() {
    int personal = getUnmodifiedPersonalPool();
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      if (pool.modifiesBasePool()) {
        personal += pool.getAdditionalPersonalPool(traitMap, magicCollection);
      }
    }
    return personal - Math.max(0, getAttunementExpenditures() - getUnmodifiedPeripheralPool());
  }

  @Override
  public int getUnmodifiedPersonalPool() {
    return getPool(essenceTemplate.getPersonalTraits(getWillpower(), getVirtues(), getEssence()));
  }

  @Override
  public int getFullPeripheralPool() {
    int additionalPool = 0;
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      additionalPool += pool.getAdditionalPeripheralPool(traitMap, magicCollection);
    }
    return getUnmodifiedPeripheralPool() + additionalPool;
  }

  @Override
  public int getExtendedPeripheralPool() {
    int additionalPool = 0;
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      if (!pool.modifiesBasePool()) {
        additionalPool += pool.getAdditionalPeripheralPool(traitMap, magicCollection);
      }
    }
    return getStandardPeripheralPool() + additionalPool;
  }

  @Override
  public int getStandardPeripheralPool() {
    int peripheral = getUnmodifiedPeripheralPool();
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      if (pool.modifiesBasePool()) {
        peripheral += pool.getAdditionalPeripheralPool(traitMap, magicCollection);
      }
    }
    return Math.max(0, peripheral - getAttunementExpenditures());
  }

  @Override
  public int getUnmodifiedPeripheralPool() {
    return getPool(essenceTemplate.getPeripheralTraits(getWillpower(), getVirtues(), getEssence()));
  }

  @Override
  public int getOverdrivePool() {
    return overdrivePool.getPool();
  }

  @Override
  public IdentifiedInteger[] getComplexPools() {
    Map<String, Integer> complexPools = new HashMap<>();
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      for (IdentifiedInteger complexPool : pool.getAdditionalComplexPools(traitMap, magicCollection)) {
        String id = complexPool.getId();
        int value = complexPool.getValue();
        if (complexPools.containsKey(id)) {
          value += complexPools.get(id);
        }
        complexPools.put(id, value);
      }
    }
    IdentifiedInteger[] r = new IdentifiedInteger[complexPools.size()];
    int i = 0;
    for (Entry<String, Integer> entry : complexPools.entrySet()) {
      r[i] = new IdentifiedInteger(entry.getKey(), entry.getValue());
      i++;
    }
    return r;
  }

  @Override
  public int getAttunementExpenditures() {
    int expenditure = 0;
    for (IEssencePoolModifier modifier : EssencePoolModelFetcher.fetch(hero).getEssencePoolModifiers()) {
      expenditure += modifier.getMotesExpended();
    }
    return expenditure;
  }

  private GenericTrait[] getVirtues() {
    return new GenericTrait[]{traitMap.getTrait(VirtueType.Compassion), traitMap.getTrait(VirtueType.Conviction),
            traitMap.getTrait(VirtueType.Temperance), traitMap.getTrait(VirtueType.Valor)};
  }

  private GenericTrait getWillpower() {
    return traitMap.getTrait(OtherTraitType.Willpower);
  }

  private GenericTrait getEssence() {
    return traitMap.getTrait(OtherTraitType.Essence);
  }

  private int getPool(FactorizedTrait[] factorizedTraits) {
    return new FactorizedTraitSumCalculator().calculateSum(factorizedTraits);
  }
}