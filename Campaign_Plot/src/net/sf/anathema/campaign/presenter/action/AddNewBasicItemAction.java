package net.sf.anathema.campaign.presenter.action;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.BasicItemData;
import net.sf.anathema.framework.presenter.action.AbstractAddNewItemAction;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class AddNewBasicItemAction extends AbstractAddNewItemAction<Object> {

  public static Action createMenuAction(
      IResources resources,
      IAnathemaModel anathemaModel,
      IItemType itemType,
      String nameKey) {
    SmartAction action = new AddNewBasicItemAction(anathemaModel, resources, itemType);
    action.setName(resources.getString(nameKey));
    return action;
  }

  private AddNewBasicItemAction(IAnathemaModel anathemaModel, IResources resources, IItemType itemType) {
    super(anathemaModel, resources, itemType.getId());
  }
  
  @Override
  protected Object createTemplate(Component parentComponent) {
    return new Object();
  }

  @Override
  protected IItem createNewItem(Object template, IItemType itemType) throws AnathemaException {
    return new AnathemaItem(itemType, new BasicItemData());
  }
}