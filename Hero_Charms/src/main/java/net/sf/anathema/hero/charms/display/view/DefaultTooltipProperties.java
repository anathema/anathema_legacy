package net.sf.anathema.hero.charms.display.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilderImpl;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.NullSpecialCharm;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

public class DefaultTooltipProperties implements ToolTipProperties {
  private final ICharmTreeViewProperties treeProperties;
  private final CharmIdMap map;
  private final CharmTooltipBuilderImpl tooltipTextProvider;
  private SpecialCharmSet specialCharmSet;

  public DefaultTooltipProperties(ICharmTreeViewProperties treeProperties, CharmIdMap map, Resources resources,
                                  MagicDescriptionProvider magicDescriptionProvider, SpecialCharmSet specialCharmSet) {
    this.treeProperties = treeProperties;
    this.map = map;
    this.specialCharmSet = specialCharmSet;
    this.tooltipTextProvider = new CharmTooltipBuilderImpl(resources, magicDescriptionProvider);
  }

  @Override
  public String getToolTip(String charmId) {
    if (treeProperties.isRequirementNode(charmId)) {
      return null;
    }
    Charm charm = findNonNullCharm(charmId);
    ISpecialCharm specialCharm = getSpecialCharm(charmId);
    TooltipBuilder tooltipBuilder = new TooltipBuilder();
    tooltipTextProvider.configureTooltipWithSpecials(charm, specialCharm, tooltipBuilder);
    return tooltipBuilder.build();
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