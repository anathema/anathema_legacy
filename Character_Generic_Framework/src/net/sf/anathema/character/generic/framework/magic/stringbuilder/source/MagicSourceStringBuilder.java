package net.sf.anathema.character.generic.framework.magic.stringbuilder.source;

import static net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants.ColonSpace;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

public class MagicSourceStringBuilder extends AbstractMagicSourceStringBuilder<IMagic> {

  public MagicSourceStringBuilder(IResources resources) {
    super(resources);
  }

  public String createSourceString(IMagic magic) {
    StringBuilder builder = new StringBuilder();
    builder.append(getResources().getString("CharmTreeView.ToolTip.Source") + ColonSpace); //$NON-NLS-1$
    builder.append(createSourceString(magic.getSource()));
    return builder.toString();
  }
}