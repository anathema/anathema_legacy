package net.sf.anathema.hero.othertraits.model.pool;

import net.sf.anathema.character.main.essence.IEssencePoolModifier;
import net.sf.anathema.character.main.template.essence.FactorizedTrait;
import net.sf.anathema.character.main.template.essence.FactorizedTraitSumCalculator;
import net.sf.anathema.character.main.template.essence.IEssenceTemplate;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.hero.essencepool.OverdrivePool;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;
import org.jmock.example.announcer.Announcer;

public class EssencePoolStrategyImpl implements EssencePoolStrategy {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private final IEssenceTemplate essenceTemplate;
  private OverdrivePool overdrivePool;
  private Hero hero;
  private final TraitMap traitMap;

  public EssencePoolStrategyImpl(Hero hero, IEssenceTemplate essenceTemplate, TraitMap traitMap, OverdrivePool overdrivePool) {
    this.hero = hero;
    this.traitMap = traitMap;
    this.overdrivePool = overdrivePool;
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        control.announce().changeOccurred();
      }
    });
    this.essenceTemplate = essenceTemplate;
  }

  @Override
  public void addPoolChangeListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public int getFullPersonalPool() {
    return getUnmodifiedPersonalPool();
  }

  @Override
  public int getExtendedPersonalPool() {
    return getStandardPersonalPool();
  }

  @Override
  public int getStandardPersonalPool() {
    int personal = getUnmodifiedPersonalPool();
    return personal - Math.max(0, getAttunementExpenditures() - getUnmodifiedPeripheralPool());
  }

  @Override
  public int getUnmodifiedPersonalPool() {
    return getPool(essenceTemplate.getPersonalTraits(getWillpower(), getVirtues(), getEssence()));
  }

  @Override
  public int getFullPeripheralPool() {
     return getUnmodifiedPeripheralPool();
  }

  @Override
  public int getExtendedPeripheralPool() {
    return getStandardPeripheralPool();
  }

  @Override
  public int getStandardPeripheralPool() {
    int peripheral = getUnmodifiedPeripheralPool();
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
    return new IdentifiedInteger[0];
  }

  @Override
  public int getAttunementExpenditures() {
    int expenditure = 0;
    for (IEssencePoolModifier modifier : EssencePoolModelFetcher.fetch(hero).getEssencePoolModifiers()) {
      expenditure += modifier.getMotesExpended();
    }
    return expenditure;
  }

  private ValuedTraitType[] getVirtues() {
    return new ValuedTraitType[]{traitMap.getTrait(VirtueType.Compassion), traitMap.getTrait(VirtueType.Conviction),
            traitMap.getTrait(VirtueType.Temperance), traitMap.getTrait(VirtueType.Valor)};
  }

  private ValuedTraitType getWillpower() {
    return traitMap.getTrait(OtherTraitType.Willpower);
  }

  private ValuedTraitType getEssence() {
    return traitMap.getTrait(OtherTraitType.Essence);
  }

  private int getPool(FactorizedTrait[] factorizedTraits) {
    return new FactorizedTraitSumCalculator().calculateSum(factorizedTraits);
  }
}