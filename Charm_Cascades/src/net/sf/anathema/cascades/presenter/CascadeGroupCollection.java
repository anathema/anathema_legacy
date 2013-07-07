package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.charmtree.CharmTree;
import net.sf.anathema.character.main.magic.model.charmtree.MartialArtsCharmTree;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmTree;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.magic.display.presenter.CharmGroupCollection;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CascadeGroupCollection implements CharmGroupCollection {
  private final CharacterTypes characterTypes;
  private ITemplateRegistry templateRegistry;
  private CharmTreeIdentificateMap treeIdentificateMap;

  public CascadeGroupCollection(CharacterTypes characterTypes, ITemplateRegistry templateRegistry, CharmTreeIdentificateMap treeIdentificateMap) {
    this.templateRegistry = templateRegistry;
    this.treeIdentificateMap = treeIdentificateMap;
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
    for (ICharacterType type : characterTypes.findAll()) {
      HeroTemplate template = templateRegistry.getDefaultTemplate(type);
      if (template == null) {
        continue;
      }
      ICharmTemplate charmTemplate = DefaultCharmTemplateRetriever.getCharmTemplate(template);
      if (charmTemplate.canLearnCharms()) {
        registerTypeCharms(allCharmGroups, type, template);
      }
    }
  }

  private void initMartialArtsCharms(List<ICharmGroup> allCharmGroups) {
    ICharmTemplate charmTemplate = findCharmTemplateOfCharacterTypeMostProficientWithMartialArts();
    ICharmTree martialArtsTree = new MartialArtsCharmTree(charmTemplate);
    treeIdentificateMap.put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
    allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
  }

  private ICharmTemplate findCharmTemplateOfCharacterTypeMostProficientWithMartialArts() {
    ICharmTemplate currentFavoriteTemplate = new NullCharmTemplate();
    for (ICharacterType type : characterTypes.findAll()) {
      HeroTemplate defaultTemplate = templateRegistry.getDefaultTemplate(type);
      if (defaultTemplate == null) {
        continue;
      }
      ICharmTemplate charmTemplate = DefaultCharmTemplateRetriever.getCharmTemplate(defaultTemplate);
      MartialArtsLevel martialArtsLevel = charmTemplate.getMartialArtsRules().getStandardLevel();
      MartialArtsLevel highestLevelSoFar = currentFavoriteTemplate.getMartialArtsRules().getStandardLevel();
      if (martialArtsLevel.compareTo(highestLevelSoFar) > 0) {
        currentFavoriteTemplate = charmTemplate;
      }
    }
    return currentFavoriteTemplate;
  }

  private void registerTypeCharms(List<ICharmGroup> allCharmGroups, ICharacterType type, HeroTemplate defaultTemplate) {
    ICharmTree typeTree = new CharmTree(DefaultCharmTemplateRetriever.getCharmTemplate(defaultTemplate));
    registerGroups(allCharmGroups, type, typeTree);
  }

  private void registerGroups(List<ICharmGroup> allCharmGroups, Identifier typeId, ICharmTree charmTree) {
    ICharmGroup[] groups = charmTree.getAllCharmGroups();
    if (groups.length != 0) {
      treeIdentificateMap.put(typeId, charmTree);
      allCharmGroups.addAll(Arrays.asList(groups));
    }
  }
}