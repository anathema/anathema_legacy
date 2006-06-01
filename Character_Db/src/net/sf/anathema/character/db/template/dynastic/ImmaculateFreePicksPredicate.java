package net.sf.anathema.character.db.template.dynastic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.collection.Predicate;

public class ImmaculateFreePicksPredicate extends Predicate<IMagic> {

  public boolean evaluate(IMagic magic) {
    if (magic instanceof ICharm) {
      ICharm charm = (ICharm) magic;
      if (charm.getId().equals("Dragon-Blooded.Ox-BodyTechnique") || charm.getId().equals("Dragon-Blooded.SpiritSight") || charm.getId().equals("Dragon-Blooded.SpiritWalking")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return true;
      }
      if (charm.getGroupId().equals("AirDragonStyle") || charm.getGroupId().equals("EarthDragonStyle") || charm.getGroupId().equals("FireDragonStyle") || charm.getGroupId().equals("WaterDragonStyle") || charm.getGroupId().equals("WoodDragonStyle")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        return true;
      }
    }
    return false;
  }
}