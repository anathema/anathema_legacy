package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
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

import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterCharmPresenter extends AbstractCascadePresenter implements IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private final ICharmView view;
  private final CharacterCharmModel model;
  private Color characterColor;
  private CharacterCharmGroupChangeListener charmGroupChangeListener;

  public CharacterCharmPresenter(IResources resources, IMagicViewFactory factory, CharacterCharmModel charmModel,
                                 ITreePresentationProperties presentationProperties, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(resources);
    this.model = charmModel;
    this.characterColor = presentationProperties.getColor();
    this.viewProperties = new CharacterCharmTreeViewProperties(resources, model.getCharmConfiguration());
    this.view = factory.createCharmSelectionView(viewProperties);
    this.charmGroupChangeListener = new CharacterCharmGroupChangeListener(model.getCharmConfiguration(), filterSet, model.getEdition(),
                                                                          view.getCharmTreeRenderer(), displayPropertiesMap);
    setChangeListener(charmGroupChangeListener);
    setView(view);
    setSpecialPresenter(new CharacterSpecialCharmPresenter(view, resources, charmGroupChangeListener, charmModel, presentationProperties));
  }

  @Override
  public void initPresentation() {
    super.initPresentation();
    initCasteListening();
    initCharmLearning();
    view.initGui();
  }

  @Override
  protected ILearningCharmGroup[] getCharmGroups() {
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
  protected void initFilters() {
    ICharmConfiguration charms = model.getCharmConfiguration();
    if (charms.getCharmFilters() == null) {
      filterSet.init(new ObtainableCharmFilter(charms), new SourceBookCharmFilter(model.getEdition(), charms), new EssenceLevelCharmFilter());
      filterSet.commitFilters(charms);
    } else {
      List<ICharmFilter> charmFilters = charms.getCharmFilters();
      filterSet.init(charmFilters.toArray(new ICharmFilter[charmFilters.size()]));
    }
    createFilterButton(view);
  }

  @Override
  public IViewContent getTabContent() {
    String header = getResources().getString("CardView.CharmConfiguration.CharmSelection.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(header), view);
  }

  private void initCasteListening() {
    model.addCasteChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        boolean alienCharms = model.isAllowedAlienCharms();
        ICharmConfiguration charmConfiguration = model.getCharmConfiguration();
        if (!alienCharms) {
          charmConfiguration.unlearnAllAlienCharms();
        }
        IIdentificate[] currentCharmTypes = getCurrentCharmTypes();
        view.fillCharmTypeBox(currentCharmTypes);
      }
    });
  }

  @Override
  protected List<IIdentificate> getCurrentCharacterTypes() {
    boolean alienCharms = model.isAllowedAlienCharms();
    List<IIdentificate> types = new ArrayList<IIdentificate>();
    Collections.addAll(types, model.getCharmConfiguration().getCharacterTypes(alienCharms));
    return types;
  }

  protected GroupCharmTree getCharmTree(final IIdentificate cascadeType) {
    return new GroupCharmTree() {
      @Override
      public ICharmGroup[] getAllCharmGroups() {
        return model.getCharmConfiguration().getCharmGroups(cascadeType);
      }
    };
  }

  private void initCharmLearnListening(ICharmConfiguration charmConfiguration) {
    ICharmLearnListener charmLearnListener = createCharmLearnListener(view);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  @Override
  protected void setCharmVisuals() {
    ICharmGroup group = charmGroupChangeListener.getCurrentGroup();
    if (group == null) {
      return;
    }
    for (ICharm charm : group.getAllCharms()) {
      setCharmVisuals(charm, view);
    }
  }

  private void setCharmVisuals(ICharm charm, ICharmView view) {
    ICharmConfiguration charmConfiguration = model.getCharmConfiguration();
    ICharmGroup selectedGroup = charmGroupChangeListener.getCurrentGroup();
    if (selectedGroup == null || !charm.getGroupId().equals(selectedGroup.getId())) {
      return;
    }
    Color fillColor = charmConfiguration.isLearned(charm) ? characterColor : Color.WHITE;
    int opacity = charmConfiguration.isLearnable(charm) ? 255 : 70;
    view.setCharmVisuals(charm.getId(), fillColor, opacity);
  }

  private ICharmLearnListener createCharmLearnListener(final ICharmView view) {
    return new CharmLearnAdapter() {
      @Override
      public void charmLearned(ICharm charm) {
        setCharmVisuals(charm, view);
      }

      @Override
      public void charmForgotten(ICharm charm) {
        setCharmVisuals(charm, view);
      }

      @Override
      public void charmNotLearnable(ICharm charm) {
        Toolkit.getDefaultToolkit().beep();
      }

      @Override
      public void charmNotUnlearnable(ICharm charm) {
        Toolkit.getDefaultToolkit().beep();
      }
    };
  }
}