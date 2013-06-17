package net.sf.anathema.character.main.essencepool.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

public class EssencePoolModelImpl implements EssencePoolModel {

  private EssencePoolStrategy poolStrategy = null;
  private IAdditionalRules additionalRules;
  private IEssenceTemplate essenceTemplate;

  public EssencePoolModelImpl(HeroTemplate template, ICharacterModelContext context) {
    this.additionalRules = template.getAdditionalRules();
    this.essenceTemplate = template.getEssenceTemplate();
    if (!isEssenceUser()) {
      return;
    }
    poolStrategy = new EssencePoolStrategyImpl(essenceTemplate, context, context.getTraitCollection(), context.getMagicCollection(),
            context.getCharmContext().getCharmConfiguration(), additionalRules);
  }

  @Override
  public String getPersonalPool() {
    Preconditions.checkArgument(isEssenceUser());
    if (!hasAdditionalPools()) {
      return String.valueOf(poolStrategy.getStandardPersonalPool());
    }
    return poolStrategy.getStandardPersonalPool() + " (" + poolStrategy.getExtendedPersonalPool() + ")";
  }

  @Override
  public int getPersonalPoolValue() {
    return poolStrategy.getFullPersonalPool();
  }

  @Override
  public String getPeripheralPool() {
    Preconditions.checkArgument(hasPeripheralPool());
    if (!hasAdditionalPools()) {
      return String.valueOf(poolStrategy.getStandardPeripheralPool());
    }
    return poolStrategy.getStandardPeripheralPool() + " (" + poolStrategy.getExtendedPeripheralPool() + ")";
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
  public String getAttunedPool() {
    return "" + poolStrategy.getAttunementExpenditures();
  }

  @Override
  public int getAttunedPoolValue() {
    return poolStrategy.getAttunementExpenditures();
  }

  private boolean hasAdditionalPools() {
    for (IAdditionalEssencePool pool : additionalRules.getAdditionalEssencePools()) {
      if (!pool.modifiesBasePool()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isEssenceUser() {
    return essenceTemplate.isEssenceUser();
  }

  @Override
  public boolean hasPeripheralPool() {
    return isEssenceUser() && (poolStrategy.getExtendedPeripheralPool() != 0 || poolStrategy.getUnmodifiedPeripheralPool() != 0);
  }

  @Override
  public void addPoolChangeListener(IChangeListener listener) {
    poolStrategy.addPoolChangeListener(listener);
  }
}