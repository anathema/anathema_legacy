package net.sf.anathema.charmtree.presenter;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.charmtree.filters.CharmFilterSettingsPage;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractCascadeSelectionPresenter implements
    ICascadeSelectionPresenter {

  private final ITemplateRegistry registry;
  private final IResources resources;

  protected List<ICharmFilter> filterSet = new ArrayList<ICharmFilter>();
  protected ICharmGroupChangeListener changeListener;
  protected IIdentificate currentType;

  public AbstractCascadeSelectionPresenter(IResources resources,
      ITemplateRegistry registry) {
    this.resources = resources;
    this.registry = registry;
  }

  protected IResources getResources() {
    return resources;
  }

  protected void createCharmGroupSelector(ICascadeSelectionView selectionView,
      ICharmGroupChangeListener charmSelectionChangeListener,
      ICharmGroup[] allGroups) {
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer(
        "", getResources()); //$NON-NLS-1$
    Dimension preferredSize = GuiUtilities.calculateComboBoxSize(allGroups,
        renderer);
    selectionView.addCharmGroupSelector(
        getResources().getString(
            "CardView.CharmConfiguration.AlienCharms.CharmGroup"), //$NON-NLS-1$
        renderer, charmSelectionChangeListener, preferredSize);
    changeListener = charmSelectionChangeListener;
  }

  protected void createCharmTypeSelector(IIdentificate[] types,
      ICascadeSelectionView selectionView, String titleResourceKey) {
    selectionView.addCharmTypeSelector(
        getResources().getString(titleResourceKey), types,
        new IdentificateSelectCellRenderer("", getResources())); //$NON-NLS-1$
  }

  protected void createFilterButton(ICascadeSelectionView selectionView) {
    SmartAction buttonAction = new SmartAction() {
      private static final long serialVersionUID = 1L;

      @Override
      protected void execute(Component parentComponent) {
        CharmFilterSettingsPage page = new CharmFilterSettingsPage(
            getResources(), filterSet);
        UserDialog userDialog = new UserDialog(parentComponent, page);

        boolean dirty = false;
        IDialogResult result = userDialog.show();

        for (ICharmFilter element : filterSet)
          if (element.isDirty())
            if (result.isCanceled())
              element.reset();
            else {
              element.apply();
              dirty = true;
            }

        if (dirty) {
          handleTypeSelectionChange(currentType);
          changeListener.reselect();
        }
      }
    };
    selectionView.addCharmFilterButton(buttonAction,
        resources.getString("CharmFilters.Filters"),
        resources.getString("CharmFilters.Define"));
  }

  protected ICharmGroup[] sortCharmGroups(ICharmGroup[] originalGroups) {
    ArrayList<ICharmGroup> filteredGroups = new ArrayList<ICharmGroup>();
    for (ICharmGroup group : originalGroups) {
      boolean acceptGroup = false;
      for (ICharm charm : group.getAllCharms()) {
        boolean acceptCharm = true;
        for (ICharmFilter filter : filterSet) {
          if (!filter.acceptsCharm(charm, false)) {
            acceptCharm = false;
            break;
          }
        }
        if (acceptCharm) {
          acceptGroup = true;
          break;
        }
      }
      if (acceptGroup) {
        filteredGroups.add(group);
      }
    }
    ICharmGroup[] filteredGroupArray = new ICharmGroup[filteredGroups.size()];
    for (int i = 0; i != filteredGroups.size(); i++)
      filteredGroupArray[i] = filteredGroups.get(i);
    if (filteredGroups.size() > 0)
      return new I18nedIdentificateSorter<ICharmGroup>()
          .sortAscending(filteredGroupArray,
              new ICharmGroup[filteredGroups.size()], resources);
    else
      return filteredGroupArray;
  }

  protected final ITemplateRegistry getTemplateRegistry() {
    return registry;
  }

  public final List<ICharmFilter> getFilterSet() {
    return filterSet;
  }

  abstract protected void handleTypeSelectionChange(
      final IIdentificate cascadeType);
}