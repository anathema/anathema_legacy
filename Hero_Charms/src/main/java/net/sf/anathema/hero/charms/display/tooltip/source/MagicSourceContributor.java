package net.sf.anathema.hero.charms.display.tooltip.source;

import net.sf.anathema.hero.charms.display.tooltip.IMagicSourceStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.MagicTooltipContributor;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.framework.environment.Resources;

public class MagicSourceContributor<T extends Magic> implements IMagicSourceStringBuilder<T>, MagicTooltipContributor {

  private final Resources resources;

  public MagicSourceContributor(Resources resources) {
    this.resources = resources;
  }

  protected Resources getResources() {
    return resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object specialDetails) {
    String descriptionLabel = resources.getString("CharmTreeView.ToolTip.Source");
    String descriptionText = createSourceString((T) magic);
    tooltip.appendLine(descriptionLabel, descriptionText);
  }

  @Override
  public String createSourceString(T t) {
    SourceBook[] sources = getSources(t);
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

  private String createSourceBookKey(SourceBook source) {
    return "ExaltedSourceBook." + source.getId();
  }

  private String createPageKey(String id, SourceBook source) {
    return source.getId() + "." + id + ".Page";
  }

  @Override
  public String createShortSourceString(T t) {
    SourceBook source = t.getPrimarySource();
    String id = t.getId();
    return createShortSourceString(source, id);
  }

  protected SourceBook[] getSources(T t) {
    return t.getSources();
  }

  public String createShortSourceString(SourceBook source, String magicId) {
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
