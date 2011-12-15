package net.sf.anathema.character.generic.framework.xml.rules;

import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultTraitCostModifier;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.collection.DefaultValueHashMap;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericAdditionalRules extends NullAdditionalRules implements ICloneable<GenericAdditionalRules> {

  private boolean revisedIntimacies = false;
  private boolean willpowerVirtueBased = true;
  private String[] compulsiveCharmIds = new String[0];
  private String[] rejectedBackgroundIds = new String[0];
  private IAdditionalEssencePool[] essencePools = new IAdditionalEssencePool[0];
  private IAdditionalMagicLearnPool[] magicPools = new IAdditionalMagicLearnPool[0];
  private final Map<String, ITraitCostModifier> backgroundCostModifiers = new DefaultValueHashMap<String, ITraitCostModifier>(
      new DefaultTraitCostModifier());

  public void setCompulsiveCharmIds(String[] compulsiveCharmIds) {
    this.compulsiveCharmIds = compulsiveCharmIds;
  }
  
  public void addCompulsiveCharmIds(String[] compulsiveCharmIds) {
    this.compulsiveCharmIds = ArrayUtilities.concat(String.class, this.compulsiveCharmIds, compulsiveCharmIds);
  }

  @Override
  public String[] getCompulsiveCharmIDs() {
    return compulsiveCharmIds;
  }

  public void setAdditionalEssencePools(IAdditionalEssencePool[] pools) {
    this.essencePools = pools;
  }

  public void addAdditionalEssencePools(IAdditionalEssencePool[] pools) {
    this.essencePools = ArrayUtilities.concat(IAdditionalEssencePool.class, this.essencePools, pools);
  }

  @Override
  public IAdditionalEssencePool[] getAdditionalEssencePools() {
    return essencePools;
  }

  @Override
  public IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools() {
    return magicPools;
  }

  public void setMagicPools(IAdditionalMagicLearnPool[] magicPools) {
    this.magicPools = magicPools;
  }
  
  public void addMagicPools(IAdditionalMagicLearnPool[] magicPools) {
    this.magicPools = ArrayUtilities.concat(IAdditionalMagicLearnPool.class, this.magicPools, magicPools);
  }

  @Override
  public boolean isRejected(IBackgroundTemplate backgroundTemplate) {
    return ArrayUtilities.containsValue(rejectedBackgroundIds, backgroundTemplate.getId());
  }

  public void setRejectedBackgrounds(String[] backgroundIds) {
    this.rejectedBackgroundIds = backgroundIds;
  }
  
  public void addRejectedBackgrounds(String[] backgroundIds) {
    this.rejectedBackgroundIds = ArrayUtilities.concat(String.class, this.rejectedBackgroundIds, rejectedBackgroundIds);
  }
  
  @Override
  public boolean isRevisedIntimacies() {
    return revisedIntimacies;
  }
  
  public void setRevisedIntimacies(boolean revisedIntimacies) {
    this.revisedIntimacies = revisedIntimacies;
  }
  
  @Override
  public boolean isWillpowerVirtueBased() {
    return willpowerVirtueBased;
  }
  
  public void setWillpowerVirtueBased(boolean willpowerVirtueBased) {
    this.willpowerVirtueBased = willpowerVirtueBased;
  }

  @Override
  public ITraitCostModifier getCostModifier(ITraitType type) {
    final ITraitCostModifier[] modifier = new ITraitCostModifier[1];
    type.accept(new TraitTypeAdapter() {
      @Override
      public void visitBackground(IBackgroundTemplate template) {
        modifier[0] = backgroundCostModifiers.get(template.getId());
      }
    });
    return modifier[0];
  }

  public void addBackgroundCostModifier(String backgroundId, ITraitCostModifier modifier) {
    backgroundCostModifiers.put(backgroundId, modifier);
  }

  @Override
  public GenericAdditionalRules clone() {
    try {
      GenericAdditionalRules clonedRules;
      clonedRules = (GenericAdditionalRules) super.clone();
      clonedRules.backgroundCostModifiers.putAll(backgroundCostModifiers);
      return clonedRules;
    }
    catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException();
    }
  }
}