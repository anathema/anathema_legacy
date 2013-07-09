package net.sf.anathema.hero.magic.display.tree;

import net.sf.anathema.character.main.magic.display.view.charmtree.CharmView;
import net.sf.anathema.character.main.magic.model.charmtree.GroupCharmTree;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.magic.model.CharacterCharmModel;
import net.sf.anathema.hero.magic.model.charms.CharacterCharmTypes;
import net.sf.anathema.hero.magic.display.special.CharacterSpecialCharmPresenter;
import net.sf.anathema.hero.magic.display.special.CommonSpecialCharmList;
import net.sf.anathema.hero.magic.model.charms.special.SpecialCharmList;
import net.sf.anathema.hero.magic.display.special.SpecialCharmViewBuilder;
import net.sf.anathema.character.main.magicdescription.presenter.ShowMagicDetailListener;
import net.sf.anathema.hero.magic.display.coloring.CharacterColoringStrategy;
import net.sf.anathema.hero.magic.display.coloring.ConfigurableCharmDye;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmDisplayPropertiesMap;
import net.sf.anathema.character.main.magic.display.view.charmtree.DefaultNodeProperties;
import net.sf.anathema.hero.magic.display.special.SwingSpecialCharmViewBuilder;
import net.sf.anathema.hero.magic.model.charms.CharacterGroupCollection;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public class CharacterCharmTreePresenter extends AbstractCascadePresenter {

  private final CharmView view;
  private final CharacterCharmModel model;

  public CharacterCharmTreePresenter(Resources resources, CharmView view, CharacterCharmModel charmModel,
                                     ITreePresentationProperties presentationProperties, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(resources);
    this.model = charmModel;
    CharmsModel charmConfiguration = model.getCharmConfiguration();
    CharacterCharmTreeViewProperties viewProperties =
            new CharacterCharmTreeViewProperties(resources, charmConfiguration, model.getMagicDescriptionProvider());
    DefaultNodeProperties nodeProperties = new DefaultNodeProperties(resources, viewProperties, viewProperties);
    this.view = view;
    view.initGui(viewProperties, nodeProperties);
    CharacterCharmGroupChangeListener charmGroupChangeListener =
            new CharacterCharmGroupChangeListener(charmConfiguration, view.getCharmTreeRenderer(), displayPropertiesMap);
    ConfigurableCharmDye colorist = new ConfigurableCharmDye(charmGroupChangeListener, new CharacterColoringStrategy(presentationProperties.getColor(), view, model));
    setCharmTypes(new CharacterCharmTypes(charmModel));
    setChangeListener(charmGroupChangeListener);
    setView(view);
    SpecialCharmViewBuilder specialViewBuilder = createSpecialCharmViewBuilder(resources, charmConfiguration);
    SpecialCharmList specialCharmList = new CommonSpecialCharmList(view, specialViewBuilder);
    setSpecialPresenter(new CharacterSpecialCharmPresenter(charmGroupChangeListener, charmModel, specialCharmList));
    setCharmDye(colorist);
    setAlienCharmPresenter(new CharacterAlienCharmPresenter(model, view));
    setInteractionPresenter(new LearnInteractionPresenter(model, view, viewProperties, colorist));
    setCharmGroups(new CharacterGroupCollection(model));
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
