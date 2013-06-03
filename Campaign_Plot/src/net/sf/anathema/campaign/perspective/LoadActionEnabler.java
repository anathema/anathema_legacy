package net.sf.anathema.campaign.perspective;

import net.sf.anathema.campaign.item.ClosedFileCollector;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.Action;

public class LoadActionEnabler {

  private final Action action;
  private final IRepository repository;
  private final IItemType[] types;
  private final IItemManagementModel model;

  public LoadActionEnabler(IRepository repository, IItemManagementModel model, Action action, IItemType... types) {
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
    model.addListener(new ItemManagementModelAdapter() {
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