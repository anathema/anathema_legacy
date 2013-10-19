package net.sf.anathema.hero.charms.display.tooltip.type;

import net.sf.anathema.character.main.magic.charm.type.ISimpleSpecialsModel;
import net.sf.anathema.character.main.magic.charm.type.TurnType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class ShortCharmTypeContributor extends AbstractCharmTypeContributor {

  public ShortCharmTypeContributor(Resources resources) {
    super(resources, true);
  }

  @Override
  protected StringBuilder buildDefenseString(ISimpleSpecialsModel model, boolean defaultSpeed, boolean longAction) {
    StringBuilder builder = new StringBuilder();
    builder.append(ConfigurableTooltip.CommaSpace);
    int defenseModifier = model.getDefenseModifier();
    if (defenseModifier == 0) {
      builder.append("-");
    }
    builder.append(defenseModifier);
    return builder;
  }

  @Override
  protected StringBuilder buildSpeedString(ISimpleSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    builder.append(model.getSpeed());
    if (model.getTurnType() == TurnType.LongTick) {
      builder.append(ConfigurableTooltip.Space);
      builder.append(getResources().getString("CharmTreeView.ToolTip.Type.LongTick.Short"));
    }
    return builder;
  }

  @Override
  protected String getDramaticActionKey() {
    return "CharmTreeView.ToolTip.Type.DramaticAction.Short";
  }

  @Override
  protected String getReflexiveDualStepPattern() {
    return "CharmTreeView.ToolTip.Type.DualStep.Short";
  }

  @Override
  protected String getReflexiveSingleStepPattern() {
    return "CharmTreeView.ToolTip.Type.SingleStep.Short";
  }
}