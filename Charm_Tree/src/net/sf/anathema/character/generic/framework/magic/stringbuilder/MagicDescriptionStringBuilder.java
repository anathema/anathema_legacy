package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.IResources;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.lang.StringUtilities.createFixedWidthParagraph;

public class MagicDescriptionStringBuilder implements IMagicTooltipStringBuilder {
  private final IResources resources;
  private MagicDescriptionProvider magicDescriptionProvider;

  private final int MAX_DESCRIPTION_LENGTH = 80;

  public MagicDescriptionStringBuilder(IResources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails) {
    MagicDescription charmDescription = magicDescriptionProvider.getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      return;
    }
    boolean isFirst = true;
    String[] paragraphs = charmDescription.getParagraphs();
    for (String paragraph : paragraphs) {
      builder.append(TooltipBuilder.HtmlLineBreak);
      String displayParagraph = isFirst ? getDescriptionHead() + paragraph : paragraph;
      if (isFirst) {
        isFirst = false;
      }
      String shortedParagraph = createFixedWidthParagraph(displayParagraph, TooltipBuilder.HtmlLineBreak, MAX_DESCRIPTION_LENGTH);
      builder.append(shortedParagraph);
    }
  }

  private String getDescriptionHead() {
    return format("<i>{0}</i>: ", resources.getString("CharmTreeView.ToolTip.Description"));
  }
}
