package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.MessageGenerator;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.gui.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

public class ItemCreationOperator implements IItemOperator {

  private final IItemCreator creator;
  private final IResources resources;
  private final ItemReceiver receiver;
  private final MessageGenerator titleGenerator;

  public ItemCreationOperator(IItemCreator creator, IResources resources, ItemReceiver receiver, MessageGenerator titleGenerator) {
    this.creator = creator;
    this.resources = resources;
    this.receiver = receiver;
    this.titleGenerator = titleGenerator;
  }

  @Override
  public void operate(Component parentComponent, IItemType type, IAnathemaWizardModelTemplate template) throws PersistenceException {
    final IItem item = creator.createItem(type, template);
    try {
      new ProgressMonitorDialog(parentComponent, titleGenerator.generateInformativeMessage()).run(new INonInterruptibleRunnableWithProgress() {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException {
          try {
            Object internationalizedType = resources.getString("ItemType." + item.getItemType() + ".PrintName");
            String taskTitle = resources.getString("AnathemaCore.AddItem.Progress.Task.View", internationalizedType);
            monitor.beginTaskWithUnknownTotalWork(taskTitle); //$NON-NLS-1$
            receiver.addItem(item);
          } catch (AnathemaException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    } catch (InvocationTargetException exception) {
      Throwable cause = exception.getCause();
      MessageDialogFactory.showMessageDialog(parentComponent,
              new Message("An error occured while creating repository item: " + cause.getMessage(), cause)); //$NON-NLS-1$
    } catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message("An error occured while creating repository item.", e)); //$NON-NLS-1$
    }
  }
}
