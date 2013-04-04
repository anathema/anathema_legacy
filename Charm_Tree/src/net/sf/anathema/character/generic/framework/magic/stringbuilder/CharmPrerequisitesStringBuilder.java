package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class CharmPrerequisitesStringBuilder implements IMagicTooltipStringBuilder {
  private final Resources resources;

  public CharmPrerequisitesStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    if (magic instanceof ICharm) {
      createPrerequisiteLines(builder, ((ICharm) magic).getPrerequisites());
      createPrerequisiteLines(builder, new IGenericTrait[]{((ICharm) magic).getEssence()});
    }
  }

  private void createPrerequisiteLines(StringBuilder builder, IGenericTrait[] prerequisites) {
    for (IGenericTrait prerequisite : prerequisites) {
      if (prerequisite.getCurrentValue() == 0) {
        continue;
      }
      builder.append(resources.getString("CharmTreeView.ToolTip.Minimum"));
      builder.append(TooltipBuilder.Space);
      builder.append(resources.getString(prerequisite.getType().getId()));
      builder.append(TooltipBuilder.ColonSpace);
      builder.append(String.valueOf(prerequisite.getCurrentValue()));
      builder.append(TooltipBuilder.HtmlLineBreak);
    }
  }
}