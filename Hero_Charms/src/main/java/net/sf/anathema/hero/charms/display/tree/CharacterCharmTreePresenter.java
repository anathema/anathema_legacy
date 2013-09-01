package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.coloring.CharacterColoringStrategy;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.hero.charms.display.model.CharacterCharmTypes;
import net.sf.anathema.hero.charms.display.model.CharacterGroupCollection;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.special.AgnosticSpecialCharmViewBuilder;
import net.sf.anathema.hero.charms.display.special.CharacterSpecialCharmPresenter;
import net.sf.anathema.hero.charms.display.special.CommonSpecialCharmList;
import net.sf.anathema.hero.charms.display.special.SpecialCharmViewBuilder;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.DefaultFunctionalNodeProperties;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.special.SpecialCharmList;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CharacterCharmTreePresenter {

  private final CharmView view;
  private final CascadePresenter cascadePresenter;
  private final Resources resources;
  private CharmDisplayModel model;
  private TreePresentationProperties presentationProperties;
  private CharmDisplayPropertiesMap displayPropertiesMap;

  public CharacterCharmTreePresenter(Resources resources, CharmView view, CharmDisplayModel model,
                                     TreePresentationProperties presentationProperties,
                                     CharmDisplayPropertiesMap displayPropertiesMap, CharmIdMap charmIdMap,
                                     MagicDescriptionProvider magicDescriptionProvider) {
    this.resources = resources;
    this.model = model;
    this.presentationProperties = presentationProperties;
    this.displayPropertiesMap = displayPropertiesMap;
    this.cascadePresenter = new CascadePresenter(resources, charmIdMap, magicDescriptionProvider);
    this.view = view;
  }

  public void initPresentation() {
    CharmsModel charmConfiguration = model.getCharmModel();
    CharacterCharmGroupChangeListener charmGroupChangeListener = new CharacterCharmGroupChangeListener(
            charmConfiguration, displayPropertiesMap);
    ConfigurableCharmDye colorist = new ConfigurableCharmDye(charmGroupChangeListener,
            new CharacterColoringStrategy(presentationProperties.getColor(), model));
    cascadePresenter.setCharmTreeMap(new CharacterCharmTreeMap(model));
    cascadePresenter.setCharmTypes(new CharacterCharmTypes(model));
    cascadePresenter.setChangeListener(charmGroupChangeListener);
    cascadePresenter.setView(view);
    SpecialCharmViewBuilder specialViewBuilder = new AgnosticSpecialCharmViewBuilder(resources, charmConfiguration, view);
    SpecialCharmList specialCharmList = new CommonSpecialCharmList(specialViewBuilder);
    cascadePresenter.setSpecialPresenter(
            new CharacterSpecialCharmPresenter(charmGroupChangeListener, model, specialCharmList));
    cascadePresenter.setCharmDye(colorist);
    cascadePresenter.setAlienCharmPresenter(new CharacterAlienCharmPresenter(model));
    cascadePresenter.setInteractionPresenter(
            new LearnInteractionPresenter(model, new DefaultFunctionalNodeProperties(), colorist));
    cascadePresenter.setCharmGroups(new CharacterGroupCollection(model));
    cascadePresenter.setSpecialCharmSet(new CharacterSpecialCharmSet(model));
    cascadePresenter.initPresentation();
  }
}