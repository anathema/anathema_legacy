package net.sf.anathema.character.main.magic.model.charmtree.builder;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class MagicDisplayLabeler {
  private Resources resources;

  public MagicDisplayLabeler(Resources resources) {
    this.resources = resources;
  }

  public String getLabelForMagic(Magic magic) {
    if (magic instanceof Charm && ((Charm) magic).isInstanceOfGenericCharm()) {
      TraitType charmType = ((Charm) magic).getPrimaryTraitType();
      String baseCharmId = getGenericCharmBaseId((Charm) magic);
      return resources.getString(baseCharmId, resources.getString(charmType.getId()));
    }
    return resources.getString(magic.getId());
  }

  public String getGenericLabelForMagic(Magic magic) {
    if (magic instanceof Charm && ((Charm) magic).isInstanceOfGenericCharm()) {
      Identifier favoringTraitType = ((Charm) magic).getCharacterType().getFavoringTraitType();
      String traitString = "(" + resources.getString(favoringTraitType.getId()) + ")";
      String baseCharmId = getGenericCharmBaseId((Charm) magic);
      return resources.getString(baseCharmId, traitString);
    }
    return resources.getString(magic.getId());
  }

  public boolean supportsMagic(Magic magic) {
    if (magic == null) {
      return false;
    }
    if (magic instanceof Charm && ((Charm) magic).isInstanceOfGenericCharm()) {
      String baseCharmId = getGenericCharmBaseId((Charm) magic);
      return resources.supportsKey(baseCharmId);
    }
    return resources.supportsKey(magic.getId());
  }

  private String getGenericCharmBaseId(Charm charm) {
    return charm.getId().substring(0, charm.getId().lastIndexOf('.'));
  }
}
