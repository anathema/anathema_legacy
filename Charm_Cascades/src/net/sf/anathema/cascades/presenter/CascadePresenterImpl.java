package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.CascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.CascadeView;
import net.sf.anathema.character.main.magic.cache.CharmCache;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmDisplayPropertiesMap;
import net.sf.anathema.character.main.magic.display.view.charmtree.DefaultNodeProperties;
import net.sf.anathema.character.main.magic.model.charmtree.GroupCharmTree;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.hero.charms.display.tree.AbstractCascadePresenter;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadePresenterImpl extends AbstractCascadePresenter implements CascadePresenter {

  private final CharmTreeIdentifierMap treeIdentifierMap = new CharmTreeIdentifierMap();

  public CascadePresenterImpl(Resources resources, HeroEnvironment generics, CascadeViewFactory factory,
                              MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    CharmCache cache = generics.getDataSet(CharmCache.class);
    CascadeCharmTreeViewProperties viewProperties = new CascadeCharmTreeViewProperties(resources, magicDescriptionProvider, generics, cache);
    DefaultNodeProperties nodeProperties = new DefaultNodeProperties(resources, viewProperties, viewProperties);
    CascadeView view = factory.createCascadeView(viewProperties, nodeProperties);
    view.initGui(viewProperties, nodeProperties);
    CharmDisplayPropertiesMap charmDisplayPropertiesMap = new CharmDisplayPropertiesMap(generics.getObjectFactory());
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties, charmDisplayPropertiesMap);
    CharacterTypes characterTypes = generics.getCharacterTypes();
    setCharmTypes(new CascadeCharmTypes(characterTypes, generics.getCharmCache().getCharmProvider()));
    setChangeListener(selectionListener);
    setView(view);
    CharmDye dye = new ConfigurableCharmDye(selectionListener, new CascadeColoringStrategy(view));
    setCharmDye(dye);
    setCharmGroups(new CascadeGroupCollection(generics.getCharmCache().getCharmProvider(), characterTypes, treeIdentifierMap));
  }

  @Override
  protected GroupCharmTree getCharmTree(Identifier type) {
    return treeIdentifierMap.get(type);
  }
}