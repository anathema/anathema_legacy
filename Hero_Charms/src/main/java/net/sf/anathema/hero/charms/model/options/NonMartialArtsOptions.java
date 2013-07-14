package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.character.main.magic.cache.CharmProvider;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmHasSameTypeAsCharacter;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.GroupedCharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.CharmTree;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmTree;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.main.magic.model.charm.CharmAttributeList.EXCLUSIVE_ATTRIBUTE;

public class NonMartialArtsOptions implements CharmIdMap, CharmGroupArbitrator {

  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;
  private final CharacterTypeList availableTypes;
  private final Map<Identifier, ICharmTree> treesByType = new HashMap<>();
  private Hero hero;

  public NonMartialArtsOptions(Hero hero, CharacterTypes characterTypes, CharmProvider charmProvider) {
    this.hero = hero;
    this.characterTypes = characterTypes;
    this.charmProvider = charmProvider;
    this.availableTypes = new CharacterTypeList(charmProvider);
    availableTypes.collectAvailableTypes(getNativeCharacterType(), characterTypes);
    initCharmTreesForAvailableTypes();
  }

  public ICharmTree getCharmTrees(CharacterType type) {
    return treesByType.get(type);
  }

  public Iterable<CharacterType> getAvailableCharacterTypes() {
    return availableTypes;
  }

  @Override
  public Charm getCharmById(String charmId) {
    GroupedCharmIdMap aggregatedTrees = new GroupedCharmIdMap(treesByType.values());
    return aggregatedTrees.getCharmById(charmId);
  }

  @Override
  public Charm[] getCharms(ICharmGroup charmGroup) {
    Charm[] allCharms = charmGroup.getAllCharms();
    if (characterMayLearnAlienCharms()) {
      return allCharms;
    }
    return charmsThatAreNativeOrNotExclusive(allCharms);
  }

  private Charm[] charmsThatAreNativeOrNotExclusive(Charm[] allCharms) {
    List<Charm> charms = new ArrayList<>();
    for (Charm charm : allCharms) {
      if (!charm.hasAttribute(EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
      if (isNativeCharm(charm)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new Charm[charms.size()]);
  }

  private boolean isNativeCharm(Charm charm) {
    return new CharmHasSameTypeAsCharacter(hero).apply(charm);
  }

  private boolean characterMayLearnAlienCharms() {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return hero.getTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(concept.getCaste().getType());
  }

  public CharacterType getNativeCharacterType() {
    return NativeCharacterType.get(hero);
  }

  private void initCharmTreesForAvailableTypes() {
    for (CharacterType type : availableTypes) {
      Charm[] charms = charmProvider.getCharms(type);
      treesByType.put(type, new CharmTree(charms));
    }
  }

  public CharacterType getCharacterType(String characterTypeId) {
    if (characterTypeId == null) {
      return getNativeCharacterType();
    }
    return characterTypes.findById(characterTypeId);
  }

  public CharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    if (!includeAlienTypes) {
      return new CharacterType[]{getNativeCharacterType()};
    }
    return availableTypes.asArray();
  }

  public boolean isAlienType(CharacterType characterType) {
    CharacterType nativeType = getNativeCharacterType();
    return !characterType.equals(nativeType);
  }
}
