package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
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
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterCharmPresenter extends AbstractCascadePresenter implements IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private final ICharmView view;
  private final SpecialCharmViewPresenter specialViewPresenter;
  private final CharacterCharmModel model;
  private Color characterColor;
  private CharacterCharmGroupChangeListener charmGroupChangeListener;

  public CharacterCharmPresenter(IResources resources, IMagicViewFactory factory, CharacterCharmModel charmModel,
                                 ITreePresentationProperties presentationProperties,
                                 CharmDisplayPropertiesMap displayPropertiesMap) {
    super(resources);
    this.model = charmModel;
    this.characterColor = presentationProperties.getColor();
    this.viewProperties = new CharacterCharmTreeViewProperties(resources, model.getCharmConfiguration());
    this.view = factory.createCharmSelectionView(viewProperties);
    this.charmGroupChangeListener = new CharacterCharmGroupChangeListener(model.getCharmConfiguration(), filterSet,
            model.getEdition(), view.getCharmTreeRenderer(), displayPropertiesMap);
    this.specialViewPresenter = new SpecialCharmViewPresenter(view, resources, charmGroupChangeListener, charmModel,
            presentationProperties);
  }

  @Override
  public void initPresentation() {
    final ICharmConfiguration charms = model.getCharmConfiguration();
    boolean alienCharms = model.isAllowedAlienCharms();
    createCharmTypeSelector(getCurrentCharmTypes(alienCharms), view, "CharmTreeView.GUI.CharmType"); //$NON-NLS-1$
    initFilters(charms);
    specialViewPresenter.initPresentation();
    initCharmTypeSelectionListening();
    initCasteListening();
    createCharmGroupSelector(view, charmGroupChangeListener, charms.getAllGroups());
    createFilterButton(view);
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
    resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();
    view.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        setCharmVisuals();
      }
    });
    view.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        specialViewPresenter.showSpecialViews();
      }
    });
    charms.addLearnableListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        setCharmVisuals();
      }
    });
    view.initGui();
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

  private void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea() {
    getCharmComponent().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        specialViewPresenter.resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();
      }
    });
  }

  private void initFilters(ICharmConfiguration charms) {
    if (charms.getCharmFilters() == null) {
      filterSet.init(new ObtainableCharmFilter(charms), new SourceBookCharmFilter(model.getEdition(), charms),
              new EssenceLevelCharmFilter());
      filterSet.commitFilters(charms);
    } else {
      List<ICharmFilter> charmFilters = charms.getCharmFilters();
      filterSet.init(charmFilters.toArray(new ICharmFilter[charmFilters.size()]));
    }
  }

  private JComponent getCharmComponent() {
    return view.getCharmComponent();
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
        IIdentificate[] currentCharmTypes = getCurrentCharmTypes(alienCharms);
        view.fillCharmTypeBox(currentCharmTypes);
      }
    });
  }

  private IIdentificate[] getCurrentCharmTypes(boolean alienCharms) {
    List<IIdentificate> types = new ArrayList<IIdentificate>();
    Collections.addAll(types, model.getCharmConfiguration().getCharacterTypes(alienCharms));
    types.add(MartialArtsUtilities.MARTIAL_ARTS);
    return types.toArray(new IIdentificate[types.size()]);
  }

  private void initCharmTypeSelectionListening() {
    view.addCharmTypeSelectionListener(new IObjectValueChangedListener<IIdentificate>() {
      @Override
      public void valueChanged(IIdentificate cascadeType) {
        currentType = cascadeType;
        handleTypeSelectionChange(cascadeType);
      }
    });
  }

  @Override
  protected void handleTypeSelectionChange(IIdentificate cascadeType) {
    ICharmGroup[] allCharmGroups = new ICharmGroup[0];
    if (cascadeType != null) {
      allCharmGroups = sortCharmGroups(model.getCharmConfiguration().getCharmGroups(cascadeType));
    }
    view.fillCharmGroupBox(allCharmGroups);
    specialViewPresenter.showSpecialViews();
  }

  private void initCharmLearnListening(ICharmConfiguration charmConfiguration) {
    ICharmLearnListener charmLearnListener = createCharmLearnListener(view);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void setCharmVisuals() {
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