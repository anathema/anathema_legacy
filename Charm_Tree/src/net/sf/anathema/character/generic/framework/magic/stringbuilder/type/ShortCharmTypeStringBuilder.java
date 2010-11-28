package net.sf.anathema.character.generic.framework.magic.stringbuilder.type;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.resources.IResources;

public class ShortCharmTypeStringBuilder extends AbstractCharmTypeStringBuilder {

  public ShortCharmTypeStringBuilder(IResources resources) {
    super(resources, true);
  }

  @Override
  protected StringBuilder buildDefenseString(
      ISimpleSpecialsModel model,
      final boolean defaultSpeed,
      final boolean longAction) {
    StringBuilder builder = new StringBuilder();
    builder.append(IMagicStringBuilderConstants.CommaSpace);
    final int defenseModifier = model.getDefenseModifier();
    if (defenseModifier == 0) {
      builder.append("-"); //$NON-NLS-1$
    }
    builder.append(defenseModifier);
    return builder;
  }

  @Override
  protected StringBuilder buildSpeedString(ISimpleSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    builder.append(model.getSpeed());
    if (model.getTurnType() == TurnType.LongTick) {
      builder.append(IMagicStringBuilderConstants.Space);
      builder.append(getResources().getString("CharmTreeView.ToolTip.Type.LongTick.Short")); //$NON-NLS-1$
    }
    return builder;
  }

  @Override
  protected String getDramaticActionKey() {
    return "CharmTreeView.ToolTip.Type.DramaticAction.Short"; //$NON-NLS-1$
  }

  @Override
  protected String getReflexiveDualStepPattern() {
    return "CharmTreeView.ToolTip.Type.DualStep.Short"; //$NON-NLS-1$
  }

  @Override
  protected String getReflexiveSingleStepPattern() {
    return "CharmTreeView.ToolTip.Type.SingleStep.Short"; //$NON-NLS-1$
  }
}