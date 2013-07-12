package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.magic.model.charmtree.CharmTree;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmTree;
import net.sf.anathema.character.main.magic.model.charmtree.MartialArtsCharmTree;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.magic.CharmProvider;
import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.model.CharmGroupCollection;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CascadeGroupCollection implements CharmGroupCollection {
  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;
  private ITemplateRegistry templateRegistry;
  private CharmTreeIdentifierMap treeIdentifierMap;

  public CascadeGroupCollection(CharmProvider charmProvider, CharacterTypes characterTypes, ITemplateRegistry templateRegistry,
                                CharmTreeIdentifierMap treeIdentifierMap) {
    this.charmProvider = charmProvider;
    this.templateRegistry = templateRegistry;
    this.treeIdentifierMap = treeIdentifierMap;
    this.characterTypes = characterTypes;
  }

  @Override
  public ICharmGroup[] getCharmGroups() {
    List<ICharmGroup> allCharmGroups = new ArrayList<>();
    initCharacterTypeCharms(allCharmGroups);
    initMartialArtsCharms(allCharmGroups);
    return allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]);
  }

  private void initCharacterTypeCharms(List<ICharmGroup> allCharmGroups) {
    for (CharacterType type : characterTypes.findAll()) {
      HeroTemplate template = templateRegistry.getDefaultTemplate(type);
      if (template == null) {
        continue;
      }
      CharmTemplate charmTemplate = DefaultCharmTemplateRetriever.getCharmTemplate(template);
      if (charmTemplate.canLearnCharms()) {
        registerTypeCharms(allCharmGroups, type, template);
      }
    }
  }

  private void initMartialArtsCharms(List<ICharmGroup> allCharmGroups) {
    ICharmTree martialArtsTree = new MartialArtsCharmTree(charmProvider, MartialArtsLevel.Sidereal);
    treeIdentifierMap.put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
    allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
  }

  private void registerTypeCharms(List<ICharmGroup> allCharmGroups, CharacterType type, HeroTemplate defaultTemplate) {
    ICharmTree typeTree = new CharmTree(DefaultCharmTemplateRetriever.getCharmTemplate(defaultTemplate));
    registerGroups(allCharmGroups, type, typeTree);
  }

  private void registerGroups(List<ICharmGroup> allCharmGroups, Identifier typeId, ICharmTree charmTree) {
    ICharmGroup[] groups = charmTree.getAllCharmGroups();
    if (groups.length != 0) {
      treeIdentifierMap.put(typeId, charmTree);
      allCharmGroups.addAll(Arrays.asList(groups));
    }
  }
}