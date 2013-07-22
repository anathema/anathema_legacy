package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.coloring.CharacterColoringStrategy;
import net.sf.anathema.hero.charms.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.hero.charms.display.model.CharacterCharmTypes;
import net.sf.anathema.hero.charms.display.model.CharacterGroupCollection;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.special.CharacterSpecialCharmPresenter;
import net.sf.anathema.hero.charms.display.special.CommonSpecialCharmList;
import net.sf.anathema.hero.charms.display.special.SpecialCharmViewBuilder;
import net.sf.anathema.hero.charms.display.special.SwingSpecialCharmViewBuilder;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.DefaultFunctionalNodeProperties;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.hero.charms.model.special.SpecialCharmList;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.magic.description.display.ShowMagicDetailListener;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CharacterCharmTreePresenter extends AbstractCascadePresenter {

  private final CharmView view;
  private final CharmIdMap charmIdMap;
  private final CharmDisplayModel model;

  public CharacterCharmTreePresenter(Resources resources, CharmView view, CharmDisplayModel charmModel,
                                     TreePresentationProperties presentationProperties,
                                     CharmDisplayPropertiesMap displayPropertiesMap, CharmIdMap charmIdMap) {
    super(resources);
    this.model = charmModel;
    this.view = view;
    this.charmIdMap = charmIdMap;
    CharmsModel charmConfiguration = model.getCharmModel();
    addTreeView(resources, view);
    CharacterCharmGroupChangeListener charmGroupChangeListener = new CharacterCharmGroupChangeListener(
            charmConfiguration, view.getCharmTreeRenderer(), displayPropertiesMap);
    ConfigurableCharmDye colorist = new ConfigurableCharmDye(charmGroupChangeListener,
            new CharacterColoringStrategy(presentationProperties.getColor(), view, model));
    setCharmTypes(new CharacterCharmTypes(charmModel));
    setChangeListener(charmGroupChangeListener);
    setView(view);
    SpecialCharmViewBuilder specialViewBuilder = createSpecialCharmViewBuilder(resources, charmConfiguration);
    SpecialCharmList specialCharmList = new CommonSpecialCharmList(view, specialViewBuilder);
    setSpecialPresenter(new CharacterSpecialCharmPresenter(charmGroupChangeListener, charmModel, specialCharmList));
    setCharmDye(colorist);
    setAlienCharmPresenter(new CharacterAlienCharmPresenter(model));
    setInteractionPresenter(
            new LearnInteractionPresenter(model, view, new DefaultFunctionalNodeProperties(), colorist));
    setCharmGroups(new CharacterGroupCollection(model));
  }

  private void addTreeView(Resources resources, CharmView view) {
    CharacterSpecialCharmSet specialCharmSet = new CharacterSpecialCharmSet(model);
    MagicDescriptionProvider magicDescriptionProvider = model.getMagicDescriptionProvider();
    addTreeView(resources, view, specialCharmSet, magicDescriptionProvider, charmIdMap);
  }

  private SpecialCharmViewBuilder createSpecialCharmViewBuilder(Resources resources, CharmsModel charmConfiguration) {
    return new SwingSpecialCharmViewBuilder(resources, charmConfiguration);
  }

  @Override
  protected GroupCharmTree getCharmTree(Identifier cascadeType) {
    return new CharacterGroupCharmTree(model, cascadeType);
  }

  @SuppressWarnings("UnusedDeclaration")
  public void addShowDetailListener(ShowMagicDetailListener listener) {
    view.addCharmInteractionListener(new ShowDetailInteractionListener(listener));
  }
}
