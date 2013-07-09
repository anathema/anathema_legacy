package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

public class MagicNameStringBuilder implements IMagicTooltipStringBuilder {
  private final MagicDisplayLabeler labeler;

  public MagicNameStringBuilder(Resources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, Magic magic, Object details) {
    builder.append("<b>");
    builder.append(labeler.getLabelForMagic(magic));
    builder.append("</b>");
    builder.append(TooltipBuilder.HtmlLineBreak);
  }

}
