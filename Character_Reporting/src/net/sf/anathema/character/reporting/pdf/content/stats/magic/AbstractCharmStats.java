package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCharmStats extends AbstractMagicStats<ICharm> {

  public AbstractCharmStats(ICharm magic) {
    super(magic);
  }

  public String getGroupName(final IResources resources) {
    return resources.getString(getMagic().getGroupId());
  }

  public String getType(IResources resources) {
    ICharmTypeModel model = getMagic().getCharmTypeModel();
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }

  public String getDurationString(final IResources resources) {
    return getMagic().getDuration().getText(resources);
  }

  public String getSourceString(IResources resources) {
    final IMagicSourceStringBuilder<ICharm> stringBuilder = new MagicSourceStringBuilder<ICharm>(resources);
    return stringBuilder.createShortSourceString(getMagic());
  }

  public String[] getDetailKeys() {
    final List<String> details = new ArrayList<String>();
    for (ICharmAttribute attribute : getMagic().getAttributes()) {
      final String attributeId = attribute.getId();
      if (attribute.isVisualized()) {
        details.add("Keyword." + attributeId); //$NON-NLS-1$
      }
    }
    return details.toArray(new String[details.size()]);
  }

  public int compareTo(IMagicStats stats) {
    if (stats instanceof AbstractGenericCharm) {
      return 1;
    }
    else if (stats instanceof AbstractCharmStats) {
      /*
      AbstractCharmStats charm = (AbstractCharmStats)stats;
      int r = getMagic().getGroupId().compareTo(charm.getMagic().getGroupId());
      if (r == 0) {
        r = this.getName().getId().compareTo(charm.getName().getId());
      }
      return r;
      */
      return 0;
    }
    else {
      return -1;
    }
  }
}
