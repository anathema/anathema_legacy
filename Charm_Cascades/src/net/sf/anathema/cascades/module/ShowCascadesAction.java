package net.sf.anathema.cascades.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.repository.AnathemaNullDataItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.Action;
import java.awt.Component;
import java.awt.Cursor;

public class ShowCascadesAction extends SmartAction {

  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action createToolAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new ShowCascadesAction(resources, anathemaModel);
    action.setIcon(new CascadesUI(resources).getCascadesToolBarIcon());
    action.setToolTipText(resources.getString("CharmCascades.ShowCascades.Tooltip")); //$NON-NLS-1$
    return action;
  }

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new ShowCascadesAction(resources, anathemaModel);
    action.setName(resources.getString("CharmCascades.ShowCascades.Name")); //$NON-NLS-1$
    return action;
  }

  private ShowCascadesAction(IResources resources, IAnathemaModel anathemaModel) {
    this.resources = resources;
    this.anathemaModel = anathemaModel;
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      IItemType itemType = anathemaModel.getItemTypeRegistry().getById(
              CharmCascadeItemTypeConfiguration.CHARM_CASCADES_ITEM_TYPE_ID);
      IItem cascadeItem = new AnathemaNullDataItem(itemType, new Identifier("CharmCascades")); //$NON-NLS-1$
      cascadeItem.setPrintName(resources.getString("ItemType.CharmCascades.PrintName")); //$NON-NLS-1$
      anathemaModel.getItemManagement().addItem(cascadeItem);
    } catch (AnathemaException | IllegalStateException e) {
      handleExceptionWithMessage(parentComponent, e);
    } catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent,
              new Message("An error occured while opening the Charm Cascades.", e)); //$NON-NLS-1$
    } finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  private void handleExceptionWithMessage(Component parentComponent, Exception e) {
    MessageUtilities.indicateMessage(getClass(), parentComponent,
            new Message("An error occured while opening the Charm Cascades:\n" + e.getMessage(), e)); //$NON-NLS-1$
  }
}