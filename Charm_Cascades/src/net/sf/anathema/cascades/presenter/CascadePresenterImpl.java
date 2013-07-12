package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.CascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.character.main.magic.parser.charms.CharmCache;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.character.main.magic.model.charmtree.GroupCharmTree;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.display.tree.AbstractCascadePresenter;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmDisplayPropertiesMap;
import net.sf.anathema.character.main.magic.display.view.charmtree.DefaultNodeProperties;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadePresenterImpl extends AbstractCascadePresenter implements CascadePresenter {

  private final CharmTreeIdentifierMap treeIdentifierMap = new CharmTreeIdentifierMap();

  public CascadePresenterImpl(Resources resources, HeroEnvironment generics, CascadeViewFactory factory,
                              MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    CharmCache cache = generics.getDataSet(CharmCache.class);
    CascadeCharmTreeViewProperties viewProperties =
            new CascadeCharmTreeViewProperties(resources, magicDescriptionProvider, generics, cache, treeIdentifierMap);
    DefaultNodeProperties nodeProperties = new DefaultNodeProperties(resources, viewProperties, viewProperties);
    CascadeView view = factory.createCascadeView(viewProperties, nodeProperties);
    view.initGui(viewProperties, nodeProperties);
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    CascadeCharmGroupChangeListener selectionListener =
            new CascadeCharmGroupChangeListener(view, viewProperties, new CharmDisplayPropertiesMap(templateRegistry));
    CharacterTypes characterTypes = generics.getCharacterTypes();
    setCharmTypes(new CascadeCharmTypes(characterTypes, templateRegistry));
    setChangeListener(selectionListener);
    setView(view);
    CharmDye dye = new ConfigurableCharmDye(selectionListener, new CascadeColoringStrategy(view));
    setCharmDye(dye);
    setCharmGroups(new CascadeGroupCollection(characterTypes, templateRegistry, treeIdentifierMap));
  }

  @Override
  protected GroupCharmTree getCharmTree(Identifier type) {
    return treeIdentifierMap.get(type);
  }
}