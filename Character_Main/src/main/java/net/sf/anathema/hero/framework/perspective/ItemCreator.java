package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.character.framework.display.ItemReceiver;
import net.sf.anathema.character.framework.display.IItemCreator;
import net.sf.anathema.hero.creation.IItemOperator;
import net.sf.anathema.character.framework.item.Item;
import net.sf.anathema.lib.exception.PersistenceException;

public class ItemCreator implements IItemOperator {

  private final IItemCreator creator;
  private final ItemReceiver receiver;

  public ItemCreator(IItemCreator creator, ItemReceiver receiver) {
    this.creator = creator;
    this.receiver = receiver;
  }

  @Override
  public void operate(HeroTemplate template) throws PersistenceException {
    Item item = creator.createItem(template);
    try {
      receiver.addItem(item);
    } catch (Throwable e) {
      throw new PersistenceException(e);
    }
  }
}