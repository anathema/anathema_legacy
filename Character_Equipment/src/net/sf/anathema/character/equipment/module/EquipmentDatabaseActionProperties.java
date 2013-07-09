package net.sf.anathema.character.equipment.module;

import net.sf.anathema.character.equipment.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.IDatabaseActionProperties;
import net.sf.anathema.initialization.repository.DataFileProvider;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentDatabaseActionProperties implements IDatabaseActionProperties {

  private final Resources resources;
  private final IApplicationModel anathemaModel;

  public EquipmentDatabaseActionProperties(Resources resources, IApplicationModel anathemaModel) {
    this.resources = resources;
    this.anathemaModel = anathemaModel;
  }

  @Override
  public String getToolTipText() {
    return resources.getString("EquipmentDatabase.NewAction.Tooltip");
  }

  @Override
  public GsonEquipmentDatabase createItemData(DataFileProvider provider) {
    return GsonEquipmentDatabase.CreateFrom(anathemaModel);
  }
}
