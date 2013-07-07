package net.sf.anathema.character.main.magic.model.charms.options;

import net.sf.anathema.character.main.magic.model.charmtree.CharmTree;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmTree;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.magic.model.charm.CharmHasSameTypeAsCharacter;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.magic.model.charm.GroupedCharmIdMap;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupArbitrator;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.main.magic.model.charm.IExtendedCharmData.EXCLUSIVE_ATTRIBUTE;
import static net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever.getNativeTemplate;

public class NonMartialArtsOptions implements CharmIdMap, CharmGroupArbitrator {

  private final CharacterTypes characterTypes;
  private final AvailableCharacterTypes availableTypes;
  private final Map<Identifier, ICharmTree> treesByType = new HashMap<>();
  private final CharmTemplateRetriever templateRetriever;
  private Hero hero;

  public NonMartialArtsOptions(Hero hero, CharacterTypes characterTypes, CharmTemplateRetriever charmTemplateRetriever) {
    this.hero = hero;
    this.characterTypes = characterTypes;
    this.templateRetriever = charmTemplateRetriever;
    this.availableTypes = new AvailableCharacterTypes(templateRetriever);
    availableTypes.collectAvailableTypes(getNativeCharacterType(), characterTypes);
    initCharmTreesForAvailableTypes();
  }

  public ICharmTree getCharmTrees(ICharacterType type) {
    return treesByType.get(type);
  }

  public Iterable<ICharacterType> getAvailableCharacterTypes() {
    return availableTypes;
  }

  @Override
  public ICharm getCharmById(String charmId) {
    GroupedCharmIdMap aggregatedTrees = new GroupedCharmIdMap(treesByType.values());
    return aggregatedTrees.getCharmById(charmId);
  }

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    ICharm[] allCharms = charmGroup.getAllCharms();
    if (characterMayLearnAlienCharms()) {
      return allCharms;
    }
    return charmsThatAreNativeOrNotExclusive(allCharms);
  }

  private ICharm[] charmsThatAreNativeOrNotExclusive(ICharm[] allCharms) {
    List<ICharm> charms = new ArrayList<>();
    for (ICharm charm : allCharms) {
      if (!charm.hasAttribute(EXCLUSIVE_ATTRIBUTE)) {
        charms.add(charm);
      }
      if (isNativeCharm(charm)) {
        charms.add(charm);
      }
    }
    return charms.toArray(new ICharm[charms.size()]);
  }

  private boolean isNativeCharm(ICharm charm) {
    return new CharmHasSameTypeAsCharacter(hero).apply(charm);
  }

  private boolean characterMayLearnAlienCharms() {
    HeroConcept concept = HeroConceptFetcher.fetch(hero);
    return getNativeTemplate(hero).isAllowedAlienCharms(concept.getCaste().getType());
  }

  public ICharacterType getNativeCharacterType() {
    return NativeCharacterType.get(hero);
  }

  private void initCharmTreesForAvailableTypes() {
    for (ICharacterType type : availableTypes) {
      ICharmTemplate charmTemplate = templateRetriever.getCharmTemplate(type);
      if (charmTemplate != null && type == getNativeCharacterType()) {
        treesByType.put(type, new CharmTree(charmTemplate));
      } else if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        treesByType.put(type, new CharmTree(charmTemplate));
      }
    }
  }

  public ICharacterType getCharacterType(String characterTypeId) {
    if (characterTypeId == null) {
      return getNativeCharacterType();
    }
    return characterTypes.findById(characterTypeId);
  }

  public ICharacterType[] getCharacterTypes(boolean includeAlienTypes) {
    if (!includeAlienTypes) {
      return new ICharacterType[]{getNativeCharacterType()};
    }
    return availableTypes.asArray();
  }

  public boolean isAlienType(ICharacterType characterType) {
    ICharacterType nativeType = getNativeCharacterType();
    return !characterType.equals(nativeType);
  }
}
