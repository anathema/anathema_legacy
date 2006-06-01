package net.sf.anathema.character.db.template.cult;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.collection.Predicate;

public class SequesteredTabernacleFreePicksPredicate extends Predicate<IMagic> {
  public boolean evaluate(IMagic magic) {
    if (magic instanceof ICharm) {
      ICharm charm = (ICharm) magic;
      if (charm.getId().equals("Dragon-Blooded.Ox-BodyTechnique")) { //$NON-NLS-1$
        return true;
      }
      if (charm.getGroupId().equals("EbonShadowStyle") || charm.getGroupId().equals("FallingBlossomStyle") || charm.getGroupId().equals("PrayingMantisStyle") || charm.getGroupId().equals("SnakeStyle") || charm.getGroupId().equals("TigerStyle")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        return true;
      }
    }
    return false;
  }
}