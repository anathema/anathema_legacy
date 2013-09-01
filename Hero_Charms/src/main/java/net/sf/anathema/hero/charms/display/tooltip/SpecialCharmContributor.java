package net.sf.anathema.hero.charms.display.tooltip;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.special.multilearn.AbstractMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.CharmTier;
import net.sf.anathema.hero.charms.model.special.multilearn.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.StaticMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.TieredMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.framework.environment.Resources;

public class SpecialCharmContributor implements MagicTooltipContributor {
  private Resources resources;

  public SpecialCharmContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails) {
    if (magic instanceof Charm && specialDetails instanceof ISpecialCharm) {
      Charm charm = (Charm) magic;
      ISpecialCharm details = (ISpecialCharm) specialDetails;
      if (details instanceof AbstractMultiLearnableCharm) {
        String label = resources.getString("CharmTreeView.ToolTip.Repurchases");
        String repurchaseInfo = null;
        if (details instanceof StaticMultiLearnableCharm) {
          repurchaseInfo = printStaticLimit((StaticMultiLearnableCharm) details);
        }
        if (details instanceof EssenceFixedMultiLearnableCharm) {
          return;
        }
        if (details instanceof TraitDependentMultiLearnableCharm) {
          repurchaseInfo = printTraitLimit((TraitDependentMultiLearnableCharm) details);
        }
        if (details instanceof TieredMultiLearnableCharm) {
          repurchaseInfo = printTieredLimit(charm, (TieredMultiLearnableCharm) details);
        }
        tooltip.appendLine(label, repurchaseInfo);
      }
    }
  }

  private String printTieredLimit(Charm charm, TieredMultiLearnableCharm details) {
    StringBuilder builder = new StringBuilder();
    CharmTier[] tiers = details.getTiers();
    CharmTier first = tiers[0], second = tiers[1], last = tiers[tiers.length - 1];
    for (CharmTier tier : tiers) {
      if (tier == first) {
        continue;
      }
      if (tier == last && tier != second) {
        builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.And"));
        builder.append(TooltipBuilder.Space);
      }
      if (tier == second || tiers.length <= 3) {
        builder.append(resources.getString("Essence"));
        builder.append(TooltipBuilder.Space);
      }
      builder.append(tier.getRequirement(OtherTraitType.Essence));

      int traitRequirement = tier.getRequirement(charm.getPrimaryTraitType());
      if (traitRequirement > 0) {
        builder.append("/");
        if (tier == second || tiers.length <= 3) {
          builder.append(resources.getString(charm.getPrimaryTraitType().getId()));
          builder.append(TooltipBuilder.Space);
        }
        builder.append(traitRequirement);
      }
      if (tier != last) {
        builder.append(TooltipBuilder.CommaSpace);
      }
    }
    return builder.toString();
  }

  private String printTraitLimit(TraitDependentMultiLearnableCharm details) {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    TraitType traitType = details.getTraitType();
    builder.append(resources.getString(traitType.getId()));
    if (details.getModifier() != 0) {
      builder.append(details.getModifier());
    }
    builder.append(")");
    builder.append(TooltipBuilder.Space);
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Times"));
    return builder.toString();
  }

  private String printStaticLimit(StaticMultiLearnableCharm details) {
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + details.getAbsoluteLearnLimit()));
    return builder.toString();
  }
}