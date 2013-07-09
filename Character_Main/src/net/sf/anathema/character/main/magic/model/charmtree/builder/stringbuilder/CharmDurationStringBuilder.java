package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class CharmDurationStringBuilder implements IMagicTooltipStringBuilder {
  private final Resources resources;

  public CharmDurationStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, Magic magic, Object details) {
    if (magic instanceof Charm) {
      builder.append(resources.getString("CharmTreeView.ToolTip.Duration"));
      builder.append(TooltipBuilder.ColonSpace);
      builder.append(((Charm) magic).getDuration().getText(resources));
      builder.append(TooltipBuilder.HtmlLineBreak);
    }
  }
}
