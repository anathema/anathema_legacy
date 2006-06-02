package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
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
    final IMagicSource source = getSource(t);
    StringBuilder builder = new StringBuilder();
    builder.append(source.getSource());
    if (source.getPage() != null) {
      builder.append(IMagicStringBuilderConstants.CommaSpace
          + resources.getString("CharmTreeView.ToolTip.Page") + IMagicStringBuilderConstants.Space + source.getPage()); //$NON-NLS-1$
    }
    return builder.toString();
  }

  public String createShortSourceString(T t) {
    final IMagicSource source = getSource(t);
    StringBuilder builder = new StringBuilder();
    builder.append(source.getSource());
    if (source.getPage() != null) {
      builder.append(IMagicStringBuilderConstants.CommaSpace + source.getPage());
    }
    return builder.toString();
  }

  protected IMagicSource getSource(T t) {
    return t.getSource();
  }
}