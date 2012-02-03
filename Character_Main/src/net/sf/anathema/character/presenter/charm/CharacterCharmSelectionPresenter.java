package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.AbstractCascadeSelectionPresenter;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterCharmSelectionPresenter extends AbstractCascadeSelectionPresenter implements IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private CharacterCharmGroupChangeListener charmSelectionChangeListener;
  private final List<ISVGSpecialNodeView> specialCharmViews = new ArrayList<ISVGSpecialNodeView>();
  private final ICharacterStatistics statistics;
  private final ICharmSelectionView view;
  private final IMagicViewFactory viewFactory;

  public CharacterCharmSelectionPresenter(
          ICharacterStatistics statistics,
          IResources resources,
          ITemplateRegistry templateRegistry,
          IMagicViewFactory factory) {
    super(resources, templateRegistry);
    this.viewFactory = factory;
    this.viewProperties = new CharacterCharmTreeViewProperties(resources, statistics.getCharms());
    this.statistics = statistics;
    this.view = factory.createCharmSelectionView(viewProperties);
  }

  public void initPresentation() {
    final ICharmConfiguration charms = getCharmConfiguration();
    boolean alienCharms = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(
            statistics.getCharacterConcept().getCaste().getType());
    createCharmTypeSelector(getCurrentCharmTypes(alienCharms), view, "CharmTreeView.GUI.CharmType"); //$NON-NLS-1$
    initFilters(charms);
    this.charmSelectionChangeListener = new CharacterCharmGroupChangeListener(
            getTemplateRegistry(),
            getCharmConfiguration(),
            filterSet,
            statistics.getRules().getEdition(), view.getCharmTreeRenderer());
    initSpecialCharmViews();
    initCharmTypeSelectionListening();
    initCasteListening();
    createCharmGroupSelector(view, charmSelectionChangeListener, charms.getAllGroups());
    createFilterButton(view);
    view.addCharmSelectionListener(new INodeSelectionListener() {
      public void nodeSelected(final String charmId) {
        if (viewProperties.isRequirementNode(charmId)) {
          return;
        }
        ILearningCharmGroup charmGroup = statistics.getCharms().getGroup(statistics.getCharms().getCharmById(charmId));

        charmGroup.toggleLearned(charms.getCharmById(charmId));
      }
    });
    initCharmLearnListening(charms);
    reloadCharmGraphAfterFiltering();
    resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();
    view.addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        setCharmVisuals();
      }
    });
    view.addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        showSpecialViews();
      }
    });
    charms.addLearnableListener(new IChangeListener() {
      public void changeOccurred() {
        setCharmVisuals();
      }
    });
    view.initGui();
  }

  private void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea() {
    getCharmComponent().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(final MouseEvent e) {
        for (ISVGSpecialNodeView charmView : specialCharmViews) {
          ICharm charm = getCharmConfiguration().getCharmById(charmView.getNodeId());
          boolean isVisible = isVisible(charmSelectionChangeListener.getCurrentGroup(), charm);
          if (isVisible) {
            charmView.reset();
          }
        }
        ToolTipManager.sharedInstance().setEnabled(false);
        ToolTipManager.sharedInstance().setEnabled(true);
      }
    });
  }

  private void reloadCharmGraphAfterFiltering() {
    getCharmComponent().addHierarchyListener(new HierarchyListener() {

      @Override
      public void hierarchyChanged(HierarchyEvent arg0) {
        if ((arg0.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && getCharmComponent().isShowing()) {
          refresh();
        }
      }
    });
  }

  private void initFilters(ICharmConfiguration charms) {
    if (charms.getCharmFilters() == null) {
      filterSet.add(new ObtainableCharmFilter(statistics.getCharms()));
      filterSet.add(new SourceBookCharmFilter(statistics.getRules().getEdition(),
              statistics.getCharms()));
      statistics.getCharms().setCharmFilters(filterSet);
    } else
      filterSet = charms.getCharmFilters();
  }

  private JComponent getCharmComponent() {
    return view.getCharmComponent();
  }

  public IViewContent getTabContent() {
    String header = getResources().getString("CardView.CharmConfiguration.CharmSelection.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(header), view);
  }

  private void initCasteListening() {
    final ITypedDescription<ICasteType> caste = statistics.getCharacterConcept().getCaste();
    caste.addChangeListener(new IChangeListener() {
      public void changeOccurred() {
        boolean alienCharms = statistics.getCharacterTemplate()
                .getMagicTemplate()
                .getCharmTemplate()
                .isAllowedAlienCharms(caste.getType());
        ICharmConfiguration charmConfiguration = getCharmConfiguration();
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
    Collections.addAll(types, getCharmConfiguration().getCharacterTypes(alienCharms));
    types.add(MartialArtsUtilities.MARTIAL_ARTS);
    return types.toArray(new IIdentificate[types.size()]);
  }

  private void refresh() {
    handleTypeSelectionChange(currentType);
    charmSelectionChangeListener.reselect();
  }

  private void initCharmTypeSelectionListening() {
    view.addCharmTypeSelectionListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(final IIdentificate cascadeType) {
        currentType = cascadeType;
        handleTypeSelectionChange(cascadeType);
      }
    });
  }

  protected void handleTypeSelectionChange(IIdentificate cascadeType) {
    ICharmGroup[] allCharmGroups = new ICharmGroup[0];
    if (cascadeType != null) {
      allCharmGroups = sortCharmGroups(getCharmConfiguration()
              .getCharmGroups(cascadeType));
    }
    view.fillCharmGroupBox(allCharmGroups);
    showSpecialViews();
  }

  private boolean isVisible(final ILearningCharmGroup group, final ICharm charm) {
    if (group == null) {
      return false;
    }
    boolean isOfGroupType = charm.getCharacterType() == group.getCharacterType();
    return isOfGroupType && charm.getGroupId().equals(group.getId());
  }

  private void initCharmLearnListening(final ICharmConfiguration charmConfiguration) {
    ICharmLearnListener charmLearnListener = createCharmLearnListener(view);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void setCharmVisuals(final ICharm charm, final ICharmSelectionView selectionView) {
    ICharmConfiguration charmConfiguration = getCharmConfiguration();
    ILearningCharmGroup selectedGroup = charmSelectionChangeListener.getCurrentGroup();
    if (selectedGroup == null || !charm.getGroupId().equals(selectedGroup.getId())) {
      return;
    }
    Color fillColor = charmConfiguration.isLearned(charm) ? getCharacterColor() : Color.WHITE;
    int opacity = charmConfiguration.isLearnable(charm) ? 255 : 70;
    selectionView.setCharmVisuals(charm.getId(), fillColor, opacity);
  }

  private void setCharmVisuals() {
    ILearningCharmGroup group = charmSelectionChangeListener.getCurrentGroup();
    if (group == null) {
      return;
    }
    for (ICharm charm : group.getAllCharms()) {
      setCharmVisuals(charm, view);
    }
  }

  private ICharmLearnListener createCharmLearnListener(final ICharmSelectionView selectionView) {
    return new CharmLearnAdapter() {
      @Override
      public void charmLearned(ICharm charm) {
        setCharmVisuals(charm, selectionView);
        charmSelectionChangeListener.reselect();
      }

      @Override
      public void charmForgotten(ICharm charm) {
        setCharmVisuals(charm, selectionView);
        charmSelectionChangeListener.reselect();
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

  private void showSpecialViews() {
    ILearningCharmGroup group = charmSelectionChangeListener.getCurrentGroup();
    if (group == null) {
      return;
    }
    for (ISVGSpecialNodeView charmView : specialCharmViews) {
      ICharm charm = getCharmConfiguration().getCharmById(charmView.getNodeId());
      boolean isVisible = isVisible(group, charm);
      view.setSpecialCharmViewVisible(charmView, isVisible);
    }
  }

  private void initSpecialCharmViews() {
    for (ISpecialCharm charm : getCharmConfiguration().getSpecialCharms()) {
      addSpecialCharmControl(charm);
    }
  }

  private void addSpecialCharmControl(ISpecialCharm charm) {
    charm.accept(new SpecialCharmViewInitializer(specialCharmViews, getResources(), statistics, viewFactory));
  }

  private ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }

  private Color getCharacterColor() {
    return statistics.getCharacterTemplate().getPresentationProperties().getColor();
  }
}