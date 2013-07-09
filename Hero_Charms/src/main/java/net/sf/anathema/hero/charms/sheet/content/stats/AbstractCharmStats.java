package net.sf.anathema.hero.charms.sheet.content.stats;

import com.google.common.base.Function;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.character.main.magic.model.charm.ICharmAttribute;
import net.sf.anathema.character.main.magic.model.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.hero.magic.sheet.content.stats.AbstractMagicStats;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public abstract class AbstractCharmStats extends AbstractMagicStats<Charm> {

  public AbstractCharmStats(Charm magic) {
    super(magic);
  }

  @Override
  public String getGroupName(Resources resources) {
    return resources.getString(getMagic().getGroupId());
  }

  @Override
  public String getType(Resources resources) {
    ICharmTypeModel model = getMagic().getCharmTypeModel();
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }

  @Override
  public String getDurationString(Resources resources) {
    return getMagic().getDuration().getText(resources);
  }

  @Override
  public String getSourceString(Resources resources) {
    IMagicSourceStringBuilder<Charm> stringBuilder = new MagicSourceStringBuilder<>(resources);
    return stringBuilder.createShortSourceString(getMagic());
  }

  protected String[] getDetailKeys() {
    List<String> details = new ArrayList<>();
    for (ICharmAttribute attribute : getMagic().getAttributes()) {
      String attributeId = attribute.getId();
      if (attribute.isVisualized()) {
        details.add("Keyword." + attributeId);
      }
    }
    return details.toArray(new String[details.size()]);
  }

  @Override
  public String[] getDetailStrings(final Resources resources) {
    return transform(getDetailKeys(), String.class, new Function<String, String>() {
      @Override
      public String apply(String input) {
        return resources.getString(input);
      }
    });
  }

  @Override
  public int compareTo(IMagicStats stats) {
    if (stats instanceof GenericCharmStats) {
      return 1;
    } else if (stats instanceof AbstractCharmStats) {
      return 0;
    } else {
      return -1;
    }
  }
}
