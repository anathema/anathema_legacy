package net.sf.anathema.charmtree.presenter;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.charmtree.filters.CharmFilterSettingsPage;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCascadePresenter implements ICascadeSelectionPresenter {

  private final IResources resources;
  protected CharmFilterSet filterSet = new CharmFilterSet();
  private ICharmGroupChangeListener changeListener;
  private IIdentificate currentType;
  private ICascadeSelectionView view;
  private CharmDye dye;
  private CharmTypes charmTypes;
  protected CharmGroupCollection charmGroups;
  private SpecialCharmViewPresenter specialCharmPresenter = new NullSpecialCharmPresenter();
  private AlienCharmPresenter alienPresenter = new NullAlienCharmPresenter();
  private CharmInteractionPresenter interactionPresenter = new NullInteractionPresenter();

  public AbstractCascadePresenter(IResources resources) {
    this.resources = resources;
  }

  public void initPresentation() {
    createCharmTypeSelector();
    listenForCascadeLoading();
    initCharmTypeSelectionListening();
    specialCharmPresenter.initPresentation();
    resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();
    createCharmGroupSelector();
    initFilters();
    alienPresenter.initPresentation();
    interactionPresenter.initPresentation();
    view.initGui();
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

  private void listenForCascadeLoading() {
    view.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        dye.setCharmVisuals();
      }
    });
    view.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        specialCharmPresenter.showSpecialViews();
      }
    });
  }

  private void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea() {
    getCharmComponent().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        specialCharmPresenter.resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();
      }
    });
  }

  private JComponent getCharmComponent() {
    return view.getCharmComponent();
  }

  protected IResources getResources() {
    return resources;
  }

  protected void createCharmGroupSelector() {
    ICharmGroup[] allGroups = charmGroups.getCharmGroups();
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("", getResources()); //$NON-NLS-1$
    Dimension preferredSize = GuiUtilities.calculateComboBoxSize(allGroups, renderer);
    view.addCharmGroupSelector(getResources().getString("CardView.CharmConfiguration.AlienCharms.CharmGroup"), renderer, changeListener, preferredSize);
  }

  protected void createCharmTypeSelector() {
    IIdentificate[] types = charmTypes.getCurrentCharmTypes();
    view.addCharmTypeSelector(getResources().getString("CharmTreeView.GUI.CharmType"), types,
                              new IdentificateSelectCellRenderer("", getResources())); //$NON-NLS-1$
  }

  protected void createFilterButton(ICascadeSelectionView selectionView) {
    SmartAction buttonAction = new SmartAction() {
      private static final long serialVersionUID = 1L;

      @Override
      protected void execute(Component parentComponent) {
        CharmFilterSettingsPage page = new CharmFilterSettingsPage(getResources(), filterSet);
        UserDialog userDialog = new UserDialog(parentComponent, page);
        IDialogResult result = userDialog.show();
        resetOrApplyFilters(result);
        reselectTypeAndGroup(result);
      }

      private void reselectTypeAndGroup(IDialogResult result) {
        if (result.isCanceled()) {
          return;
        }
        handleTypeSelectionChange(currentType);
        changeListener.reselect();
      }

      private void resetOrApplyFilters(IDialogResult result) {
        if (result.isCanceled()) {
          filterSet.resetAllFilters();
        } else {
          filterSet.applyAllFilters();
        }
      }
    };
    selectionView.addCharmFilterButton(buttonAction, resources.getString("CharmFilters.Filters"), resources.getString("CharmFilters.Define"));
  }

  private ICharmGroup[] sortCharmGroups(ICharmGroup[] originalGroups) {
    ArrayList<ICharmGroup> filteredGroups = new ArrayList<ICharmGroup>();
    for (ICharmGroup group : originalGroups) {
      boolean acceptGroup = false;
      for (ICharm charm : group.getAllCharms()) {
        boolean acceptCharm = filterSet.acceptsCharm(charm);
        if (acceptCharm) {
          acceptGroup = true;
          break;
        }
      }
      if (acceptGroup) {
        filteredGroups.add(group);
      }
    }
    ICharmGroup[] filteredGroupArray = filteredGroups.toArray(new ICharmGroup[filteredGroups.size()]);
    if (!filteredGroups.isEmpty()) {
      I18nedIdentificateSorter<ICharmGroup> sorter = new I18nedIdentificateSorter<ICharmGroup>();
      return sorter.sortAscending(filteredGroupArray, new ICharmGroup[filteredGroups.size()], resources);
    }
    return filteredGroupArray;
  }

  protected void setSpecialPresenter(SpecialCharmViewPresenter presenter) {
    this.specialCharmPresenter = presenter;
  }

  protected void setView(ICascadeSelectionView view) {
    this.view = view;
  }

  protected void setChangeListener(ICharmGroupChangeListener charmGroupChangeListener) {
    this.changeListener = charmGroupChangeListener;
  }

  protected void setCharmDye(CharmDye dye) {
    this.dye = dye;
  }

  protected void setCharmTypes(CharmTypes types) {
    this.charmTypes = types;
  }

  private void handleTypeSelectionChange(IIdentificate cascadeType) {
    if (cascadeType == null) {
      view.fillCharmGroupBox(new IIdentificate[0]);
      return;
    }
    GroupCharmTree charmTree = getCharmTree(cascadeType);
    if (charmTree == null) {
      view.fillCharmGroupBox(new IIdentificate[0]);
      return;
    }
    ICharmGroup[] allCharmGroups = charmTree.getAllCharmGroups();
    ICharmGroup[] sortedCharmGroups = sortCharmGroups(allCharmGroups);
    view.fillCharmGroupBox(sortedCharmGroups);
    specialCharmPresenter.showSpecialViews();
  }

  protected abstract GroupCharmTree getCharmTree(IIdentificate type);

  private void initFilters() {
    CharmFilterContainer charms = getFilterContainer();
    List<ICharmFilter> charmFilters = charms.getCharmFilters();
    filterSet.init(charmFilters);
    createFilterButton(view);
  }

  protected abstract CharmFilterContainer getFilterContainer();

  protected void setAlienCharmPresenter(AlienCharmPresenter presenter) {
    this.alienPresenter = presenter;
  }

  protected void setInteractionPresenter(CharmInteractionPresenter presenter) {
    this.interactionPresenter = presenter;
  }

  public void setCharmGroups(CharmGroupCollection charmGroups) {
    this.charmGroups = charmGroups;
  }
}