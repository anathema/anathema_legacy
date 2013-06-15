package net.sf.anathema.charmtree.builder;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

public class MagicDisplayLabeler {
  private Resources resources;

  public MagicDisplayLabeler(Resources resources) {
    this.resources = resources;
  }

  public String getLabelForMagic(IMagic magic) {
    if (magic instanceof ICharm && ((ICharm) magic).isInstanceOfGenericCharm()) {
      ITraitType charmType = ((ICharm) magic).getPrimaryTraitType();
      String baseCharmId = getGenericCharmBaseId((ICharm) magic);
      return resources.getString(baseCharmId, resources.getString(charmType.getId()));
    }
    return resources.getString(magic.getId());
  }

  public String getGenericLabelForMagic(IMagic magic) {
    if (magic instanceof ICharm && ((ICharm) magic).isInstanceOfGenericCharm()) {
      Identified favoringTraitType = ((ICharm) magic).getCharacterType().getFavoringTraitType();
      String traitString = "(" + resources.getString(favoringTraitType.getId()) + ")";
      String baseCharmId = getGenericCharmBaseId((ICharm) magic);
      return resources.getString(baseCharmId, traitString);
    }
    return resources.getString(magic.getId());
  }

  public boolean supportsMagic(IMagic magic) {
    if (magic == null) {
      return false;
    }
    if (magic instanceof ICharm && ((ICharm) magic).isInstanceOfGenericCharm()) {
      String baseCharmId = getGenericCharmBaseId((ICharm) magic);
      return resources.supportsKey(baseCharmId);
    }
    return resources.supportsKey(magic.getId());
  }

  private String getGenericCharmBaseId(ICharm charm) {
    return charm.getId().substring(0, charm.getId().lastIndexOf('.'));
  }
}
