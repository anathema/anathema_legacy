package net.sf.anathema.character.equipment.impl.reporting.content.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.character.EquipmentObjectPresenter;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public final class EquipmentNameStatsGroup<T extends IEquipmentStats> extends AbstractNameStatsGroup<T> implements
        IEquipmentStatsGroup<T> {

  private final IResources resources;

  public EquipmentNameStatsGroup(IResources resources) {
    super(resources);
    this.resources = resources;
  }

  @Override
  protected String getHeaderResourceKey() {
    return "Sheet.Equipment.Header.Name"; //$NON-NLS-1$
  }

  @Override
  public void addContent(PdfPTable table, Font font, T stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    } else {
      String name = stats.getName().getId();
      String resourceKey = getResourceBase() + name;
      if (resources.supportsKey(resourceKey)) {
        name = resources.getString(resourceKey);
      }
      table.addCell(createTextCell(font, name));
    }
  }

  @Override
  protected String getResourceBase() {
    return EquipmentObjectPresenter.EQUIPMENT_NAME_PREFIX;
  }
}
