package net.sf.anathema.character.generic.framework.magic.stringbuilder.type;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.resources.IResources;

public class VerboseCharmTypeStringBuilder extends AbstractCharmTypeStringBuilder {

  public VerboseCharmTypeStringBuilder(IResources resources) {
    super(resources, false);
  }

  @Override
  protected StringBuilder buildDefenseString(
      ISimpleSpecialsModel model,
      final boolean defaultSpeed,
      final boolean longAction) {
    StringBuilder builder = new StringBuilder();
    if (!defaultSpeed || longAction) {
      builder.append(IMagicStringBuilderConstants.CommaSpace);
    }
    builder.append(getResources().getString("CharmTreeView.ToolTip.Type.Defense")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.Space);
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
    builder.append(getResources().getString("CharmTreeView.ToolTip.Type.Speed")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.Space);
    builder.append(model.getSpeed());
    if (model.getTurnType() == TurnType.LongTick) {
      builder.append(IMagicStringBuilderConstants.Space);
      builder.append(getResources().getString("CharmTreeView.ToolTip.Type.LongTick")); //$NON-NLS-1$
    }
    return builder;
  }

  @Override
  protected String getDramaticActionKey() {
    return "CharmTreeView.ToolTip.Type.DramaticAction"; //$NON-NLS-1$
  }

  @Override
  protected String getReflexiveDualStepPattern() {
    return "CharmTreeView.ToolTip.Type.DualStep"; //$NON-NLS-1$
  }

  @Override
  protected String getReflexiveSingleStepPattern() {
    return "CharmTreeView.ToolTip.Type.SingleStep"; //$NON-NLS-1$
  }
}