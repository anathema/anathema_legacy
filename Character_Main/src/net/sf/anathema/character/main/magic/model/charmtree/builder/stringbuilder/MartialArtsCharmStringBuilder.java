package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class MartialArtsCharmStringBuilder implements IMagicTooltipStringBuilder {
  private final Resources resources;

  public MartialArtsCharmStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, Magic magic, Object details) {
    if (magic instanceof Charm && MartialArtsUtilities.isMartialArts((Charm) magic)) {
      MartialArtsLevel level = MartialArtsUtilities.getLevel((Charm) magic);
      builder.append(resources.getString("CharmTreeView.ToolTip.MartialArtsLevel"));
      builder.append(TooltipBuilder.ColonSpace);
      builder.append(resources.getString(level.getId()));
      builder.append(TooltipBuilder.HtmlLineBreak);
    }
  }
}
