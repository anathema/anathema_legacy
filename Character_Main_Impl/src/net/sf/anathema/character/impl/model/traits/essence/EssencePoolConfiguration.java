package net.sf.anathema.character.impl.model.traits.essence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolStrategy;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class EssencePoolConfiguration implements IEssencePoolConfiguration {

  private IEssencePoolStrategy poolStrategy = null;
  private final IAdditionalRules additionalRules;
  private final IEssenceTemplate essenceTemplate;

  public EssencePoolConfiguration(
      IEssenceTemplate essenceTemplate,
      IAdditionalRules additionalRules,
      ICharacterModelContext context) {
    this.additionalRules = additionalRules;
    this.essenceTemplate = essenceTemplate;
    if (!isEssenceUser()) {
      return;
    }
    poolStrategy = new EssencePoolStrategy(
        essenceTemplate,
        context,
        context.getTraitCollection(),
        context.getMagicCollection(),
        context.getCharmContext().getCharmConfiguration(),
        additionalRules);
  }

  public String getPersonalPool() {
    Ensure.ensureTrue("No Essence User", isEssenceUser()); //$NON-NLS-1$
    if (additionalRules.getAdditionalEssencePools().length == 0) {
      return String.valueOf(poolStrategy.getStandardPersonalPool());
    }
    return poolStrategy.getStandardPersonalPool() + " (" + poolStrategy.getExtendedPersonalPool() + ")"; //$NON-NLS-1$//$NON-NLS-2$
  }
  
  public int getPersonalPoolValue() {
    return poolStrategy.getFullPersonalPool();
  }

  public String getPeripheralPool() {
    Ensure.ensureTrue("No Peripheral Pool", hasPeripheralPool()); //$NON-NLS-1$
    if (additionalRules.getAdditionalEssencePools().length == 0) {
      return String.valueOf(poolStrategy.getStandardPeripheralPool());
    }
    return poolStrategy.getStandardPeripheralPool() + " (" + poolStrategy.getExtendedPeripheralPool() + ")"; //$NON-NLS-1$//$NON-NLS-2$
  }
  
  public int getPeripheralPoolValue() {
    return poolStrategy.getFullPeripheralPool();
  }
  
  public int getOverdrivePoolValue() {
    return poolStrategy.getOverdrivePool();
  }
  
  public IdentifiedInteger[] getComplexPools() {
    return poolStrategy.getComplexPools();
  }
  
  public String getAttunedPool()
  {
    return "" + poolStrategy.getAttunementExpenditures();
  }
  
  public int getAttunedPoolValue() {
    return poolStrategy.getAttunementExpenditures();
  }

  public boolean isEssenceUser() {
    return essenceTemplate.isEssenceUser();
  }

  public boolean hasPeripheralPool() {
    return isEssenceUser() && (poolStrategy.getExtendedPeripheralPool() != 0 ||
    		poolStrategy.getUnmodifiedPeripheralPool() != 0);
  }

  public void addPoolChangeListener(IChangeListener listener) {
    poolStrategy.addPoolChangeListener(listener);
  }
}