package net.sf.anathema.character.main.charmtree.builder.stringbuilder;

import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

import static java.text.MessageFormat.format;

public class MagicDescriptionStringBuilder implements IMagicTooltipStringBuilder {
  private final Resources resources;
  private MagicDescriptionProvider magicDescriptionProvider;

  public MagicDescriptionStringBuilder(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails) {
    TooltipBuilder tooltipBuilder = new TooltipBuilder();
    tooltipBuilder.appendParagraphs(getDisplayParagraphs(magic));
    builder.append(tooltipBuilder.build());
  }

  public String[] getDisplayParagraphs(IMagic magic) {
    MagicDescription charmDescription = magicDescriptionProvider.getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      return new String[0];
    }
    String[] paragraphs = charmDescription.getParagraphs();
    paragraphs[0] = getDescriptionHead() + paragraphs[0];
    return paragraphs;
  }

  private String getDescriptionHead() {
    return format("<i>{0}</i>: ", resources.getString("CharmTreeView.ToolTip.Description"));
  }
}
