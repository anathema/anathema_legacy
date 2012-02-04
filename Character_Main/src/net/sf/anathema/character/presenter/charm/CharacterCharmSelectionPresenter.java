package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
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

public class CharacterCharmSelectionPresenter extends AbstractCascadeSelectionPresenter implements IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private final ICharacterStatistics statistics;
  private final ICharmSelectionView view;
  private final SpecialCharmViewPresenter specialCharmViewPresenter;
  private CharacterCharmGroupChangeListener charmSelectionChangeListener;

  public CharacterCharmSelectionPresenter(
          ICharacterStatistics statistics,
          IResources resources,
          ITemplateRegistry templateRegistry,
          IMagicViewFactory factory) {
    super(resources, templateRegistry);
    this.statistics = statistics;
    this.viewProperties = new CharacterCharmTreeViewProperties(resources, getCharmConfiguration());
    this.view = factory.createCharmSelectionView(viewProperties);
    this.charmSelectionChangeListener = new CharacterCharmGroupChangeListener(
            getTemplateRegistry(),
            getCharmConfiguration(),
            filterSet,
            statistics.getRules().getEdition(), view.getCharmTreeRenderer());
    this.specialCharmViewPresenter = new SpecialCharmViewPresenter(statistics, view, resources, charmSelectionChangeListener);
  }

  public void initPresentation() {
    final ICharmConfiguration charms = getCharmConfiguration();
    boolean alienCharms = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(
            statistics.getCharacterConcept().getCaste().getType());
    createCharmTypeSelector(getCurrentCharmTypes(alienCharms), view, "CharmTreeView.GUI.CharmType"); //$NON-NLS-1$
    initFilters(charms);
    specialCharmViewPresenter.initPresentation();
    initCharmTypeSelectionListening();
    initCasteListening();
    createCharmGroupSelector(view, charmSelectionChangeListener, charms.getAllGroups());
    createFilterButton(view);
    view.addCharmSelectionListener(new INodeSelectionListener() {
      public void nodeSelected(String charmId) {
        if (viewProperties.isRequirementNode(charmId)) {
          return;
        }
        ILearningCharmGroup charmGroup = getCharmConfiguration().getGroup(getCharmConfiguration().getCharmById(charmId));
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
        specialCharmViewPresenter.showSpecialViews();
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
      public void mouseExited(MouseEvent e) {
        specialCharmViewPresenter.resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();
      }
    });
  }

  private void reloadCharmGraphAfterFiltering() {
    getCharmComponent().addHierarchyListener(new HierarchyListener() {

      @Override
      public void hierarchyChanged(HierarchyEvent event) {
        if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && getCharmComponent().isShowing()) {
          refresh();
        }
      }
    });
  }

  private void initFilters(ICharmConfiguration charms) {
    if (charms.getCharmFilters() == null) {
      filterSet.add(new ObtainableCharmFilter(getCharmConfiguration()));
      filterSet.add(new SourceBookCharmFilter(statistics.getRules().getEdition(),
              getCharmConfiguration()));
      getCharmConfiguration().setCharmFilters(filterSet);
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
      public void valueChanged(IIdentificate cascadeType) {
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
    specialCharmViewPresenter.showSpecialViews();
  }

  private void initCharmLearnListening(ICharmConfiguration charmConfiguration) {
    ICharmLearnListener charmLearnListener = createCharmLearnListener(view);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void setCharmVisuals(ICharm charm, ICharmSelectionView selectionView) {
    ICharmConfiguration charmConfiguration = getCharmConfiguration();
    ICharmGroup selectedGroup = charmSelectionChangeListener.getCurrentGroup();
    if (selectedGroup == null || !charm.getGroupId().equals(selectedGroup.getId())) {
      return;
    }
    Color fillColor = charmConfiguration.isLearned(charm) ? getCharacterColor() : Color.WHITE;
    int opacity = charmConfiguration.isLearnable(charm) ? 255 : 70;
    selectionView.setCharmVisuals(charm.getId(), fillColor, opacity);
  }

  private void setCharmVisuals() {
    ICharmGroup group = charmSelectionChangeListener.getCurrentGroup();
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

  private ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }

  private Color getCharacterColor() {
    return statistics.getCharacterTemplate().getPresentationProperties().getColor();
  }
}