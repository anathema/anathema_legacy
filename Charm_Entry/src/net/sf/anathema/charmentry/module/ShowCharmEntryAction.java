package net.sf.anathema.charmentry.module;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Action;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class ShowCharmEntryAction extends SmartAction {

  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new ShowCharmEntryAction(resources.getString("CharmEntry.Show.Name"), resources, anathemaModel); //$NON-NLS-1$
    return action;
  }

  public ShowCharmEntryAction(String string, IResources resources, IAnathemaModel anathemaModel) {
    this.resources = resources;
    this.anathemaModel = anathemaModel;
    setName(string);
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      IItemType itemType = anathemaModel.getItemTypeRegistry().getById(
          CharmEntryItemTypeConfiguration.CHARM_ENTRY_ITEM_TYPE_ID);
      AnathemaItem charmEntryItem = new AnathemaItem(itemType, new Identificate("CharmEntry"), null); //$NON-NLS-1$
      charmEntryItem.setPrintName(resources.getString("ItemType.CharmEntry.PrintName")); //$NON-NLS-1$
      anathemaModel.getItemManagement().addItem(charmEntryItem);
    }
    catch (AnathemaException e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          "An error occured while creating charm entry: " + e.getMessage(), e)); //$NON-NLS-1$
    }
    catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          "An error occured while creating charm entry.", e)); //$NON-NLS-1$
    }
    finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}