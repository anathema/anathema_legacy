package net.sf.anathema.namegenerator.anathema;

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

public class ShowNameGeneratorAction extends SmartAction {

  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new ShowNameGeneratorAction(resources, anathemaModel);
    action.setName(resources.getString("NameGenerator.ShowGenerator.Name")); //$NON-NLS-1$
    return action;
  }

  private ShowNameGeneratorAction(IResources resources, IAnathemaModel anathemaModel) {
    this.resources = resources;
    this.anathemaModel = anathemaModel;
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      String id = NameGeneratorItemTypeConfiguration.NAME_GENERATOR_ITEM_TYPE_ID;
      IItemType itemType = anathemaModel.getItemTypeRegistry().getById(id);
      AnathemaItem generatorItem = new AnathemaItem(itemType, new Identificate(id), null);
      generatorItem.setPrintName(resources.getString("ItemType.NameGenerator.PrintName")); //$NON-NLS-1$
      anathemaModel.getItemManagement().addItem(generatorItem);
    }
    catch (AnathemaException e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          "An error occured while creating character: " + e.getMessage(), e)); //$NON-NLS-1$
    }
    catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          "An error occured while creating character.", e)); //$NON-NLS-1$
    }
    finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}