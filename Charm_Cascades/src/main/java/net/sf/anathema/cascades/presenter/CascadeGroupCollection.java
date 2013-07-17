package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;
import net.sf.anathema.hero.charms.model.options.CharmTree;
import net.sf.anathema.hero.charms.model.options.CharmTreeImpl;
import net.sf.anathema.hero.charms.model.options.MartialArtsCharmTree;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.model.CharmGroupCollection;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;
import net.sf.anathema.charms.MartialArtsUtilities;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CascadeGroupCollection implements CharmGroupCollection {
  private final CharacterTypes characterTypes;
  private CharmProvider charmProvider;
  private CharmTreeIdentifierMap treeIdentifierMap;

  public CascadeGroupCollection(CharmProvider charmProvider, CharacterTypes characterTypes, CharmTreeIdentifierMap treeIdentifierMap) {
    this.charmProvider = charmProvider;
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
       if (charmProvider.getCharms(type).length > 0) {
        registerTypeCharms(allCharmGroups, type);
      }
    }
  }

  private void initMartialArtsCharms(List<ICharmGroup> allCharmGroups) {
    CharmTree martialArtsTree = new MartialArtsCharmTree(charmProvider, MartialArtsLevel.Sidereal);
    treeIdentifierMap.put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
    allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
  }

  private void registerTypeCharms(List<ICharmGroup> allCharmGroups, CharacterType type) {
    CharmTree typeTree = new CharmTreeImpl(charmProvider.getCharms(type));
    registerGroups(allCharmGroups, type, typeTree);
  }

  private void registerGroups(List<ICharmGroup> allCharmGroups, Identifier typeId, CharmTree charmTree) {
    ICharmGroup[] groups = charmTree.getAllCharmGroups();
    if (groups.length != 0) {
      treeIdentifierMap.put(typeId, charmTree);
      allCharmGroups.addAll(Arrays.asList(groups));
    }
  }
}