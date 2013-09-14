package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.action.IItemCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class ItemCreator implements IItemOperator {

  private final IItemCreator creator;
  private final ItemReceiver receiver;

  public ItemCreator(IItemCreator creator, ItemReceiver receiver) {
    this.creator = creator;
    this.receiver = receiver;
  }

  @Override
  public void operate(IDialogModelTemplate template) throws PersistenceException {
    Item item = creator.createItem(template);
    try {
      receiver.addItem(item);
    } catch (Throwable e) {
      throw new PersistenceException(e);
    }
  }
}