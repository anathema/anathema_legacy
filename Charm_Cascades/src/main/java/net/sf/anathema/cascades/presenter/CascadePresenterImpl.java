package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.tree.AbstractCascadePresenter;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.resources.Resources;

public class CascadePresenterImpl extends AbstractCascadePresenter implements CascadePresenter {

  public CascadePresenterImpl(Resources resources, HeroEnvironment environment, CharmView view,
                              MagicDescriptionProvider magicDescriptionProvider, CharmTreeIdentifierMap identifierMap) {
    super(resources, identifierMap);
    CharmCache cache = environment.getDataSet(CharmCache.class);
    CascadeSpecialCharmSet specialCharmSet = new CascadeSpecialCharmSet(cache);
    CharmDisplayPropertiesMap charmDisplayPropertiesMap = new CharmDisplayPropertiesMap(environment.getObjectFactory());
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, specialCharmSet,
            charmDisplayPropertiesMap);
    CharacterTypes characterTypes = environment.getCharacterTypes();
    setCharmTypes(new CascadeCharmTypes(characterTypes, cache.getCharmProvider()));
    setChangeListener(selectionListener);
    setView(view);
    CharmDye dye = new ConfigurableCharmDye(selectionListener, new CascadeColoringStrategy(view));
    setCharmDye(dye);
    setCharmGroups(new CascadeGroupCollection(cache.getCharmProvider(), characterTypes, identifierMap));
    addTreeView(specialCharmSet, magicDescriptionProvider, cache);
  }
}