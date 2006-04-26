package net.sf.anathema.gis.platform.menu;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Action;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.gis.main.impl.model.GisModel;
import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.platform.GisItemTypeConfiguration;
import net.sf.anathema.gis.platform.util.RestrictiveItemTypeEnabler;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class ShowGisAction extends SmartAction {

  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new ShowGisAction(resources.getString("GisItem.Show.Name"), resources, anathemaModel); //$NON-NLS-1$
    return action;
  }

  public ShowGisAction(String string, IResources resources, IAnathemaModel anathemaModel) {
    this.resources = resources;
    this.anathemaModel = anathemaModel;
    setName(string);
    anathemaModel.getItemManagement().addListener(
        new RestrictiveItemTypeEnabler(this, GisItemTypeConfiguration.GIS_ITEM_TYPE_ID));
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      IItemType itemType = anathemaModel.getItemTypeRegistry().getById(GisItemTypeConfiguration.GIS_ITEM_TYPE_ID);
      IGisModel gisModel = new GisModel(anathemaModel.getRepository().getRepositoryFolder());
      AnathemaItem gisItem = new AnathemaItem(itemType, new Identificate("GisItem"), gisModel); //$NON-NLS-1$
      gisItem.setPrintName(resources.getString("ItemType.Gis.PrintName")); //$NON-NLS-1$
      anathemaModel.getItemManagement().addItem(gisItem);
    }
    catch (AnathemaException e) {
      Message message = new Message("An error occured while creating gis: " + e.getMessage(), e);
      MessageUtilities.indicateMessage(getClass(), parentComponent, message);
    }
    catch (Throwable e) {
      Message message = new Message("An error occured while creating gis.", e);
      MessageUtilities.indicateMessage(getClass(), parentComponent, message);
    }
    finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}