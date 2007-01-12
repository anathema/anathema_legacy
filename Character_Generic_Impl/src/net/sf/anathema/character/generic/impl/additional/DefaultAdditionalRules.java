package net.sf.anathema.character.generic.impl.additional;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DefaultAdditionalRules implements IAdditionalRules {

  private final String[] rejectedBackgrounds;
  private final List<IAdditionalBonusPointPool> additonalBonusPointPools = new ArrayList<IAdditionalBonusPointPool>();
  private final List<IAdditionalMagicLearnPool> additonalMagicLearnPools = new ArrayList<IAdditionalMagicLearnPool>();
  private final List<IAdditionalEssencePool> additonalEssencePools = new ArrayList<IAdditionalEssencePool>();
  private final IAdditionalTraitRules traitRules;

  public DefaultAdditionalRules(String... rejectedBackgroundIds) {
    this(new DefaultAdditionalTraitRules(), rejectedBackgroundIds);
  }

  protected DefaultAdditionalRules(IAdditionalTraitRules traitRules, String... rejectedBackgroundIds) {
    this.rejectedBackgrounds = rejectedBackgroundIds;
    this.traitRules = traitRules;
  }

  public final IAdditionalBonusPointPool[] getAdditionalBonusPointPools() {
    return additonalBonusPointPools.toArray(new IAdditionalBonusPointPool[additonalBonusPointPools.size()]);
  }

  public final boolean isRejected(IBackgroundTemplate backgroundTemplate) {
    return ArrayUtilities.contains(rejectedBackgrounds, backgroundTemplate.getId());
  }

  public IAdditionalMagicLearnPool[] getAdditionalMagicLearnPools() {
    return additonalMagicLearnPools.toArray(new IAdditionalMagicLearnPool[additonalMagicLearnPools.size()]);
  }

  protected final void addMagicLearnPool(IAdditionalMagicLearnPool magicLearnPool) {
    additonalMagicLearnPools.add(magicLearnPool);
  }

  protected final void addBonusPointPool(IAdditionalBonusPointPool bonusPointPool) {
    additonalBonusPointPools.add(bonusPointPool);
  }

  protected final void addEssencePool(IAdditionalEssencePool essencePool) {
    additonalEssencePools.add(essencePool);
  }

  public IAdditionalEssencePool[] getAdditionalEssencePools() {
    return additonalEssencePools.toArray(new IAdditionalEssencePool[additonalEssencePools.size()]);
  }

  public ITraitCostModifier getCostModifier(ITraitType type) {
    return new DefaultTraitCostModifier();
  }

  public String[] getCompulsiveCharmIDs() {
    return new String[0];
  }

  public IAdditionalTraitRules getAdditionalTraitRules() {
    return traitRules;
  }
}