package net.sf.anathema.character.equipment.impl.module;

import javax.swing.Icon;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.impl.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.EquipmentDatabasePresenter;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.module.StartDatabaseAction;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class EquipmentDatabaseItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String EQUIPMENT_DATABASE_ITEM_TYPE_ID = "EquipmentDatabase"; //$NON-NLS-1$

  public EquipmentDatabaseItemTypeConfiguration() {
    super(new ItemType(EQUIPMENT_DATABASE_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        IEquipmentDatabase database = (IEquipmentDatabase) item.getItemData();
        EquipmentDatabaseView view = new EquipmentDatabaseView();
        IEquipmentDatabaseManagement model = new EquipmentDatabaseManagement(database);
        new EquipmentDatabasePresenter(resources, model, view).initPresentation();
        Icon icon = new EquipmentUI(resources).getStandardIcon(EquipmentStatisticsType.CloseCombat);
        return new EquipmentDatabaseItemView(resources.getString("ItemType.EquipmentDatabase.PrintName"), icon, view); //$NON-NLS-1$
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    EquipmentDatabaseActionProperties properties = new EquipmentDatabaseActionProperties(resources);
    return new IMenuItem[] { new ActionMenuItem(StartDatabaseAction.createMenuAction(
        resources,
        anathemaModel,
        properties)) };
  }
}