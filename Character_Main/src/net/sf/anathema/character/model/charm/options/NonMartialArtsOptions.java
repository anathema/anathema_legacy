package net.sf.anathema.character.model.charm.options;

import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.model.charm.CharmHasSameTypeAsCharacter;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.model.charm.GroupedCharmIdMap;
import net.sf.anathema.charmtree.view.CharmGroupArbitrator;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.template.NativeCharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.generic.magic.IExtendedCharmData.EXCLUSIVE_ATTRIBUTE;

public class NonMartialArtsOptions implements CharmIdMap, CharmGroupArbitrator {

  private final CharacterTypes characterTypes;
  private final ITemplateRegistry registry;
  private final List<ICharacterType> availableTypes = new ArrayList<>();
  private final Map<Identifier, ICharmTree> treesByType = new HashMap<>();
  private Hero hero;

  public NonMartialArtsOptions(Hero hero, CharacterTypes characterTypes, ITemplateRegistry registry) {
    this.hero = hero;
    this.characterTypes = characterTypes;
    this.registry = registry;
    collectAvailableTypes();
    initCharmTreesForAvailableTypes();
  }

  private void collectAvailableTypes() {
    for (ICharacterType type : this.characterTypes.findAll()) {
      ICharmTemplate charmTemplate = getCharmTemplate(registry, type);
      if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        availableTypes.add(type);
      }
    }
    availableTypes.remove(getNativeCharacterType());
    availableTypes.add(0, getNativeCharacterType());
  }

  public ICharmTree getCharmTrees(ICharacterType type) {
    return treesByType.get(type);
  }

  public Iterable<ICharacterType> getAvailableCharacterTypes() {
    return new ArrayList<>(availableTypes);
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
    return getNativeCharmTemplate().isAllowedAlienCharms(concept.getCaste().getType());
  }

  public ICharacterType getNativeCharacterType() {
    return NativeCharacterType.get(hero);
  }

  public ICharmTemplate getNativeCharmTemplate() {
    ITemplateType templateType = hero.getTemplate().getTemplateType();
    HeroTemplate template = registry.getTemplate(templateType);
    IMagicTemplate magicTemplate = template.getMagicTemplate();
    return magicTemplate.getCharmTemplate();
  }

  private void initCharmTreesForAvailableTypes() {
    for (ICharacterType type : availableTypes) {
      ICharmTemplate charmTemplate = getCharmTemplate(registry, type);
      if (charmTemplate != null && type == getNativeCharacterType()) {
        treesByType.put(type, new CharmTree(charmTemplate));
      } else if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        treesByType.put(type, new CharmTree(charmTemplate));
      }
    }
  }

  private ICharmTemplate getCharmTemplate(ITemplateRegistry registry, ICharacterType type) {
    HeroTemplate defaultTemplate = registry.getDefaultTemplate(type);
    if (defaultTemplate == null) {
      return null;
    }
    return defaultTemplate.getMagicTemplate().getCharmTemplate();
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
    return availableTypes.toArray(new ICharacterType[availableTypes.size()]);
  }

  public boolean isAlienType(ICharacterType characterType) {
    ICharacterType nativeType = getNativeCharacterType();
    return !characterType.equals(nativeType);
  }
}
