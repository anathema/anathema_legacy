package net.sf.anathema.character.impl.model.traits.essence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
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

  @Override
  public String getPersonalPool() {
    Ensure.ensureTrue("No Essence User", isEssenceUser()); //$NON-NLS-1$
    if (!hasAdditionalPools()) {
      return String.valueOf(poolStrategy.getStandardPersonalPool());
    }
    return poolStrategy.getStandardPersonalPool() + " (" + poolStrategy.getExtendedPersonalPool() + ")"; //$NON-NLS-1$//$NON-NLS-2$
  }
  
  @Override
  public int getPersonalPoolValue() {
    return poolStrategy.getFullPersonalPool();
  }

  @Override
  public String getPeripheralPool() {
    Ensure.ensureTrue("No Peripheral Pool", hasPeripheralPool()); //$NON-NLS-1$
    if (!hasAdditionalPools()) {
      return String.valueOf(poolStrategy.getStandardPeripheralPool());
    }
    return poolStrategy.getStandardPeripheralPool() + " (" + poolStrategy.getExtendedPeripheralPool() + ")"; //$NON-NLS-1$//$NON-NLS-2$
  }
  
  @Override
  public int getPeripheralPoolValue() {
    return poolStrategy.getFullPeripheralPool();
  }
  
  @Override
  public int getOverdrivePoolValue() {
    return poolStrategy.getOverdrivePool();
  }
  
  @Override
  public IdentifiedInteger[] getComplexPools() {
    return poolStrategy.getComplexPools();
  }
  
  @Override
  public String getAttunedPool()
  {
    return "" + poolStrategy.getAttunementExpenditures();
  }
  
  @Override
  public int getAttunedPoolValue() {
    return poolStrategy.getAttunementExpenditures();
  }
  
  private boolean hasAdditionalPools()
  {
	  for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools())
		  if (!pool.modifiesBasePool())
			  return true;
	  return false;
  }

  @Override
  public boolean isEssenceUser() {
    return essenceTemplate.isEssenceUser();
  }

  @Override
  public boolean hasPeripheralPool() {
    return isEssenceUser() && (poolStrategy.getExtendedPeripheralPool() != 0 ||
    		poolStrategy.getUnmodifiedPeripheralPool() != 0);
  }

  @Override
  public void addPoolChangeListener(IChangeListener listener) {
    poolStrategy.addPoolChangeListener(listener);
  }
}