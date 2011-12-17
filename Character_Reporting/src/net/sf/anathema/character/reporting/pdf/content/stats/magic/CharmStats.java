package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharmStats extends AbstractCharmStats {

  private final IGenericCharacter character;

  public CharmStats(ICharm charm, IGenericCharacter character) {
    super(charm);
    this.character = character;
  }

  @Override
  public String[] getDetailKeys() {
    String[] detailKeys = super.getDetailKeys();
    final List<String> details = new ArrayList<String>();
    Collections.addAll(details, detailKeys);
    if (character.isSubeffectCharm(getMagic())) {
      for (String subeffectId : character.getLearnedEffects(getMagic())) {
        details.add(getMagic().getId() + ".Subeffects." + subeffectId);//$NON-NLS-1$
      }
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
