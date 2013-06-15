package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public class GenericCharmStats extends CharmStats {

  private final String genericId;

  public GenericCharmStats(ICharm charm, IGenericCharacter character) {
    super(charm, character);

    String charmId = charm.getId();
    genericId = charmId.substring(0, charmId.lastIndexOf('.'));
  }

  @Override
  public String[] getDetailStrings(Resources resources) {
    String description = resources.getString(getName() + ".Description.Short");
    return new String[]{description};
  }

  @Override
  public Identified getName() {
    return new Identifier(genericId);
  }

  @Override
  public String getNameString(Resources resources) {
    return new MagicDisplayLabeler(resources).getGenericLabelForMagic(getMagic());
  }

  @Override
  public final String getGroupName(Resources resources) {
    return resources.getString("Generics");
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof GenericCharmStats && this.getName().getId().equals(((GenericCharmStats) obj).getName().getId());
  }

  @Override
  public int compareTo(IMagicStats stats) {
    if (stats instanceof GenericCharmStats) {
      return this.getName().getId().compareTo(stats.getName().getId());
    } else {
      return -1;
    }
  }
}
