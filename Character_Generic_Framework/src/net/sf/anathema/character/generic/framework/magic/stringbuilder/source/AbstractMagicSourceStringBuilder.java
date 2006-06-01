package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractMagicSourceStringBuilder<T extends IMagic> implements IMagicSourceStringBuilder<T> {

  private final IResources resources;

  public AbstractMagicSourceStringBuilder(IResources resources) {
    this.resources = resources;
  }

  protected IResources getResources() {
    return resources;
  }

  protected StringBuilder createSourceString(final IMagicSource source) {
    StringBuilder builder = new StringBuilder();
    builder.append(source.getSource());
    if (source.getPage() != null) {
      builder.append(IMagicStringBuilderConstants.CommaSpace
          + resources.getString("CharmTreeView.ToolTip.Page") + IMagicStringBuilderConstants.Space + source.getPage()); //$NON-NLS-1$
    }
    return builder;
  }

  protected StringBuilder createShortSourceString(final IMagicSource source) {
    StringBuilder builder = new StringBuilder();
    builder.append(source.getSource());
    if (source.getPage() != null) {
      builder.append(IMagicStringBuilderConstants.CommaSpace + source.getPage());
    }
    return builder;
  }
}