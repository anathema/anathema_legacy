package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public class CharacterCharmPresenter extends AbstractCascadePresenter implements IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private final ICharmView view;
  private final CharacterCharmModel model;
  private CharacterCharmGroupChangeListener charmGroupChangeListener;
  private CharacterCharmDye dye;

  public CharacterCharmPresenter(IResources resources, IMagicViewFactory factory, CharacterCharmModel charmModel,
                                 ITreePresentationProperties presentationProperties, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(resources, new CharacterCharmTypes(charmModel));
    this.model = charmModel;
    this.viewProperties = new CharacterCharmTreeViewProperties(resources, model.getCharmConfiguration());
    this.view = factory.createCharmSelectionView(viewProperties);
    this.charmGroupChangeListener = new CharacterCharmGroupChangeListener(model.getCharmConfiguration(), filterSet, model.getEdition(),
                                                                          view.getCharmTreeRenderer(), displayPropertiesMap);
    this.dye = new CharacterCharmDye(model, charmGroupChangeListener, presentationProperties.getColor(), view);
    setChangeListener(charmGroupChangeListener);
    setView(view);
    setSpecialPresenter(new CharacterSpecialCharmPresenter(view, resources, charmGroupChangeListener, charmModel, presentationProperties));
    setCharmGroupInformer(charmGroupChangeListener);
    setCharmDye(dye);
    setAlienCharmPresenter(new CharacterAlienCharmPresenter(model, view));
  }

  @Override
  public void initPresentation() {
    super.initPresentation();
    initCharmLearning();
    view.initGui();
  }

  @Override
  protected ICharmGroup[] getCharmGroups() {
    return model.getCharmConfiguration().getAllGroups();
  }

  private void initCharmLearning() {
    ICharmConfiguration charms = model.getCharmConfiguration();
    view.addCharmSelectionListener(new INodeSelectionListener() {
      @Override
      public void nodeSelected(String charmId) {
        if (viewProperties.isRequirementNode(charmId)) {
          return;
        }
        model.toggleLearned(charmId);
      }
    });
    initCharmLearnListening(charms);
    charms.addLearnableListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        setCharmVisuals();
      }
    });
    ensureRefreshAfterAutomaticUnlearn();
  }

  private void ensureRefreshAfterAutomaticUnlearn() {
    view.getCharmComponent().addHierarchyListener(new HierarchyListener() {
      @Override
      public void hierarchyChanged(HierarchyEvent e) {
        charmGroupChangeListener.reselect();
      }
    });
  }

  @Override
  protected CharmFilterContainer getFilterContainer() {
    return model.getCharmConfiguration();
  }

  @Override
  public IViewContent getTabContent() {
    String header = getResources().getString("CardView.CharmConfiguration.CharmSelection.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(header), view);
  }

  @Override
  protected GroupCharmTree getCharmTree(IIdentificate cascadeType) {
    return new CharacterGroupCharmTree(model, cascadeType);
  }

  private void initCharmLearnListening(ICharmConfiguration charmConfiguration) {
    ICharmLearnListener charmLearnListener = new CharmLearnVisualizer(dye);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }
}