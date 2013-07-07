package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.charms.GroupCharmTree;
import net.sf.anathema.character.main.presenter.magic.charm.ShowDetailInteractionListener;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.presenter.magic.CharacterCharmGroupChangeListener;
import net.sf.anathema.character.main.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.main.presenter.magic.CharacterCharmTreeViewProperties;
import net.sf.anathema.character.main.presenter.magic.CharacterCharmTypes;
import net.sf.anathema.character.main.presenter.magic.CharacterGroupCharmTree;
import net.sf.anathema.character.main.presenter.magic.CharacterGroupCollection;
import net.sf.anathema.character.main.presenter.magic.CharacterSpecialCharmPresenter;
import net.sf.anathema.hero.charms.display.special.CommonSpecialCharmList;
import net.sf.anathema.character.main.presenter.magic.SpecialCharmList;
import net.sf.anathema.hero.charms.display.special.SpecialCharmViewBuilder;
import net.sf.anathema.character.main.presenter.magic.detail.ShowMagicDetailListener;
import net.sf.anathema.character.main.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.hero.charms.display.coloring.CharacterColoringStrategy;
import net.sf.anathema.character.main.charmtree.presenter.ConfigurableCharmDye;
import net.sf.anathema.character.main.charmtree.view.CharmDisplayPropertiesMap;
import net.sf.anathema.character.main.charmtree.view.DefaultNodeProperties;
import net.sf.anathema.character.main.charmtree.view.ICharmView;
import net.sf.anathema.hero.charms.display.special.SwingSpecialCharmViewBuilder;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public class CharacterCharmTreePresenter extends AbstractCascadePresenter {

  private final ICharmView view;
  private final CharacterCharmModel model;

  public CharacterCharmTreePresenter(Resources resources, ICharmView view, CharacterCharmModel charmModel,
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
