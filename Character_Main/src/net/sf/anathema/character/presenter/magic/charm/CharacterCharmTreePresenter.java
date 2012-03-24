package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.presenter.magic.CharacterAlienCharmPresenter;
import net.sf.anathema.character.presenter.magic.CharacterCharmDye;
import net.sf.anathema.character.presenter.magic.CharacterCharmGroupChangeListener;
import net.sf.anathema.character.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.presenter.magic.CharacterCharmTreeViewProperties;
import net.sf.anathema.character.presenter.magic.CharacterCharmTypes;
import net.sf.anathema.character.presenter.magic.CharacterGroupCharmTree;
import net.sf.anathema.character.presenter.magic.CharacterGroupCollection;
import net.sf.anathema.character.presenter.magic.CharacterSpecialCharmPresenter;
import net.sf.anathema.character.presenter.magic.LearnInteractionPresenter;
import net.sf.anathema.character.presenter.magic.detail.DetailDemandingMagicPresenter;
import net.sf.anathema.character.presenter.magic.detail.ShowMagicDetailListener;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public class CharacterCharmTreePresenter extends AbstractCascadePresenter implements DetailDemandingMagicPresenter {

  private final ICharmView view;
  private final CharacterCharmModel model;

  public CharacterCharmTreePresenter(IResources resources, IMagicViewFactory factory, CharacterCharmModel charmModel,
                                     ITreePresentationProperties presentationProperties, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(resources);
    this.model = charmModel;
    ICharmTreeViewProperties viewProperties = new CharacterCharmTreeViewProperties(resources, model.getCharmConfiguration(), model.getMagicDescriptionProvider());
    this.view = factory.createCharmSelectionView(viewProperties);
    CharacterCharmGroupChangeListener charmGroupChangeListener = new CharacterCharmGroupChangeListener(model.getCharmConfiguration(), filterSet,
            view.getCharmTreeRenderer(),
            displayPropertiesMap);
    CharacterCharmDye dye = new CharacterCharmDye(model, charmGroupChangeListener, presentationProperties.getColor(), view);
    setCharmTypes(new CharacterCharmTypes(charmModel));
    setChangeListener(charmGroupChangeListener);
    setView(view);
    setSpecialPresenter(new CharacterSpecialCharmPresenter(view, resources, charmGroupChangeListener, charmModel, presentationProperties));
    setCharmDye(dye);
    setAlienCharmPresenter(new CharacterAlienCharmPresenter(model, view));
    setInteractionPresenter(new LearnInteractionPresenter(model, view, charmGroupChangeListener, viewProperties, dye));
    setCharmGroups(new CharacterGroupCollection(model));
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
  protected GroupCharmTree getCharmTree(IIdentificate cascadeType) {
    return new CharacterGroupCharmTree(model, cascadeType);
  }

  @Override
  public void addShowDetailListener(ShowMagicDetailListener listener) {
    view.addCharmInteractionListener(new ShowDetailInteractionListener(listener));
  }
}
