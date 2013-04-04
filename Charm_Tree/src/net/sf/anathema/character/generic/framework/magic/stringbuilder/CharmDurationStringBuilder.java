package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class CharmDurationStringBuilder implements IMagicTooltipStringBuilder {
  private final Resources resources;

  public CharmDurationStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    if (magic instanceof ICharm) {
      builder.append(resources.getString("CharmTreeView.ToolTip.Duration"));
      builder.append(TooltipBuilder.ColonSpace);
      builder.append(((ICharm) magic).getDuration().getText(resources));
      builder.append(TooltipBuilder.HtmlLineBreak);
    }
  }
}
