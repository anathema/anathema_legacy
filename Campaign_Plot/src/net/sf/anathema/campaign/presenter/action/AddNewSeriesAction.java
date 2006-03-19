package net.sf.anathema.campaign.presenter.action;

import java.awt.Component;

import javax.swing.Action;

import net.sf.anathema.campaign.concrete.Series;
import net.sf.anathema.campaign.concrete.SeriesContentModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.module.SeriesTypeConfiguration;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.AbstractAddNewItemAction;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class AddNewSeriesAction extends AbstractAddNewItemAction<Object> {

  private final IAnathemaModel anathemaModel;

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    AddNewSeriesAction action = new AddNewSeriesAction(anathemaModel, resources);
    action.setName(resources.getString("Campaign.MenuName.New")); //$NON-NLS-1$
    return action;
  }

  private AddNewSeriesAction(IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources, SeriesTypeConfiguration.SERIES_ITEM_TYPE_ID);
    this.anathemaModel = anathemaModel;
  }

  @Override
  protected Object createTemplate(Component parentComponent) {
    return new Object();
  }

  @Override
  protected IItem createNewItem(Object template, IItemType itemType) throws AnathemaException {
    ISeries data = new Series(SeriesContentModel.createSupportedItemTypes(anathemaModel.getItemTypeRegistry()));
    return new AnathemaItem(itemType, data);
  }
}