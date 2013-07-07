package net.sf.anathema.character.main.magic.model.charmtree.builder;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class MagicDisplayLabeler {
  private Resources resources;

  public MagicDisplayLabeler(Resources resources) {
    this.resources = resources;
  }

  public String getLabelForMagic(IMagic magic) {
    if (magic instanceof ICharm && ((ICharm) magic).isInstanceOfGenericCharm()) {
      TraitType charmType = ((ICharm) magic).getPrimaryTraitType();
      String baseCharmId = getGenericCharmBaseId((ICharm) magic);
      return resources.getString(baseCharmId, resources.getString(charmType.getId()));
    }
    return resources.getString(magic.getId());
  }

  public String getGenericLabelForMagic(IMagic magic) {
    if (magic instanceof ICharm && ((ICharm) magic).isInstanceOfGenericCharm()) {
      Identifier favoringTraitType = ((ICharm) magic).getCharacterType().getFavoringTraitType();
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
