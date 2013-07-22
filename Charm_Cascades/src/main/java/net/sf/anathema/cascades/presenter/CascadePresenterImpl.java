package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.CascadeViewFactory;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.tree.AbstractCascadePresenter;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.DefaultFunctionalNodeProperties;
import net.sf.anathema.hero.charms.display.view.DefaultNodePresentationProperties;
import net.sf.anathema.hero.charms.display.view.DefaultTooltipProperties;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadePresenterImpl extends AbstractCascadePresenter implements CascadePresenter {

  private final CharmTreeIdentifierMap treeIdentifierMap = new CharmTreeIdentifierMap();

  public CascadePresenterImpl(Resources resources, HeroEnvironment environment, CascadeViewFactory factory,
                              MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    CharmCache cache = environment.getDataSet(CharmCache.class);
    CharmView view = factory.createCascadeView();
    CascadeSpecialCharmSet specialCharmSet = addTreeView(resources, magicDescriptionProvider, cache, view);
    CharmDisplayPropertiesMap charmDisplayPropertiesMap = new CharmDisplayPropertiesMap(environment.getObjectFactory());
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, specialCharmSet,
            charmDisplayPropertiesMap);
    CharacterTypes characterTypes = environment.getCharacterTypes();
    setCharmTypes(new CascadeCharmTypes(characterTypes, cache.getCharmProvider()));
    setChangeListener(selectionListener);
    setView(view);
    CharmDye dye = new ConfigurableCharmDye(selectionListener, new CascadeColoringStrategy(view));
    setCharmDye(dye);
    setCharmGroups(new CascadeGroupCollection(cache.getCharmProvider(), characterTypes, treeIdentifierMap));
  }

  private CascadeSpecialCharmSet addTreeView(Resources resources, MagicDescriptionProvider magicDescriptionProvider,
                                             CharmCache cache, CharmView view) {
    DefaultFunctionalNodeProperties functionalNodeProperties = new DefaultFunctionalNodeProperties();
    DefaultNodePresentationProperties nodeProperties = new DefaultNodePresentationProperties(resources,
            functionalNodeProperties, cache);
    CascadeSpecialCharmSet specialCharmSet = new CascadeSpecialCharmSet(cache);
    DefaultTooltipProperties tooltipProperties = new DefaultTooltipProperties(functionalNodeProperties, cache,
            resources, magicDescriptionProvider, specialCharmSet);
    view.addTreeView(tooltipProperties, nodeProperties);
    return specialCharmSet;
  }

  @Override
  protected GroupCharmTree getCharmTree(Identifier type) {
    return treeIdentifierMap.get(type);
  }
}