package net.sf.anathema.character.main.charmtree.builder.stringbuilder;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.charmtree.builder.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.main.charmtree.builder.stringbuilder.type.VerboseCharmTypeStringBuilder;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class CharmInfoStringBuilder implements ICharmInfoStringBuilder {
  private final List<IMagicTooltipStringBuilder> builders = new ArrayList<>();

  public CharmInfoStringBuilder(Resources resources, MagicDescriptionProvider magicDescriptionProvider) {
    builders.add(new MagicNameStringBuilder(resources));
    builders.add(new ScreenDisplayInfoStringBuilder(resources));
    builders.add(new CharmDurationStringBuilder(resources));
    builders.add(new VerboseCharmTypeStringBuilder(resources));
    builders.add(new MartialArtsCharmStringBuilder(resources));
    builders.add(new CharmKeywordsStringBuilder(resources));
    builders.add(new CharmPrerequisitesStringBuilder(resources));
    builders.add(new SpecialCharmStringBuilder(resources));
    builders.add(new MagicSourceStringBuilder<ICharm>(resources));
    builders.add(new MagicDescriptionStringBuilder(resources, magicDescriptionProvider));
  }

  @Override
  public final String getInfoString(ICharm charm, ISpecialCharm specialDetails) {
    Preconditions.checkNotNull(charm);
    StringBuilder builder = new StringBuilder();
    builder.append("<html><body>");
    for (IMagicTooltipStringBuilder lineBuilder : builders) {
      lineBuilder.buildStringForMagic(builder, charm, specialDetails);
    }
    builder.append("</body></html>");
    return builder.toString();
  }
}