package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class AnathemaSaveAllAction extends SmartAction {
  private IItem currentItem;
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      setSaveAllEnabled();
    }
  };

  private class SaveAllEnabledListener implements IItemManagementModelListener {
    @Override
    public void itemAdded(IItem item) {
      setSaveAllEnabled();
    }

    @Override
    public void itemRemoved(IItem item) {
      setSaveAllEnabled();
    }

    @Override
    public void itemSelected(IItem item) {
      if (currentItem != null) {
        currentItem.removeDirtyListener(changeListener);
      }
      AnathemaSaveAllAction.this.currentItem = item;
      if (item != null && item.getItemType().supportsRepository()) {
        item.addDirtyListener(changeListener);
        setSaveAllEnabled();
      }
    }
  }

  private void setSaveAllEnabled() {
    boolean enable = false;
    for (IItem item : model.getItemManagement().getAllItems()) {
      if (item.getItemType().supportsRepository()) {
        enable |= item.isDirty();
      }
    }
    setEnabled(enable);
  }

  private IApplicationModel model;
  private IResources resources;

  public static Action createToolAction(IApplicationModel model, IResources resources) {
    SmartAction action = new AnathemaSaveAllAction(model, resources);
    action.setToolTipText(resources.getString("AnathemaPersistence.SaveAllAction.Tooltip")); //$NON-NLS-1$
    action.setIcon(new PlatformUI().getSaveAllTaskBarIcon());
    return action;
  }

  private AnathemaSaveAllAction(IApplicationModel model, IResources resources) {
    this.model = model;
    SaveAllEnabledListener listener = new SaveAllEnabledListener();
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK | Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    model.getItemManagement().addListener(listener);
    listener.itemAdded(null);
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    for (IItem item : model.getItemManagement().getAllItems()) {
      if (item.getItemType().supportsRepository() && item.isDirty()) {
        parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
          IRepositoryWriteAccess writeAccess = model.getRepository().createWriteAccess(item);
          IRepositoryItemPersister persister = model.getPersisterRegistry().get(item.getItemType());
          persister.save(writeAccess, item);
          item.setClean();
        } catch (IOException | RepositoryException e) {
          MessageUtilities.indicateMessage(getClass(), parentComponent,
                  new Message(resources.getString("AnathemaPersistence.SaveAction.Message.Error"), e)); //$NON-NLS-1$
        } finally {
          parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      }
    }
  }
}