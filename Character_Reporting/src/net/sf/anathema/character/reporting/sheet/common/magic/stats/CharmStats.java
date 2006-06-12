package net.sf.anathema.character.reporting.sheet.common.magic.stats;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.lib.resources.IResources;

public class CharmStats extends AbstractMagicStats<ICharm> {

  private final IGenericCharacter character;

  public CharmStats(ICharm charm, IGenericCharacter character) {
    super(charm);
    this.character = character;
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

  public String[] getDetailKeys() {
    final List<String> details = new ArrayList<String>();
    for (ICharmAttribute attribute : getMagic().getAttributes()) {
      final String attributeId = attribute.getId();
      if (attribute.isVisualized()) {
        details.add("Keyword." + attributeId); //$NON-NLS-1$
      }
    }
    for (String subeffectId : character.getLearnedSubeffects(getMagic())) {
      details.add(getMagic().getId() + ".Subeffects." + subeffectId);//$NON-NLS-1$
    }
    return details.toArray(new String[details.size()]);
  }

  public String getNameString(IResources resources) {
    final StringBuilder nameString = new StringBuilder();
    nameString.append(resources.getString(getMagic().getId()));
    int learnCount = character.getLearnCount(getMagic());
    if (learnCount > 1) {
      nameString.append(" ("); //$NON-NLS-1$
      nameString.append(learnCount);
      nameString.append("x)"); //$NON-NLS-1$
    }
    return nameString.toString();
  }

  public String getSourceString(IResources resources) {
    final IMagicSourceStringBuilder<ICharm> stringBuilder = new MagicSourceStringBuilder<ICharm>(resources);
    return stringBuilder.createShortSourceString(getMagic());
  }
}