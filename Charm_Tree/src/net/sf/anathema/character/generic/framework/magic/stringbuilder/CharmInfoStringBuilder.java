package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.VerboseCharmTypeStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class CharmInfoStringBuilder implements ICharmInfoStringBuilder
{
  private final List<IMagicTooltipStringBuilder> builders = new ArrayList<IMagicTooltipStringBuilder>();

  public CharmInfoStringBuilder(IResources resources) {
    builders.add(new MagicNameStringBuilder(resources));
    builders.add(new ScreenDisplayInfoStringBuilder(resources));
    builders.add(new CharmDurationStringBuilder(resources));
    builders.add(new VerboseCharmTypeStringBuilder(resources));
    builders.add(new MartialArtsCharmStringBuilder(resources));
    builders.add(new CharmKeywordsStringBuilder(resources));
    builders.add(new CharmPrerequisitesStringBuilder(resources));
    builders.add(new SpecialCharmStringBuilder(resources));
    builders.add(new MagicDescriptionStringBuilder(resources));
    builders.add(new MagicSourceStringBuilder<ICharm>(resources));
  }

  @Override
  public final String getInfoString(ICharm charm, ISpecialCharm specialDetails) {
    Ensure.ensureNotNull("Charm must not be null.", charm); //$NON-NLS-1$
    StringBuilder builder = new StringBuilder();
    builder.append("<html><body>"); //$NON-NLS-1$
    for (IMagicTooltipStringBuilder lineBuilder : builders) {
    	lineBuilder.buildStringForMagic(builder, charm, specialDetails);
    }
    builder.append("</body></html>"); //$NON-NLS-1$
    return builder.toString();
  }

}