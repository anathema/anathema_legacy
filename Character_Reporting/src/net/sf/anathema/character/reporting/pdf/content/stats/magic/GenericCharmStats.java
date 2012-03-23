package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class GenericCharmStats extends CharmStats {

  private final String genericId;
	
  public GenericCharmStats(ICharm charm, IGenericCharacter character) {
    super(charm, character);
    
    String charmId = charm.getId();
    genericId = charmId.substring(0, charmId.lastIndexOf('.'));
  }

  @Override
  public String[] getDetailStrings(IResources resources) {
	  String description = resources.getString(getName() + ".Description.Short");
	  return new String[]{description}; //$NON-NLS-1$
  }
  
  @Override
  public IIdentificate getName() {
    return new Identificate(genericId);
  }
  
  @Override
  public String getNameString(IResources resources)
  {
	  return resources.getString(genericId);
  }
  
  @Override
  public final String getGroupName(IResources resources) {
    return resources.getString("Generics"); //$NON-NLS-1$
  }
  
  @Override
  public boolean equals(Object obj)
  {
	  return obj instanceof GenericCharmStats && this.getName().getId().equals(((GenericCharmStats)obj).getName().getId());
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
