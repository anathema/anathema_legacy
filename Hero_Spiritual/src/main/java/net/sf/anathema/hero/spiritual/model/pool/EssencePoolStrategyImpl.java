package net.sf.anathema.hero.spiritual.model.pool;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;
import org.jmock.example.announcer.Announcer;

public class EssencePoolStrategyImpl implements EssencePoolStrategy {

  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private OverdrivePool overdrivePool;
  private Hero hero;
  private EssencePoolConfiguration configuration;
  private final TraitMap traitMap;

  public EssencePoolStrategyImpl(Hero hero, EssencePoolConfiguration configuration, TraitMap traitMap, OverdrivePool overdrivePool) {
    this.hero = hero;
    this.configuration = configuration;
    this.traitMap = traitMap;
    this.overdrivePool = overdrivePool;
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        control.announce().changeOccurred();
      }
    });
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
    return getPool(configuration.getPersonalTraits(traitMap));
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
    return getPool(configuration.getPeripheralTraits(traitMap));
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

  private int getPool(FactorizedTrait[] factorizedTraits) {
    return new FactorizedTraitSumCalculator().calculateSum(factorizedTraits);
  }
}