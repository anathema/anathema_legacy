package net.sf.anathema.character.generic.framework.xml.essence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericEssenceTemplate extends ReflectionCloneableObject<GenericEssenceTemplate> implements
    IEssenceTemplate {

  private boolean isEssenceUser = false;
  private IEssencePoolConfiguration personalPoolConfiguration;
  private IEssencePoolConfiguration peripheralPoolConfiguration;

  private FactorizedTrait[] createFactorizedTraits(
      IEssencePoolConfiguration poolConfiguration,
      IGenericTrait willpower,
      IGenericTrait[] virtues,
      IGenericTrait essence) {
    if (poolConfiguration == null) {
      return new FactorizedTrait[0];
    }
    List<FactorizedTrait> traits = new ArrayList<FactorizedTrait>();
    traits.add(new FactorizedTrait(essence, poolConfiguration.getEssenceMultiplier()));
    traits.add(new FactorizedTrait(willpower, poolConfiguration.getWillpowerMultiplier()));
    Collections.addAll(traits, poolConfiguration.createVirtueFactorizedTrait(virtues));
    return traits.toArray(new FactorizedTrait[traits.size()]);
  }

  public FactorizedTrait[] getPersonalTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return createFactorizedTraits(personalPoolConfiguration, willpower, virtues, essence);
  }

  public FactorizedTrait[] getPeripheralTraits(IGenericTrait willpower, IGenericTrait[] virtues, IGenericTrait essence) {
    return createFactorizedTraits(peripheralPoolConfiguration, willpower, virtues, essence);
  }

  public boolean isEssenceUser() {
    return isEssenceUser;
  }

  public void setPersonalPoolConfiguration(IEssencePoolConfiguration configuration) {
    this.personalPoolConfiguration = configuration;
  }

  public void setPeripheralPoolConfiguration(IEssencePoolConfiguration configuration) {
    this.peripheralPoolConfiguration = configuration;
  }

  public void setEssenceUser(boolean isEssenceUser) {
    this.isEssenceUser = isEssenceUser;
  }
}