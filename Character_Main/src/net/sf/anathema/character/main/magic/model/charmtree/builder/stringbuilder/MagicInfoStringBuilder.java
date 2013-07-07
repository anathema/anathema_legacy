package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.magic.ICost;
import net.sf.anathema.character.main.magic.model.magic.ICostList;
import net.sf.anathema.character.main.magic.model.magic.IHealthCost;
import net.sf.anathema.lib.resources.Resources;

public class MagicInfoStringBuilder implements IMagicInfoStringBuilder {

  private final ICostStringBuilder<ICost> essenceBuilder;
  private final ICostStringBuilder<ICost> willpowerBuilder;
  private final ICostStringBuilder<IHealthCost> healthBuilder;
  private final ICostStringBuilder<ICost> experienceBuilder;
  private final MagicInfoStringConcatenator concatenator;

  public MagicInfoStringBuilder(Resources resources, ICostStringBuilder<ICost> essenceBuilder, ICostStringBuilder<ICost> willpowerBuilder,
                                ICostStringBuilder<IHealthCost> healthBuilder, ICostStringBuilder<ICost> experienceBuilder) {
    this.essenceBuilder = essenceBuilder;
    this.willpowerBuilder = willpowerBuilder;
    this.healthBuilder = healthBuilder;
    this.experienceBuilder = experienceBuilder;
    this.concatenator = new MagicInfoStringConcatenator(resources);
  }

  @Override
  public String createCostString(IMagic magic) {
    ICostList temporaryCost = magic.getTemporaryCost();
    String essenceCost = essenceBuilder.getCostString(temporaryCost.getEssenceCost());
    String willpowerCost = willpowerBuilder.getCostString(temporaryCost.getWillpowerCost());
    String healthCost = healthBuilder.getCostString(temporaryCost.getHealthCost());
    String xpCost = experienceBuilder.getCostString(temporaryCost.getXPCost());
    return concatenator.buildCostString(essenceCost, willpowerCost, healthCost, xpCost);
  }
}