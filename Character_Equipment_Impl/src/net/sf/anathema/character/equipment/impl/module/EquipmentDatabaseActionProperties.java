package net.sf.anathema.character.equipment.impl.module;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.AbstractDatabaseActionProperties;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentDatabaseActionProperties extends AbstractDatabaseActionProperties {

  private static final String EQUIPMENT_DATABASE_ITEM_ID = "EquipmentDatabase.Item"; //$NON-NLS-1$

  private final IResources resources;

  public EquipmentDatabaseActionProperties(IResources resources) {
    this.resources = resources;
  }

  public String getItemTypeId() {
    return EquipmentDatabaseItemTypeConfiguration.EQUIPMENT_DATABASE_ITEM_TYPE_ID;
  }

  public String getActionName() {
    return resources.getString("EquipmentDatabase.NewAction.Name"); //$NON-NLS-1$
  }

  public Icon getActionIcon() {
    return new EquipmentUI(resources).getIcon(EquipmentStatisticsType.CloseCombat);
  }

  public String getToolTipText() {
    return resources.getString("EquipmentDatabase.NewAction.Tooltip"); //$NON-NLS-1$
  }

  public String getProgressMonitorTitle() {
    return resources.getString("Equipment.DatabaseStart.Progress.Title"); //$NON-NLS-1$;
  }

  public String getProgressTaskTitle() {
    return resources.getString("Equipment.DatabaseStart.Progress.Presentation"); //$NON-NLS-1$
  }

  @Override
  protected String getFolderName() {
    return "equipment"; //$NON-NLS-1$
  }

  public String getItemId() {
    return EQUIPMENT_DATABASE_ITEM_ID;
  }

  public IItemData createItemData(File repositoryFolder) throws IOException {
    File parentFolder = getParentFolder(repositoryFolder);
    return new Db4OEquipmentDatabase(new File(/*parentFolder,*/Db4OEquipmentDatabase.DATABASE_FILE));
  }
}
