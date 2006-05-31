package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.lib.resources.IResources;

public class MagicSourceStringBuilder implements IMagicSourceStringBuilder {

  private final IResources resources;

  public MagicSourceStringBuilder(IResources resources) {
    this.resources = resources;
  }

  public String createSourceString(IMagic magic, boolean includeHeader) {
    StringBuilder builder = new StringBuilder();
    if (includeHeader) {
      builder.append(resources.getString("CharmTreeView.ToolTip.Source") + IMagicStringBuilderConstants.ColonSpace); //$NON-NLS-1$
    }
    builder.append(buildSourceString(magic.getSource()));
    return builder.toString();
  }

  private StringBuilder buildSourceString(final IMagicSource source) {
    StringBuilder builder = new StringBuilder();
    builder.append(source.getSource());
    if (source.getPage() != null) {
      builder.append(IMagicStringBuilderConstants.CommaSpace
          + resources.getString("CharmTreeView.ToolTip.Page") + IMagicStringBuilderConstants.Space + source.getPage()); //$NON-NLS-1$
    }
    return builder;
  }
}