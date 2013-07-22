package net.sf.anathema.hero.charms.display.tooltip;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.charms.display.tooltip.type.VerboseCharmTypeContributor;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class CharmTooltipBuilderImpl implements CharmTooltipBuilder {
  private final List<MagicTooltipContributor> builders = new ArrayList<>();

  public CharmTooltipBuilderImpl(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    builders.add(new MagicNameContributor(resources));
    builders.add(new ScreenDisplayInfoContributor(resources));
    builders.add(new CharmDurationContributor(resources));
    builders.add(new VerboseCharmTypeContributor(resources));
    builders.add(new MartialArtsCharmContributor(resources));
    builders.add(new CharmKeywordsContributor(resources));
    builders.add(new CharmPrerequisitesContributor(resources));
    builders.add(new SpecialCharmContributor(resources));
    builders.add(new MagicSourceContributor<Charm>(resources));
    builders.add(new MagicDescriptionContributor(magicDescriptionProvider));
  }

  @Override
  public void configureTooltipWithoutSpecials(Charm charm, ConfigurableTooltip tooltip) {
    configureTooltipWithSpecials(charm, null, tooltip);
  }

  @Override
  public final void configureTooltipWithSpecials(Charm charm, ISpecialCharm specialDetails, ConfigurableTooltip tooltip) {
    Preconditions.checkNotNull(charm);
    for (MagicTooltipContributor lineBuilder : builders) {
      lineBuilder.buildStringForMagic(tooltip, charm, specialDetails);
    }
  }
}