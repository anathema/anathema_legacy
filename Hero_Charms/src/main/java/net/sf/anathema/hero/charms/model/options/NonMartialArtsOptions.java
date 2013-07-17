package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmHasSameTypeAsCharacter;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.charm.GroupedCharmIdMap;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.charmtree.CharmTree;
import net.sf.anathema.character.main.magic.charmtree.ICharmTree;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupArbitrator;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.main.magic.charm.CharmAttributeList.EXCLUSIVE_ATTRIBUTE;

public class NonMartialArtsOptions implements CharmIdMap, CharmGroupArbitrator {

  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;
  private CharmsRules charmsRules;
  private final CharacterTypeList availableTypes;
  private final Map<Identifier, ICharmTree> treesByType = new HashMap<>();
  private Hero hero;

  public NonMartialArtsOptions(Hero hero, CharacterTypes characterTypes, CharmProvider charmProvider, CharmsRules charmsRules) {
    this.hero = hero;
    this.characterTypes = characterTypes;
    this.charmProvider = charmProvider;
    this.charmsRules = charmsRules;
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
    return charmsRules.isAllowedAlienCharms(concept.getCaste().getType());
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
