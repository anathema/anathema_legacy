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
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
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
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.batik.intvalue.SVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.batik.intvalue.SVGSubeffectCharmView;
import net.sf.anathema.charmtree.presenter.AbstractCascadeSelectionPresenter;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionListener;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.charmtree.presenter.view.IExaltTypeChangedListener;
import net.sf.anathema.charmtree.presenter.view.ISVGSpecialCharmView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class CharacterCharmSelectionPresenter extends AbstractCascadeSelectionPresenter implements
    ICharacterCharmSelectionPresenter,
    IContentPresenter {

  private final ICharmTreeViewProperties viewProperties;
  private CharacterCharmGroupChangeListener charmSelectionChangeListener;
  private final Color characterColor;
  private final List<ISVGSpecialCharmView> specialCharmViews = new ArrayList<ISVGSpecialCharmView>();
  private final ICharacterStatistics statistics;
  private final ICharmProvider provider;
  private ICharmSelectionView view;
  private final IMagicViewFactory viewFactory;

  public CharacterCharmSelectionPresenter(
      ICharacterStatistics statistics,
      IResources resources,
      ITemplateRegistry templateRegistry,
      ICharmProvider provider,
      IMagicViewFactory factory) {
    super(resources, templateRegistry);
    this.provider = provider;
    this.viewFactory = factory;
    IPresentationProperties presentationProperties = statistics.getCharacterTemplate().getPresentationProperties();
    this.viewProperties = new CharacterCharmTreeViewProperties(
        resources,
        statistics.getCharms(),
        presentationProperties.getCharmPresentationProperties().getCharmDimension());
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
        view,
        viewProperties,
        this,
        getTemplateRegistry(),
        getCharmConfiguration(),
        statistics.getRules().getEdition());
    initSpecialCharmViews();
    initCharmTypeSelectionListening(charms, view);
    initCasteListening(view);
    ILearningCharmGroup[] allGroups = charms.getAllGroups();
    createCharmGroupSelector(view, charmSelectionChangeListener, allGroups);
    view.addCharmSelectionListener(new ICharmSelectionListener() {
      public void charmSelected(String charmId) {
        if (viewProperties.isRequirementNode(charmId)) {
          return;
        }
        ILearningCharmGroup charmGroup = charmSelectionChangeListener.getSelectedLearnCharmGroup();
        charmGroup.toggleLearned(charms.getCharmById(charmId));
      }
    });
    initCharmLearnListening(charms, view);
    view.getCharmTreeView().getComponent().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        for (ISVGSpecialCharmView charmView : specialCharmViews) {
          ICharm charm = getCharmConfiguration().getCharmById(charmView.getCharmId());
          boolean isVisible = isVisible(charmSelectionChangeListener.getSelectedLearnCharmGroup(), charm);
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
        ILearningCharmGroup charmGroup = charmSelectionChangeListener.getSelectedLearnCharmGroup();
        if (charmGroup == null) {
          return;
        }
        setCharmVisuals(charmGroup, view);
        showSpecialViews(view, charmGroup);
      }
    });
    charms.addLearnableListener(new IChangeListener() {
      public void changeOccured() {
        setCharmVisuals(charmSelectionChangeListener.getSelectedLearnCharmGroup(), view);
      }
    });
    view.initGui();
  }

  public IViewContent getTabContent() {
    String header = getResources().getString("CardView.CharmConfiguration.CharmSelection.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(header), view);
  }

  private void initCasteListening(final ICharmSelectionView selectionView) {
    final ITypedDescription<ICasteType< ? extends ICasteTypeVisitor>> caste = statistics.getCharacterConcept()
        .getCaste();
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
        selectionView.fillCharmTypeBox(currentCharmTypes);
        handleTypeSelectionChange(charmConfiguration, selectionView, null);
      }
    });
  }

  private IIdentificate[] getCurrentCharmTypes(boolean alienCharms) {
    List<IIdentificate> types = new ArrayList<IIdentificate>();
    Collections.addAll(types, getCharmConfiguration().getCharacterTypes(alienCharms));
    types.add(MARTIAL_ARTS);
    IIdentificate[] currentCharmTypes = types.toArray(new IIdentificate[types.size()]);
    return currentCharmTypes;
  }

  private void initCharmTypeSelectionListening(final ICharmConfiguration charms, final ICharmSelectionView selectionView) {
    selectionView.addCharmTypeSelectionListener(new IExaltTypeChangedListener() {
      public void valueChanged(Object cascadeType) {
        handleTypeSelectionChange(charms, selectionView, cascadeType);
      }
    });
  }

  private void handleTypeSelectionChange(
      final ICharmConfiguration charms,
      final ICharmSelectionView selectionView,
      Object cascadeType) {
    ICharmGroup[] allCharmGroups;
    if (cascadeType instanceof CharacterType) {
      CharacterType characterType = (CharacterType) cascadeType;
      allCharmGroups = charms.getNonMartialArtsGroups(characterType);
    }
    else {
      allCharmGroups = charms.getMartialArtsGroups();
    }
    selectionView.fillCharmGroupBox(sortCharmGroups(allCharmGroups));
    showSpecialViews(selectionView, null);
  }

  private boolean isVisible(ILearningCharmGroup group, ICharm charm) {
    if (group == null) {
      return false;
    }
    boolean isOfGroupType = charm.getCharacterType() == group.getCharacterType();
    return isOfGroupType && charm.getGroupId().equals(group.getId());
  }

  private void initCharmLearnListening(ICharmConfiguration charmConfiguration, final ICharmSelectionView selectionView) {
    ICharmLearnListener charmLearnListener = createCharmLearnListener(selectionView);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void setCharmVisuals(ICharm charm, ICharmSelectionView selectionView) {
    ICharmConfiguration charmConfiguration = getCharmConfiguration();
    ILearningCharmGroup selectedGroup = charmSelectionChangeListener.getSelectedLearnCharmGroup();
    if (selectedGroup == null || !charm.getGroupId().equals(selectedGroup.getId())) {
      return;
    }
    Color fillColor = charmConfiguration.isLearned(charm) ? characterColor : Color.WHITE;
    int opacity = charmConfiguration.isLearnable(charm) ? 255 : 70;
    selectionView.setCharmVisuals(charm.getId(), fillColor, opacity);
  }

  public void setCharmVisuals(ILearningCharmGroup group, ICharmSelectionView selectionView) {
    if (group != null) {
      for (ICharm charm : group.getAllCharms()) {
        setCharmVisuals(charm, selectionView);
      }
    }
  }

  private ICharmLearnListener createCharmLearnListener(final ICharmSelectionView selectionView) {
    ICharmLearnListener charmLearnListener = new ICharmLearnListener() {
      public void charmLearned(ICharm charm) {
        setCharmVisuals(charm, selectionView);
      }

      public void charmForgotten(ICharm charm) {
        setCharmVisuals(charm, selectionView);
      }

      public void charmNotLearnable(ICharm charm) {
        Toolkit.getDefaultToolkit().beep();
      }

      public void charmNotUnlearnable(ICharm charm) {
        Toolkit.getDefaultToolkit().beep();
      }

      public void recalculateRequested() {
        // Nothing to do
      }
    };
    return charmLearnListener;
  }

  private void showSpecialViews(ICharmSelectionView selectionView, ILearningCharmGroup group) {
    for (ISVGSpecialCharmView charmView : specialCharmViews) {
      ICharm charm = getCharmConfiguration().getCharmById(charmView.getCharmId());
      boolean isVisible = isVisible(group, charm);
      selectionView.setSpecialCharmViewVisible(charmView, isVisible);
    }
  }

  private void initSpecialCharmViews() {
    ISpecialCharm[] specialCharms = provider.getAllSpecialCharms(statistics.getRules().getEdition());
    for (ISpecialCharm charm : specialCharms) {
      addSpecialCharmControl(charm);
    }
  }

  private void addSpecialCharmControl(ISpecialCharm charm) {
    charm.accept(new ISpecialCharmVisitor() {
      public void visitMultiLearnableCharm(IMultiLearnableCharm visitedCharm) {
        SVGMultiLearnableCharmView multiLearnableCharmView = viewFactory.createMultiLearnableCharmView(
            visitedCharm,
            getCharmWidth(),
            characterColor);
        IMultiLearnableCharmConfiguration model = (IMultiLearnableCharmConfiguration) getCharmConfiguration().getSpecialCharmConfiguration(
            visitedCharm.getCharmId());
        new MultiLearnableCharmPresenter(getResources(), multiLearnableCharmView, model).initPresentation();
        specialCharmViews.add(multiLearnableCharmView);
      }

      public void visitOxBodyTechnique(IOxBodyTechniqueCharm visited) {
        SVGMultiLearnableCharmView oxBodyTechniqueView = viewFactory.createMultiLearnableCharmView(
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

      public void visitPainToleranceCharm(IPainToleranceCharm visited) {
        // Nothing to do
      }

      public void visitSubeffectCharm(ISubeffectCharm visited) {
        createMultipleEffectCharmView(visited, "CharmTreeView.SubeffectCharm.ButtonLabel"); //$NON-NLS-1$
      }

      public void visitMultipleEffectCharm(IMultipleEffectCharm visited) {
        createMultipleEffectCharmView(visited, visited.getCharmId() + ".ControlButton"); //$NON-NLS-1$
      }
    });
  }

  private void createMultipleEffectCharmView(IMultipleEffectCharm visited, String labelKey) {
    SVGSubeffectCharmView subeffectView = viewFactory.createSubeffectCharmView(visited, getCharmWidth(), characterColor);
    ICharm originalCharm = statistics.getCharms().getCharmById(visited.getCharmId());
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
        .getCharmDimension()
        .getWidth();
  }

  private ICharmConfiguration getCharmConfiguration() {
    return statistics.getCharms();
  }
}