package net.sf.anathema.hero.charms.display.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilder;
import net.sf.anathema.hero.charms.display.tooltip.CharmTooltipBuilderImpl;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractCharmTreeViewProperties implements ICharmTreeViewProperties {

  private final CharmTooltipBuilder tooltipTextProvider;

  public AbstractCharmTreeViewProperties(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.tooltipTextProvider = new CharmTooltipBuilderImpl(resources, magicDescriptionProvider);
  }

  @Override
  public final boolean isRequirementNode(final String nodeId) {
    return nodeId.startsWith(REQUIREMENT);
  }

  @Override
  public final String getToolTip(String charmId) {
    if (isRequirementNode(charmId)) {
      return null;
    }
    Charm charm = findNonNullCharm(charmId);
    ISpecialCharm specialCharm = getSpecialCharm(charmId);
    TooltipBuilder tooltipBuilder = new TooltipBuilder();
    tooltipTextProvider.configureTooltipWithSpecials(charm, specialCharm, tooltipBuilder);
    return tooltipBuilder.build();
  }

  private Charm findNonNullCharm(final String charmId) {
    Charm charm = getCharmById(charmId);
    Preconditions.checkNotNull(charm, "Charm with id '" + charmId + "' not found.");
    return charm;
  }

  protected abstract Charm getCharmById(String id);

  protected abstract ISpecialCharm getSpecialCharm(String id);
}