package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.resources.IResources;

public class MagicSourceStringBuilder<T extends IMagic> implements IMagicSourceStringBuilder<T> {

  private final IResources resources;

  public MagicSourceStringBuilder(IResources resources) {
    this.resources = resources;
  }

  protected IResources getResources() {
    return resources;
  }

  public String createSourceString(T t) {
    final IExaltedSourceBook source = getSource(t);
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString(createSourceBookKey(source)));
    String pageKey = createPageKey(t, source);
    if (resources.supportsKey(pageKey)) {
      builder.append(IMagicStringBuilderConstants.CommaSpace);
      builder.append(resources.getString("CharmTreeView.ToolTip.Page")); //$NON-NLS-1$
      builder.append(IMagicStringBuilderConstants.Space);
      builder.append(resources.getString(pageKey));
    }
    return builder.toString();
  }

  private String createSourceBookKey(final IExaltedSourceBook source) {
    return "ExaltedSourceBook." + source.getId(); //$NON-NLS-1$
  }

  private String createPageKey(T t, final IExaltedSourceBook source) {
    return source.getId() + "." + t.getId() + ".Page"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String createShortSourceString(T t) {
    final IExaltedSourceBook source = getSource(t);
    StringBuilder builder = new StringBuilder();
    builder.append(resources.getString(createSourceBookKey(source) + ".Short")); //$NON-NLS-1$
    String pageKey = createPageKey(t, source);
    if (resources.supportsKey(pageKey)) {
      builder.append(IMagicStringBuilderConstants.CommaSpace);
      builder.append(resources.getString(pageKey));
    }
    return builder.toString();
  }

  protected IExaltedSourceBook getSource(T t) {
    return t.getSource();
  }
}