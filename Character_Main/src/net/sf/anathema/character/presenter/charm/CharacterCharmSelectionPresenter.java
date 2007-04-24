package net.sf.anathema.character.presenter.charm;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ToolTipManager;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
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
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGCategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGToggleButtonSpecialNodeView;

public class CharacterCharmSelectionPresenter extends AbstractCascadeSelectionPresenter implements IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private CharacterCharmGroupChangeListener charmSelectionChangeListener;
  private final Color characterColor;
  private final List<ISVGSpecialNodeView> specialCharmViews = new ArrayList<ISVGSpecialNodeView>();
  private final ICharacterStatistics statistics;
  private final ICharmSelectionView view;
  private final IMagicViewFactory viewFactory;

  public CharacterCharmSelectionPresenter(
      final ICharacterStatistics statistics,
      final IResources resources,
      final ITemplateRegistry templateRegistry,
      final IMagicViewFactory factory) {
    super(resources, templateRegistry);
    this.viewFactory = factory;
    IPresentationProperties presentationProperties = statistics.getCharacterTemplate().getPresentationProperties();
    this.viewProperties = new CharacterCharmTreeViewProperties(resources, statistics.getCharms());
    this.characterColor = presentationProperties.getColor();
    this.statistics = statistics;
    this.view = factory.createCharmSelectionView(viewProperties);
  }

  public void initPresentation() {
    final ICharmConfiguration charms = getCharmConfiguration();
    boolean alienCharms = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(
        statistics.getCharacterConcept().getCaste().getType());
    createCharmTypeSelector(getCurrentCharmTypes(alienCharms), view, "CharmTreeView.GUI.CharmType"); //$NON-NLS-1$
    this.charmSelectionChangeListener = new CharacterCharmGroupChangeListener(
        view.getCharmTreeView(),
        getTemplateRegistry(),
        getCharmConfiguration(),
        statistics.getRules().getEdition());
    initSpecialCharmViews();
    initCharmTypeSelectionListening(charms);
    initCasteListening();
    createCharmGroupSelector(view, charmSelectionChangeListener, charms.getAllGroups());
    view.addCharmSelectionListener(new INodeSelectionListener() {
      public void nodeSelected(final String charmId) {
        if (viewProperties.isRequirementNode(charmId)) {
          return;
        }
        ILearningCharmGroup charmGroup = charmSelectionChangeListener.getCurrentGroup();
        charmGroup.toggleLearned(charms.getCharmById(charmId));
      }
    });
    initCharmLearnListening(charms);
    view.getCharmTreeView().getComponent().addMouseListener(new MouseAdapter() {
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
    view.addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        setCharmVisuals();
        showSpecialViews();
      }
    });
    charms.addLearnableListener(new IChangeListener() {
      public void changeOccured() {
        setCharmVisuals();
      }
    });
    view.initGui();
  }

  public IViewContent getTabContent() {
    String header = getResources().getString("CardView.CharmConfiguration.CharmSelection.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(header), view);
  }

  private void initCasteListening() {
    final ITypedDescription<ICasteType> caste = statistics.getCharacterConcept().getCaste();
    caste.addChangeListener(new IChangeListener() {
      public void changeOccured() {
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

  private IIdentificate[] getCurrentCharmTypes(final boolean alienCharms) {
    List<IIdentificate> types = new ArrayList<IIdentificate>();
    Collections.addAll(types, getCharmConfiguration().getCharacterTypes(alienCharms));
    types.add(MartialArtsUtilities.MARTIAL_ARTS);
    return types.toArray(new IIdentificate[types.size()]);
  }

  private void initCharmTypeSelectionListening(final ICharmConfiguration charms) {
    view.addCharmTypeSelectionListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(final IIdentificate cascadeType) {
        handleTypeSelectionChange(charms, cascadeType);
      }
    });
  }

  private void handleTypeSelectionChange(final ICharmConfiguration charms, final IIdentificate cascadeType) {
    ICharmGroup[] allCharmGroups = new ICharmGroup[0];
    if (cascadeType != null) {
      allCharmGroups = sortCharmGroups(charms.getCharmGroups(cascadeType));
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
    Color fillColor = charmConfiguration.isLearned(charm) ? characterColor : Color.WHITE;
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
    ICharmLearnListener charmLearnListener = new CharmLearnAdapter() {
      @Override
      public void charmLearned(ICharm charm) {
        setCharmVisuals(charm, selectionView);
      }

      @Override
      public void charmForgotten(ICharm charm) {
        setCharmVisuals(charm, selectionView);
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
    return charmLearnListener;
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

  private void addSpecialCharmControl(final ISpecialCharm charm) {
    charm.accept(new ISpecialCharmVisitor() {
      public void visitMultiLearnableCharm(final IMultiLearnableCharm visitedCharm) {
        SVGCategorizedSpecialNodeView multiLearnableCharmView = viewFactory.createMultiLearnableCharmView(
            visitedCharm,
            getCharmWidth(),
            characterColor);
        IMultiLearnableCharmConfiguration model = (IMultiLearnableCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visitedCharm.getCharmId());
        new MultiLearnableCharmPresenter(getResources(), multiLearnableCharmView, model).initPresentation();
        specialCharmViews.add(multiLearnableCharmView);
      }

      public void visitOxBodyTechnique(final IOxBodyTechniqueCharm visited) {
        SVGCategorizedSpecialNodeView oxBodyTechniqueView = viewFactory.createMultiLearnableCharmView(
            visited,
            getCharmWidth(),
            characterColor);
        ICharm originalCharm = statistics.getCharms().getCharmById(visited.getCharmId());
        IOxBodyTechniqueConfiguration model = (IOxBodyTechniqueConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visited.getCharmId());
        new OxBodyTechniquePresenter(getResources(), oxBodyTechniqueView, model).initPresentation();
        if ((originalCharm.hasChildren() || originalCharm.isTreeRoot()) && model.getCategories().length > 1) {
          specialCharmViews.add(viewFactory.createViewControlButton(
              oxBodyTechniqueView,
              getCharmWidth(),
              getResources().getString("CharmTreeView.Ox-Body.HealthLevels"))); //$NON-NLS-1$
        }
        else {
          specialCharmViews.add(oxBodyTechniqueView);
        }
      }

      public void visitPainToleranceCharm(final IPainToleranceCharm visited) {
        // Nothing to do
      }

      public void visitSubeffectCharm(final ISubeffectCharm visited) {
        createMultipleEffectCharmView(visited, "CharmTreeView.SubeffectCharm.ButtonLabel"); //$NON-NLS-1$
      }

      public void visitMultipleEffectCharm(final IMultipleEffectCharm visited) {
        createMultipleEffectCharmView(visited, visited.getCharmId() + ".ControlButton"); //$NON-NLS-1$
      }
    });
  }

  private void createMultipleEffectCharmView(final IMultipleEffectCharm visited, final String labelKey) {
    SVGToggleButtonSpecialNodeView subeffectView = viewFactory.createSubeffectCharmView(
        visited,
        getCharmWidth(),
        characterColor);
    ICharm originalCharm = getCharmConfiguration().getCharmById(visited.getCharmId());
    IMultipleEffectCharmConfiguration model = (IMultipleEffectCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
        visited.getCharmId());
    new MultipleEffectCharmPresenter(getResources(), subeffectView, model).initPresentation();
    if ((originalCharm.hasChildren() || originalCharm.isTreeRoot()) && model.getEffects().length > 1) {
      specialCharmViews.add(viewFactory.createViewControlButton(
          subeffectView,
          getCharmWidth(),
          getResources().getString(labelKey)));
    }
    else {
      specialCharmViews.add(subeffectView);
    }
  }

  private double getCharmWidth() {
    return statistics.getCharacterTemplate()
        .getPresentationProperties()
        .getCharmPresentationProperties()
        .getNodeDimension()
        .getWidth();
  }

  private ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }
}