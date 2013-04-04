package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.presenter.magic.CharacterAlienCharmPresenter;
import net.sf.anathema.character.presenter.magic.CharacterCharmDye;
import net.sf.anathema.character.presenter.magic.CharacterCharmGroupChangeListener;
import net.sf.anathema.character.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.presenter.magic.CharacterCharmTreeViewProperties;
import net.sf.anathema.character.presenter.magic.CharacterCharmTypes;
import net.sf.anathema.character.presenter.magic.CharacterGroupCharmTree;
import net.sf.anathema.character.presenter.magic.CharacterGroupCollection;
import net.sf.anathema.character.presenter.magic.CharacterSpecialCharmPresenter;
import net.sf.anathema.character.presenter.magic.CommonSpecialCharmList;
import net.sf.anathema.character.presenter.magic.LearnInteractionPresenter;
import net.sf.anathema.character.presenter.magic.SpecialCharmList;
import net.sf.anathema.character.presenter.magic.SpecialCharmViewBuilder;
import net.sf.anathema.character.presenter.magic.detail.DetailDemandingMagicPresenter;
import net.sf.anathema.character.presenter.magic.detail.ShowMagicDetailListener;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.DefaultNodeProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public class CharacterCharmTreePresenter extends AbstractCascadePresenter implements DetailDemandingMagicPresenter {

  private final ICharmView view;
  private final CharacterCharmModel model;

  public CharacterCharmTreePresenter(Resources resources, IMagicViewFactory factory, CharacterCharmModel charmModel,
                                     ITreePresentationProperties presentationProperties, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(resources);
    this.model = charmModel;
    ICharmConfiguration charmConfiguration = model.getCharmConfiguration();
    CharacterCharmTreeViewProperties viewProperties =
            new CharacterCharmTreeViewProperties(resources, charmConfiguration, model.getMagicDescriptionProvider());
    DefaultNodeProperties nodeProperties = new DefaultNodeProperties(resources, viewProperties, viewProperties);
    this.view = factory.createCharmSelectionView(viewProperties, nodeProperties);
    CharacterCharmGroupChangeListener charmGroupChangeListener =
            new CharacterCharmGroupChangeListener(charmConfiguration, filterSet, view.getCharmTreeRenderer(), displayPropertiesMap);
    CharacterCharmDye dye = new CharacterCharmDye(model, charmGroupChangeListener, presentationProperties.getColor(), view);
    setCharmTypes(new CharacterCharmTypes(charmModel));
    setChangeListener(charmGroupChangeListener);
    setView(view);
    SpecialCharmViewBuilder specialViewBuilder = createSpecialCharmViewBuilder(resources, charmConfiguration);
    SpecialCharmList specialCharmList = new CommonSpecialCharmList(view, specialViewBuilder);
    setSpecialPresenter(new CharacterSpecialCharmPresenter(charmGroupChangeListener, charmModel, specialCharmList));
    setCharmDye(dye);
    setAlienCharmPresenter(new CharacterAlienCharmPresenter(model, view));
    setInteractionPresenter(new LearnInteractionPresenter(model, view, viewProperties, dye));
    setCharmGroups(new CharacterGroupCollection(model));
  }

  private SpecialCharmViewBuilder createSpecialCharmViewBuilder(Resources resources, ICharmConfiguration charmConfiguration) {
    return new SwingSpecialCharmViewBuilder(resources, charmConfiguration);
  }

  @Override
  public ICharmView getView() {
    return view;
  }

  @Override
  protected CharmFilterContainer getFilterContainer() {
    return model.getCharmConfiguration();
  }

  @Override
  protected GroupCharmTree getCharmTree(Identified cascadeType) {
    return new CharacterGroupCharmTree(model, cascadeType);
  }

  @Override
  public void addShowDetailListener(ShowMagicDetailListener listener) {
    view.addCharmInteractionListener(new ShowDetailInteractionListener(listener));
  }
}
