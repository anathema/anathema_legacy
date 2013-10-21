package net.sf.anathema.hero.charms.display.tooltip.type;

import net.sf.anathema.character.main.magic.charm.type.ISimpleSpecialsModel;
import net.sf.anathema.character.main.magic.charm.type.TurnType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class VerboseCharmTypeContributor extends AbstractCharmTypeContributor {

  public VerboseCharmTypeContributor(Resources resources) {
    super(resources, false);
  }

  @Override
  protected StringBuilder buildDefenseString(ISimpleSpecialsModel model, boolean defaultSpeed, boolean longAction) {
    StringBuilder builder = new StringBuilder();
    if (!defaultSpeed || longAction) {
      builder.append(ConfigurableTooltip.CommaSpace);
    }
    builder.append(getResources().getString("CharmTreeView.ToolTip.Type.Defense"));
    builder.append(ConfigurableTooltip.Space);
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
    builder.append(getResources().getString("CharmTreeView.ToolTip.Type.Speed"));
    builder.append(ConfigurableTooltip.Space);
    builder.append(model.getSpeed());
    if (model.getTurnType() == TurnType.LongTick) {
      builder.append(ConfigurableTooltip.Space);
      builder.append(getResources().getString("CharmTreeView.ToolTip.Type.LongTick"));
    }
    return builder;
  }

  @Override
  protected String getDramaticActionKey() {
    return "CharmTreeView.ToolTip.Type.DramaticAction";
  }

  @Override
  protected String getReflexiveDualStepPattern() {
    return "CharmTreeView.ToolTip.Type.DualStep";
  }

  @Override
  protected String getReflexiveSingleStepPattern() {
    return "CharmTreeView.ToolTip.Type.SingleStep";
  }
}