package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.charmtree.filters.CharmFilterSettingsPage;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.platform.tree.presenter.view.CascadeLoadedListener;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCascadePresenter implements ICascadeSelectionPresenter {

  private final Resources resources;
  protected CharmFilterSet filterSet = new CharmFilterSet();
  private ICharmGroupChangeListener changeListener;
  private Identified currentType;
  private ICascadeSelectionView view;
  private CharmDye dye;
  private CharmTypes charmTypes;
  protected CharmGroupCollection charmGroups;
  private SpecialCharmViewPresenter specialCharmPresenter = new NullSpecialCharmPresenter();
  private AlienCharmPresenter alienPresenter = new NullAlienCharmPresenter();
  private CharmInteractionPresenter interactionPresenter = new NullInteractionPresenter();

  public AbstractCascadePresenter(Resources resources) {
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
    createHelpText();
    alienPresenter.initPresentation();
    interactionPresenter.initPresentation();
    view.initGui();
  }

  private void initCharmTypeSelectionListening() {
    view.addCharmTypeSelectionListener(new ObjectValueListener<Identified>() {
      @Override
      public void valueChanged(Identified cascadeType) {
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
        specialCharmPresenter.resetTooltipsWhenCursorLeavesCharmArea();
      }
    });
  }

  private JComponent getCharmComponent() {
    return view.getCharmComponent();
  }

  protected Resources getResources() {
    return resources;
  }

  protected void createCharmGroupSelector() {
    ICharmGroup[] allGroups = charmGroups.getCharmGroups();
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer(getResources());
    Dimension preferredSize = net.sf.anathema.lib.gui.swing.GuiUtilities.calculateComboBoxSize(allGroups, renderer);
    view.addCharmGroupSelector(getResources().getString("CardView.CharmConfiguration.AlienCharms.CharmGroup"), renderer, changeListener, preferredSize);
  }

  protected void createCharmTypeSelector() {
    Identified[] types = charmTypes.getCurrentCharmTypes();
    view.addCharmTypeSelector(getResources().getString("CharmTreeView.GUI.CharmType"), types,
            new IdentificateSelectCellRenderer(getResources()));
  }

  protected void createFilterButton(ICascadeSelectionView selectionView) {
    SmartAction buttonAction = new SmartAction(resources.getString("CharmFilters.Define")) {

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
    selectionView.addCharmFilterButton(buttonAction, resources.getString("CharmFilters.Filters"));
  }

  private void createHelpText() {
    view.addCharmCascadeHelp(resources.getString("CharmTreeView.GUI.HelpText"));
  }

  private ICharmGroup[] sortCharmGroups(ICharmGroup[] originalGroups) {
    ArrayList<ICharmGroup> filteredGroups = new ArrayList<>();
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
      I18nedIdentificateSorter<ICharmGroup> sorter = new I18nedIdentificateSorter<>();
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

  private void handleTypeSelectionChange(Identified cascadeType) {
    if (cascadeType == null) {
      view.fillCharmGroupBox(new Identified[0]);
      return;
    }
    GroupCharmTree charmTree = getCharmTree(cascadeType);
    if (charmTree == null) {
      view.fillCharmGroupBox(new Identified[0]);
      return;
    }
    ICharmGroup[] allCharmGroups = charmTree.getAllCharmGroups();
    ICharmGroup[] sortedCharmGroups = sortCharmGroups(allCharmGroups);
    view.fillCharmGroupBox(sortedCharmGroups);
    specialCharmPresenter.showSpecialViews();
  }

  protected abstract GroupCharmTree getCharmTree(Identified type);

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