package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.Action;
import javax.swing.KeyStroke;

import net.disy.commons.core.io.IOUtilities;
import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaSaveAction extends SmartAction {

  private static class SaveEnabledListener extends ItemManagementModelAdapter {

    private final IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry;
    private final Action action;

    public SaveEnabledListener(IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry, Action action) {
      this.persisterRegistry = persisterRegistry;
      this.action = action;
    }

    @Override
    public void itemSelected(IItem item) {
      if (item == null) {
        action.setEnabled(false);
        return;
      }
      IRepositoryItemPersister itemPersister = persisterRegistry.get(item.getItemType());
      action.setEnabled(itemPersister != null);
    }
  }

  private IAnathemaModel model;
  private IResources resources;

  public static Action createToolAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaSaveAction(model, resources);
    action.setToolTipText(resources.getString("AnathemaPersistence.SaveAction.Tooltip")); //$NON-NLS-1$
    action.setIcon(resources.getImageIcon("tools/Save24.gif")); //$NON-NLS-1$
    return action;
  }

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaSaveAction(model, resources);
    action.setName(resources.getString("AnathemaPersistence.SaveAction.Name")); //$NON-NLS-1$
    return action;
  }

  private AnathemaSaveAction(IAnathemaModel model, IResources resources) {
    SaveEnabledListener listener = new SaveEnabledListener(model.getPersisterRegistry(), this);
    setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
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
    }
    catch (IOException e) {
      MessageDialogFactory.showMessageDialog(parentComponent, new Message(
          resources.getString("AnathemaPersistence.SaveAction.Message.Error"), e)); //$NON-NLS-1$
      Logger.getLogger(getClass()).error(e);
    }
    catch (RepositoryException e) {
      MessageDialogFactory.showMessageDialog(parentComponent, new Message(
          resources.getString("AnathemaPersistence.SaveAction.Message.Error"), e)); //$NON-NLS-1$
      Logger.getLogger(getClass()).error(e);
    }
    finally {
      IOUtilities.close(stream);
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}