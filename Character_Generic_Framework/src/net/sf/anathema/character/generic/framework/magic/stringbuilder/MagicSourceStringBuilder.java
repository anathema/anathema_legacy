package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

public class MagicSourceStringBuilder implements IMagicSourceStringBuilder {

  private final IResources resources;

  public MagicSourceStringBuilder(IResources resources) {
    this.resources = resources;
  }

  public String createSourceString(IMagic magic, boolean includeHeader) {
    String sourceString = ""; //$NON-NLS-1$
    if (includeHeader) {
      sourceString = sourceString.concat(resources.getString("CharmTreeView.ToolTip.Source") + IMagicStringBuilderConstants.ColonSpace); //$NON-NLS-1$
    }
    sourceString = sourceString.concat(magic.getSource().getSource());
    if (magic.getSource().getPage() != null) {
      sourceString = sourceString.concat(IMagicStringBuilderConstants.CommaSpace
          + resources.getString("CharmTreeView.ToolTip.Page") + IMagicStringBuilderConstants.Space + magic.getSource().getPage()); //$NON-NLS-1$
    }
    return sourceString;
  }
}