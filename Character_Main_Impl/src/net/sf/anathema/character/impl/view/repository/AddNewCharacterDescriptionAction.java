package net.sf.anathema.character.impl.view.repository;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.AbstractAddNewItemAction;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class AddNewCharacterDescriptionAction extends AbstractAddNewItemAction<Object> {

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel, String nameResource) {
    SmartAction action = new AddNewCharacterDescriptionAction(anathemaModel, resources);
    action.setName(resources.getString(nameResource));
    return action;
  }

  private AddNewCharacterDescriptionAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources, ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID);
  }

  @Override
  protected Object createTemplate(Component parentComponent) {
    return new Object();
  }
  
  @Override
  protected final IItem createNewItem(Object template, IItemType itemType) throws AnathemaException {
    ExaltedCharacter character = new ExaltedCharacter();
    return new AnathemaItem(itemType, character);
  }
}