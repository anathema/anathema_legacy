package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.IOUtils;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;

public class AnathemaSaveAction extends SmartAction {

  private IItem currentItem;
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      AnathemaSaveAction.this.setEnabled(currentItem.isDirty());
    }
  };

  private class SaveEnabledListener extends ItemManagementModelAdapter {

    private final Action action;

    public SaveEnabledListener(Action action) {
      this.action = action;
    }

    @Override
    public void itemSelected(IItem item) {
      if (currentItem != null) {
        currentItem.removeDirtyListener(changeListener);
      }
      AnathemaSaveAction.this.currentItem = item;
      if (item == null) {
        action.setEnabled(false);
        return;
      }
      if (!item.getItemType().supportsRepository()) {
        action.setEnabled(false);
        return;
      }
      item.addDirtyListener(changeListener);
      action.setEnabled(item.isDirty());
    }
  }

  private IApplicationModel model;
  private IResources resources;

  public static Action createToolAction(IApplicationModel model, IResources resources) {
    SmartAction action = new AnathemaSaveAction(model, resources);
    action.setToolTipText(resources.getString("AnathemaPersistence.SaveAction.Tooltip")); //$NON-NLS-1$
    action.setIcon(new PlatformUI().getSaveTaskBarIcon());
    return action;
  }

  private AnathemaSaveAction(IApplicationModel model, IResources resources) {
    SaveEnabledListener listener = new SaveEnabledListener(this);
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    model.getItemManagement().addListener(listener);
    listener.itemSelected(model.getItemManagement().getSelectedItem());
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    OutputStream stream = null;
    try {
      IItem selectedItem = model.getItemManagement().getSelectedItem();
      IRepositoryWriteAccess writeAccess = model.getRepository().createWriteAccess(selectedItem);
      IRepositoryItemPersister persister = model.getPersisterRegistry().get(selectedItem.getItemType());
      persister.save(writeAccess, selectedItem);
      selectedItem.setClean();
    } catch (IOException | RepositoryException e) {
      MessageDialogFactory
              .showMessageDialog(parentComponent, new Message(resources.getString("AnathemaPersistence.SaveAction.Message.Error"), e)); //$NON-NLS-1$
      Logger.getLogger(getClass()).error(e);
    } finally {
      IOUtils.closeQuietly(stream);
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}