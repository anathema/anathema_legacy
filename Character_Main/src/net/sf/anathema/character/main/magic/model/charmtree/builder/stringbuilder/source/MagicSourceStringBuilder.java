package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.source;

import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.rules.IExaltedSourceBook;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.IMagicTooltipStringBuilder;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.lib.lang.StringUtilities.createFixedWidthParagraph;

public class MagicSourceStringBuilder<T extends IMagic> implements IMagicSourceStringBuilder<T>, IMagicTooltipStringBuilder {

  private final Resources resources;

  public MagicSourceStringBuilder(Resources resources) {
    this.resources = resources;
  }

  protected Resources getResources() {
    return resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object specialDetails) {
    String descriptionString = resources.getString("CharmTreeView.ToolTip.Source");
    descriptionString += TooltipBuilder.ColonSpace;
    descriptionString += createSourceString((T) magic);
    descriptionString = createFixedWidthParagraph(descriptionString, TooltipBuilder.HtmlLineBreak, TooltipBuilder.DEFAULT_TOOLTIP_WIDTH);
    builder.append(descriptionString);
    builder.append(TooltipBuilder.HtmlLineBreak);
  }

  @Override
  public String createSourceString(T t) {
    IExaltedSourceBook[] sources = getSources(t);
    String[] sourceStrings = new String[sources.length];
    for (int i = 0; i != sources.length; i++) {
      StringBuilder builder = new StringBuilder();
      builder.append(resources.getString(createSourceBookKey(sources[i])));
      String pageKey = createPageKey(t.getId(), sources[i]);
      if (resources.supportsKey(pageKey)) {
        builder.append(TooltipBuilder.CommaSpace);
        builder.append(resources.getString("CharmTreeView.ToolTip.Page"));
        builder.append(TooltipBuilder.Space);
        builder.append(resources.getString(pageKey));
      }
      sourceStrings[i] = builder.toString();
    }
    String andString = resources.getString("CharmTreeView.ToolTip.SourceAnd");
    return StringUtilities.joinStringsWithDelimiter(sourceStrings, ", " + andString + " ");
  }

  private String createSourceBookKey(IExaltedSourceBook source) {
    return "ExaltedSourceBook." + source.getId();
  }

  private String createPageKey(String id, IExaltedSourceBook source) {
    return source.getId() + "." + id + ".Page";
  }

  @Override
  public String createShortSourceString(T t) {
    IExaltedSourceBook source = t.getPrimarySource();
    String id = t.getId();
    return createShortSourceString(source, id);
  }

  protected IExaltedSourceBook[] getSources(T t) {
    return t.getSources();
  }

  public String createShortSourceString(IExaltedSourceBook source, String magicId) {
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString(createSourceBookKey(source) + ".Short"));
    String pageKey = createPageKey(magicId, source);
    if (resources.supportsKey(pageKey)) {
      builder.append(TooltipBuilder.CommaSpace);
      builder.append(resources.getString(pageKey));
    }
    return builder.toString();
  }
}
