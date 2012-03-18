package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.lib.resources.IResources;

public class MartialArtsCharmStringBuilder implements IMagicTooltipStringBuilder {
  private final IResources resources;

  public MartialArtsCharmStringBuilder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    if (magic instanceof ICharm && MartialArtsUtilities.isMartialArtsCharm((ICharm) magic)) {
      MartialArtsLevel level = MartialArtsUtilities.getLevel((ICharm) magic);
      builder.append(resources.getString("CharmTreeView.ToolTip.MartialArtsLevel")); //$NON-NLS-1$
      builder.append(ColonSpace);
      builder.append(resources.getString(level.getId()));
      builder.append(HtmlLineBreak);
    }
  }
}
