package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import com.google.common.base.Function;
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

import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public abstract class AbstractCharmStats extends AbstractMagicStats<ICharm> {

  public AbstractCharmStats(ICharm magic) {
    super(magic);
  }

  @Override
  public String getGroupName(IResources resources) {
    return resources.getString(getMagic().getGroupId());
  }

  @Override
  public String getType(IResources resources) {
    ICharmTypeModel model = getMagic().getCharmTypeModel();
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }

  @Override
  public String getDurationString(IResources resources) {
    return getMagic().getDuration().getText(resources);
  }

  @Override
  public String getSourceString(IResources resources) {
    IMagicSourceStringBuilder<ICharm> stringBuilder = new MagicSourceStringBuilder<>(resources);
    return stringBuilder.createShortSourceString(getMagic());
  }

  protected String[] getDetailKeys() {
    List<String> details = new ArrayList<>();
    for (ICharmAttribute attribute : getMagic().getAttributes()) {
      String attributeId = attribute.getId();
      if (attribute.isVisualized()) {
        details.add("Keyword." + attributeId); //$NON-NLS-1$
      }
    }
    return details.toArray(new String[details.size()]);
  }

  @Override
  public String[] getDetailStrings(final IResources resources) {
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
