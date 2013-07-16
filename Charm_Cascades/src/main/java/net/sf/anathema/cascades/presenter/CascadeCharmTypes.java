package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.charmtree.cache.CharmProvider;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.display.model.AbstractCharmTypes;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CascadeCharmTypes extends AbstractCharmTypes {
  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;

  public CascadeCharmTypes(CharacterTypes characterTypes, CharmProvider charmProvider) {
    this.charmProvider = charmProvider;
    this.characterTypes = characterTypes;
  }

  @Override
  protected List<Identifier> getCurrentCharacterTypes() {
    Set<Identifier> set = new LinkedHashSet<>();
    for (CharacterType type : characterTypes.findAll()) {
       if (charmProvider.getCharms(type).length > 0) {
        set.add(type);
      }
    }
    return new ArrayList<>(set);
  }
}