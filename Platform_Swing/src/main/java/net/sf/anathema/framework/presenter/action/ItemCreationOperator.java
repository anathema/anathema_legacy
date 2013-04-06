package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.swing.MessageUtilities;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

import java.awt.Component;

public class ItemCreationOperator implements IItemOperator {

  private final IItemCreator creator;
  private final ItemReceiver receiver;

  public ItemCreationOperator(IItemCreator creator, ItemReceiver receiver) {
    this.creator = creator;
    this.receiver = receiver;
  }

  @Override
  public void operate(Component parentComponent, IItemType type,
                      IDialogModelTemplate template) throws PersistenceException {
    final IItem item = creator.createItem(type, template);
    try {
      receiver.addItem(item);
    } catch (Throwable e) {
      Message message = new Message("An error occured while creating repository item.", e);
      MessageUtilities.indicateMessage(getClass(), parentComponent, message);
    }
  }
}