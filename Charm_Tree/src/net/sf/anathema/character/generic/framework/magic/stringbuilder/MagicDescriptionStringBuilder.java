package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.description.CharmDescription;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProvider;
import net.sf.anathema.lib.resources.IResources;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.lang.AnathemaStringUtilities.createFixedWidthParagraph;

public class MagicDescriptionStringBuilder implements IMagicTooltipStringBuilder {
  private final IResources resources;
  private CharmDescriptionProvider charmDescriptionProvider;

  private final int MAX_DESCRIPTION_LENGTH = 80;

  public MagicDescriptionStringBuilder(IResources resources, CharmDescriptionProvider charmDescriptionProvider) {
    this.resources = resources;
    this.charmDescriptionProvider = charmDescriptionProvider;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails) {
    CharmDescription charmDescription = charmDescriptionProvider.getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      return;
    }
    boolean isFirst = true;
    String[] paragraphs = charmDescription.getParagraphs();
    for (String paragraph : paragraphs) {
      builder.append(HtmlLineBreak);
      String displayParagraph = isFirst ? getDescriptionHead() + paragraph : paragraph;
      if (isFirst) {
        isFirst = false;
      }
      String shortedParagraph = createFixedWidthParagraph(displayParagraph, HtmlLineBreak, MAX_DESCRIPTION_LENGTH);
      builder.append(shortedParagraph);
    }
  }

  private String getDescriptionHead() {
    return format("<i>{0}</i>: ", resources.getString("CharmTreeView.ToolTip.Description"));
  }
}
