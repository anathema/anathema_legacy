package net.sf.anathema.framework.presenter.itemmanagement;

import java.awt.Component;
import java.awt.Cursor;
import java.io.IOException;

import javax.swing.Action;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.ItemManagementModelAdapter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaSaveAllAction extends SmartAction {

  private static class SaveAllEnabledListener extends ItemManagementModelAdapter {

    private final IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry;
    private final Action action;
    private final IItemMangementModel management;

    public SaveAllEnabledListener(
        IItemMangementModel management,
        IRegistry<IItemType, IRepositoryItemPersister> persisterRegistry,
        Action action) {
      this.management = management;
      this.persisterRegistry = persisterRegistry;
      this.action = action;
    }

    @Override
    public void itemAdded(IItem item) {
      setSaveAllEnabled();
    }

    @Override
    public void itemRemoved(IItem item) {
      setSaveAllEnabled();
    }

    private void setSaveAllEnabled() {
      boolean enabled = false;
      for (IItem currentItem : management.getAllItems()) {
        IRepositoryItemPersister itemPersister = persisterRegistry.get(currentItem.getItemType());
        enabled = enabled || itemPersister != null;
      }
      action.setEnabled(enabled);
    }
  }

  private IAnathemaModel model;
  private IResources resources;

  public static Action createToolAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaSaveAllAction(model, resources);
    action.setToolTipText(resources.getString("AnathemaPersistence.SaveAllAction.Tooltip")); //$NON-NLS-1$
    action.setIcon(resources.getImageIcon("tools/SaveAll24.gif")); //$NON-NLS-1$
    return action;
  }

  public static Action createMenuAction(IAnathemaModel model, IResources resources) {
    SmartAction action = new AnathemaSaveAllAction(model, resources);
    action.setName(resources.getString("AnathemaPersistence.SaveAllAction.Name")); //$NON-NLS-1$
    return action;
  }

  private AnathemaSaveAllAction(IAnathemaModel model, IResources resources) {
    SaveAllEnabledListener listener = new SaveAllEnabledListener(
        model.getItemManagement(),
        model.getPersisterRegistry(),
        this);
    model.getItemManagement().addListener(listener);
    listener.itemAdded(null);
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    for (IItem item : model.getItemManagement().getAllItems()) {
      if (item.getItemType().supportsRepository()) {
        parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
          IRepositoryWriteAccess writeAccess = model.getRepository().createWriteAccess(item);
          IRepositoryItemPersister persister = model.getPersisterRegistry().get(item.getItemType());
          persister.save(writeAccess, item);
        }
        catch (IOException e) {
          MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
              resources.getString("AnathemaPersistence.SaveAction.Message.Error"), e)); //$NON-NLS-1$
        }
        catch (RepositoryException e) {
          MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
              resources.getString("AnathemaPersistence.SaveAction.Message.Error"), e)); //$NON-NLS-1$
        }
        finally {
          parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      }
    }
  }
}