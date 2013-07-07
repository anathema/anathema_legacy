package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.charm.ICharmAttribute;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class CharmKeywordsStringBuilder implements IMagicTooltipStringBuilder {
  private final Resources resources;

  public CharmKeywordsStringBuilder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    if (magic instanceof ICharm) {
      ICharm charm = (ICharm) magic;
      StringBuilder listBuilder = new StringBuilder();
      for (ICharmAttribute attribute : charm.getAttributes()) {
        if (attribute.isVisualized()) {
          if (listBuilder.length() != 0) {
            listBuilder.append(TooltipBuilder.CommaSpace);
          }
          listBuilder.append(resources.getString("Keyword." + attribute.getId()));
        }
      }
      if (listBuilder.length() > 0) {
        listBuilder.insert(0, resources.getString("CharmTreeView.ToolTip.Keywords") + TooltipBuilder.ColonSpace);
        listBuilder.append(TooltipBuilder.HtmlLineBreak);
      }
      builder.append(listBuilder);
    }
  }
}
