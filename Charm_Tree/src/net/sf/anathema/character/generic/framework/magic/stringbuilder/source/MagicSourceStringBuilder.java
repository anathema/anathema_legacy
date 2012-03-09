package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicTooltipStringBuilder;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

public class MagicSourceStringBuilder<T extends IMagic> implements IMagicSourceStringBuilder<T>, IMagicTooltipStringBuilder {

  private final IResources resources;

  public MagicSourceStringBuilder(IResources resources) {
    this.resources = resources;
  }

  protected IResources getResources() {
    return resources;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic,
  		Object specialDetails) {
	  String descriptionString = resources.getString("CharmTreeView.ToolTip.Source"); //$NON-NLS-1$
	  descriptionString += ColonSpace;
	  descriptionString += createSourceString((T) magic);
	  descriptionString = AnathemaStringUtilities.createFixedWidthParagraph(descriptionString,
			  HtmlLineBreak, DEFAULT_TOOLTIP_WIDTH);
	  builder.append(descriptionString);
  }

  public String createSourceString(T t) {
    IExaltedSourceBook[] sources = getSources(t);
    String[] sourceStrings = new String[sources.length];
    for (int i = 0; i != sources.length; i++)
    {
	    StringBuilder builder = new StringBuilder();
	    builder.append(resources.getString(createSourceBookKey(sources[i])));
	    String pageKey = createPageKey(t.getId(), sources[i]);
	    if (resources.supportsKey(pageKey)) {
	      builder.append(IMagicTooltipStringBuilder.CommaSpace);
	      builder.append(resources.getString("CharmTreeView.ToolTip.Page")); //$NON-NLS-1$
	      builder.append(IMagicTooltipStringBuilder.Space);
	      builder.append(resources.getString(pageKey));
	    }
	    sourceStrings[i] = builder.toString();
    }
    String andString = resources.getString("CharmTreeView.ToolTip.SourceAnd");
    return AnathemaStringUtilities.joinStringsWithDelimiter(sourceStrings, ", " + andString + " ");
  }

  private String createSourceBookKey(final IExaltedSourceBook source) {
    return "ExaltedSourceBook." + source.getId(); //$NON-NLS-1$
  }

  private String createPageKey(String id, final IExaltedSourceBook source) {
    return source.getId() + "." + id + ".Page"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String createShortSourceString(T t) {
    final IExaltedSourceBook source = t.getPrimarySource();
    String id = t.getId();
    return createShortSourceString(source, id);
  }

  protected IExaltedSourceBook[] getSources(T t) {
    return t.getSources();
  }
  
  protected IExaltedSourceBook getSource(T t) {
	return t.getPrimarySource();
  }

  public String createShortSourceString(IExaltedSourceBook source, String magicId) {
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString(createSourceBookKey(source) + ".Short")); //$NON-NLS-1$
    String pageKey = createPageKey(magicId, source);
    if (resources.supportsKey(pageKey)) {
      builder.append(IMagicTooltipStringBuilder.CommaSpace);
      builder.append(resources.getString(pageKey));
    }
    return builder.toString();
  }
}