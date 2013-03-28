package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.IDatabaseActionProperties;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentDatabaseActionProperties implements IDatabaseActionProperties {

  private static final String EQUIPMENT_DATABASE_ITEM_ID = "EquipmentDatabase.Item"; //$NON-NLS-1$

  private final IResources resources;
  private final IApplicationModel anathemaModel;

  public EquipmentDatabaseActionProperties(IResources resources, IApplicationModel anathemaModel) {
    this.resources = resources;
    this.anathemaModel = anathemaModel;
  }

  @Override
  public String getToolTipText() {
    return resources.getString("EquipmentDatabase.NewAction.Tooltip"); //$NON-NLS-1$
  }

  @Override
  public GsonEquipmentDatabase createItemData(IDataFileProvider provider) {
    return GsonEquipmentDatabase.CreateFrom(anathemaModel);
  }
}
