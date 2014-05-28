package net.sf.anathema.hero.charms.sheet.content.stats;

import net.sf.anathema.character.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.tooltip.IMagicSourceStringBuilder;
import net.sf.anathema.hero.charms.display.tooltip.source.MagicSourceContributor;
import net.sf.anathema.hero.charms.display.tooltip.type.ShortCharmTypeContributor;
import net.sf.anathema.hero.charms.sheet.content.IMagicStats;

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
    return new ShortCharmTypeContributor(resources).createTypeString(model);
  }

  @Override
  public String getDurationString(Resources resources) {
    return getMagic().getDuration().getText(resources);
  }

  @Override
  public String getSourceString(Resources resources) {
    IMagicSourceStringBuilder<Charm> stringBuilder = new MagicSourceContributor<>(resources);
    return stringBuilder.createShortSourceString(getMagic());
  }

  protected String[] getDetailKeys() {
    List<String> details = new ArrayList<>();
    for (MagicAttribute attribute : getMagic().getAttributes()) {
      String attributeId = attribute.getId();
      if (attribute.isVisualized()) {
        details.add("Keyword." + attributeId);
      }
    }
    return details.toArray(new String[details.size()]);
  }

  @Override
  public String[] getDetailStrings(final Resources resources) {
    return transform(getDetailKeys(), String.class, resources::getString);
  }

  @Override
  public int compareTo(IMagicStats stats) {
    if (stats instanceof AbstractCharmStats) {
      return 0;
    } else {
      return -1;
    }
  }
}