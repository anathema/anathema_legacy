package net.sf.anathema.hero.health.model;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.health.HealthLevelType;
import net.sf.anathema.hero.health.IHealthLevelTypeVisitor;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HealthModelImpl implements HealthModel {

  private final List<IHealthLevelProvider> healthLevelProviders = new ArrayList<>();
  private final List<IPainToleranceProvider> painResistanceProviders = new ArrayList<>();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    addHealthLevelProvider(new DyingStaminaHealthLevelProvider(TraitModelFetcher.fetch(hero)));
  }

  @Override
  public void initializeListening(ChangeAnnouncer changeAnnouncer) {
    // nothing to do
  }

  @Override
  public void addHealthLevelProvider(IHealthLevelProvider provider) {
    healthLevelProviders.add(provider);
  }

  @Override
  public void addPainToleranceProvider(IPainToleranceProvider provider) {
    painResistanceProviders.add(provider);
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    int typeCount = getBasicHealthLevel(type);
    for (IHealthLevelProvider provider : healthLevelProviders) {
      typeCount += provider.getHealthLevelTypeCount(type);
    }
    return typeCount;
  }

  private int getBasicHealthLevel(HealthLevelType type) {
    final int[] basicCount = new int[1];
    type.accept(new IHealthLevelTypeVisitor() {
      @Override
      public void visitZero(HealthLevelType visitedType) {
        basicCount[0] = 1;
      }

      @Override
      public void visitOne(HealthLevelType visitedType) {
        basicCount[0] = 2;
      }

      @Override
      public void visitTwo(HealthLevelType visitedType) {
        basicCount[0] = 2;
      }

      @Override
      public void visitFour(HealthLevelType visitedType) {
        basicCount[0] = 1;
      }

      @Override
      public void visitIncapacitated(HealthLevelType visitedType) {
        basicCount[0] = 1;
      }

      @Override
      public void visitDying(HealthLevelType visitedType) {
        basicCount[0] = 0;
      }
    });
    return basicCount[0];
  }

  @Override
  public int getPainToleranceLevel() {
    int painToleranceLevel = 0;
    for (IPainToleranceProvider provider : painResistanceProviders) {
      painToleranceLevel = Math.max(painToleranceLevel, provider.getPainToleranceLevel());
    }
    return painToleranceLevel;
  }

  @Override
  public TraitType[] getToughnessControllingTraitTypes() {
    return new TraitType[] {AbilityType.Resistance};
  }

  private static class DyingStaminaHealthLevelProvider implements IHealthLevelProvider {
    private final TraitMap traits;

    public DyingStaminaHealthLevelProvider(TraitMap config) {
      this.traits = config;
    }

    @Override
    public int getHealthLevelTypeCount(HealthLevelType type) {
      if (type == HealthLevelType.DYING) {
        return traits.getTrait(AttributeType.Stamina).getCurrentValue();
      }
      return 0;
    }
  }
}