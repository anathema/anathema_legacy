package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.CascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.view.DefaultNodeProperties;
import net.sf.anathema.character.main.magic.charmtree.GroupCharmTree;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.hero.charms.display.tree.AbstractCascadePresenter;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadePresenterImpl extends AbstractCascadePresenter implements CascadePresenter {

  private final CharmTreeIdentifierMap treeIdentifierMap = new CharmTreeIdentifierMap();

  public CascadePresenterImpl(Resources resources, HeroEnvironment environment, CascadeViewFactory factory,
                              MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    CharmCache cache = environment.getDataSet(CharmCache.class);
    CascadeCharmTreeViewProperties viewProperties = new CascadeCharmTreeViewProperties(resources, magicDescriptionProvider, cache);
    DefaultNodeProperties nodeProperties = new DefaultNodeProperties(resources, viewProperties, viewProperties);
    CascadeView view = factory.createCascadeView(viewProperties, nodeProperties);
    view.initGui(viewProperties, nodeProperties);
    CharmDisplayPropertiesMap charmDisplayPropertiesMap = new CharmDisplayPropertiesMap(environment.getObjectFactory());
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties, charmDisplayPropertiesMap);
    CharacterTypes characterTypes = environment.getCharacterTypes();
    setCharmTypes(new CascadeCharmTypes(characterTypes, cache.getCharmProvider()));
    setChangeListener(selectionListener);
    setView(view);
    CharmDye dye = new ConfigurableCharmDye(selectionListener, new CascadeColoringStrategy(view));
    setCharmDye(dye);
    setCharmGroups(new CascadeGroupCollection(cache.getCharmProvider(), characterTypes, treeIdentifierMap));
  }

  @Override
  protected GroupCharmTree getCharmTree(Identifier type) {
    return treeIdentifierMap.get(type);
  }
}