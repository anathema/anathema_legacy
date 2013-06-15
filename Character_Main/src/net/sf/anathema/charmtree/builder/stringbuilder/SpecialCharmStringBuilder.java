package net.sf.anathema.charmtree.builder.stringbuilder;

import net.sf.anathema.character.generic.impl.magic.charm.special.AbstractMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class SpecialCharmStringBuilder implements IMagicTooltipStringBuilder {
  private static final String HtmlLineBreak = "<br>";
  private Resources resources;

  public SpecialCharmStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails) {
    if (magic instanceof ICharm && specialDetails instanceof ISpecialCharm) {
      ICharm charm = (ICharm) magic;
      ISpecialCharm details = (ISpecialCharm) specialDetails;
      StringBuilder specialCharmBuilder = new StringBuilder();
      if (details instanceof AbstractMultiLearnableCharm) {
        specialCharmBuilder.append(resources.getString("CharmTreeView.ToolTip.Repurchases"));
        specialCharmBuilder.append(TooltipBuilder.ColonSpace);
        if (details instanceof StaticMultiLearnableCharm) {
          printStaticLimit(specialCharmBuilder, (StaticMultiLearnableCharm) details);
        }
        if (details instanceof EssenceFixedMultiLearnableCharm) {
          return;
        }
        if (details instanceof TraitDependentMultiLearnableCharm) {
          printTraitLimit(specialCharmBuilder, (TraitDependentMultiLearnableCharm) details);
        }
        if (details instanceof TieredMultiLearnableCharm) {
          printTieredLimit(specialCharmBuilder, charm, (TieredMultiLearnableCharm) details);
        }
        specialCharmBuilder.append(HtmlLineBreak);
      }
      builder.append(specialCharmBuilder);
    }
  }

  private void printTieredLimit(StringBuilder builder, ICharm charm, TieredMultiLearnableCharm details) {
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
  }

  private void printTraitLimit(StringBuilder builder, TraitDependentMultiLearnableCharm details) {
    builder.append("(");
    builder.append(resources.getString(details.getTraitType().getId()));
    if (details.getModifier() != 0) {
      builder.append(details.getModifier());
    }
    builder.append(")");
    builder.append(TooltipBuilder.Space);
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Times"));
  }

  private void printStaticLimit(StringBuilder builder, StaticMultiLearnableCharm details) {
    builder.append(resources.getString("CharmTreeView.ToolTip.Repurchases.Static" + details.getAbsoluteLearnLimit()));
  }
}