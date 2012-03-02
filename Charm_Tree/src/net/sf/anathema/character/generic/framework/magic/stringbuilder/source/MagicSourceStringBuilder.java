package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicTooltipStringBuilder;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
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
	  builder.append(resources.getString("CharmTreeView.ToolTip.Source")); //$NON-NLS-1$
	  builder.append(ColonSpace);
	  builder.append(createSourceString((T) magic));
  }

  public String createSourceString(T t) {
    final IExaltedSourceBook source = getSource(t);
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString(createSourceBookKey(source)));
    String pageKey = createPageKey(t.getId(), source);
    if (resources.supportsKey(pageKey)) {
      builder.append(IMagicTooltipStringBuilder.CommaSpace);
      builder.append(resources.getString("CharmTreeView.ToolTip.Page")); //$NON-NLS-1$
      builder.append(IMagicTooltipStringBuilder.Space);
      builder.append(resources.getString(pageKey));
    }
    return builder.toString();
  }

  private String createSourceBookKey(final IExaltedSourceBook source) {
    return "ExaltedSourceBook." + source.getId(); //$NON-NLS-1$
  }

  private String createPageKey(String id, final IExaltedSourceBook source) {
    return source.getId() + "." + id + ".Page"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String createShortSourceString(T t) {
    final IExaltedSourceBook source = getSource(t);
    String id = t.getId();
    return createShortSourceString(source, id);
  }

  protected IExaltedSourceBook getSource(T t) {
    return t.getSource();
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