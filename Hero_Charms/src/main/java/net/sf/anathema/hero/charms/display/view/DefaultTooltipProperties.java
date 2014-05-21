package net.sf.anathema.hero.charms.display.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilderImpl;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.NullSpecialCharm;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

public class DefaultTooltipProperties implements ToolTipProperties {
  private final FunctionalNodeProperties treeProperties;
  private final CharmIdMap map;
  private final CharmTooltipBuilderImpl tooltipTextProvider;
  private SpecialCharmSet specialCharmSet;

  public DefaultTooltipProperties(FunctionalNodeProperties treeProperties, CharmIdMap map, Resources resources,
                                  MagicDescriptionProvider magicDescriptionProvider, SpecialCharmSet specialCharmSet) {
    this.treeProperties = treeProperties;
    this.map = map;
    this.specialCharmSet = specialCharmSet;
    this.tooltipTextProvider = new CharmTooltipBuilderImpl(resources, magicDescriptionProvider);
  }

  @Override
  public void configureTooltip(String id, ConfigurableTooltip tooltip) {
    if (treeProperties.isRequirementNode(id)) {
      tooltip.showNoTooltip();
      return;
    }
    Charm charm = findNonNullCharm(id);
    ISpecialCharm specialCharm = getSpecialCharm(id);
    tooltipTextProvider.configureTooltipWithSpecials(charm, specialCharm, tooltip);
  }

  private Charm findNonNullCharm(final String charmId) {
    Charm charm = map.getCharmById(charmId);
    Preconditions.checkNotNull(charm, "Charm with id '" + charmId + "' not found.");
    return charm;
  }

  private ISpecialCharm getSpecialCharm(String id) {
    for (ISpecialCharm special : specialCharmSet) {
      if (special.getCharmId().equals(id)) {
        return special;
      }
    }
    return new NullSpecialCharm();
  }
}