package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IItemCreator {

  Item createItem(HeroTemplate template) throws PersistenceException;
}