package net.sf.anathema.campaign.perspective;

import net.sf.anathema.campaign.item.ClosedFileCollector;
import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.campaign.item.PlotItemManagementAdapter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.Action;

public class LoadActionEnabler {

  private final Action action;
  private final IRepository repository;
  private final IItemType[] types;
  private final PlotItemManagement model;

  public LoadActionEnabler(IRepository repository, PlotItemManagement model, Action action, IItemType... types) {
    this.repository = repository;
    this.model = model;
    this.action = action;
    this.types = types;
  }

  private void adjustEnabled() {
    ClosedFileCollector collector = new ClosedFileCollector(model, repository.getPrintNameFileAccess());
    action.setEnabled(collector.containsClosed(types));
  }

  public void init() {
    adjustEnabled();
    repository.addRefreshListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        adjustEnabled();
      }
    });
    model.addListener(new PlotItemManagementAdapter() {
      @Override
      public void itemRemoved(IItem item) {
        adjustEnabled();
      }

      @Override
      public void itemAdded(IItem item) {
        adjustEnabled();
      }
    });
  }
}