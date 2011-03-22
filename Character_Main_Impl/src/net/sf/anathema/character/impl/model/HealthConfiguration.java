package net.sf.anathema.character.impl.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.health.IHealthLevelTypeVisitor;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.health.IHealthLevelProvider;
import net.sf.anathema.character.model.health.IOxBodyTechniqueArbitrator;
import net.sf.anathema.character.model.health.IPainToleranceProvider;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class HealthConfiguration implements IHealthConfiguration {

  private final List<IHealthLevelProvider> healthLevelProviders = new ArrayList<IHealthLevelProvider>();
  private final List<IPainToleranceProvider> painResistanceProviders = new ArrayList<IPainToleranceProvider>();
  private final OxBodyTechniqueArbitrator arbitrator;

  public HealthConfiguration(IGenericTrait toughnessControllingTrait,
		  ICoreTraitConfiguration config) {
    this.arbitrator = new OxBodyTechniqueArbitrator(toughnessControllingTrait);
    
    addHealthLevelProvider(new DyingStaminaHealthLevelProvider(config));
  }

  public void addHealthLevelProvider(IHealthLevelProvider provider) {
    healthLevelProviders.add(provider);
  }

  public void addPainToleranceProvider(IPainToleranceProvider provider) {
    painResistanceProviders.add(provider);
  }

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
      public void visitZero(HealthLevelType visitedType) {
        basicCount[0] = 1;
      }

      public void visitOne(HealthLevelType visitedType) {
        basicCount[0] = 2;
      }

      public void visitTwo(HealthLevelType visitedType) {
        basicCount[0] = 2;
      }

      public void visitFour(HealthLevelType visitedType) {
        basicCount[0] = 1;
      }

      public void visitIncapacitated(HealthLevelType visitedType) {
        basicCount[0] = 1;
      }
      
      public void visitDying(HealthLevelType visitedType)
      {
    	  basicCount[0] = 0;
      }
    });
    return basicCount[0];
  }

  public IOxBodyTechniqueArbitrator getOxBodyLearnArbitrator() {
    return arbitrator;
  }

  public int getPainToleranceLevel() {
    int painToleranceLevel = 0;
    for (IPainToleranceProvider provider : painResistanceProviders) {
      painToleranceLevel = Math.max(painToleranceLevel, provider.getPainToleranceLevel());
    }
    return painToleranceLevel;
  }
  
  private class DyingStaminaHealthLevelProvider implements IHealthLevelProvider
  {
	  private final ICoreTraitConfiguration traits;
	  
	  public DyingStaminaHealthLevelProvider(ICoreTraitConfiguration config)
	  {
		  this.traits = config;
	  }

		@Override
		public int getHealthLevelTypeCount(HealthLevelType type) {
			if (type == HealthLevelType.DYING)
				return traits.getTrait(AttributeType.Stamina).getCurrentValue();
			return 0;
		}
	  
  }
}