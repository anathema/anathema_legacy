package net.sf.anathema.character.equipment.impl.reporting.second.stats;

import java.awt.Color;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.second.EquipmentEncodingUtilities;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public final class EquipmentNameStatsGroup<T extends IEquipment> implements IEquipmentStatsGroup {
  private final String title;
  private final IResources resources;

  public EquipmentNameStatsGroup(IResources resources) {
    this.resources = resources;
    this.title = resources.getString("Sheet.Equipment.Header.Name"); //$NON-NLS-1$ ;
  }

  public int getColumnCount() {
    return 1;
  }

  public String getTitle() {
    return title;
  }

  public Float[] getColumnWeights() {
    return new Float[] { new Float(6) };
  }

  public void addContent(PdfPTable table, Font font, IEquipment equipment) {
    if (equipment == null) {
      table.addCell(createNameCell(font, "")); //$NON-NLS-1$
    }
    else {
      String resourceKey = "Equipment.Name." + equipment.getName().getId(); //$NON-NLS-1$
      table.addCell(createNameCell(font, resources.getString(resourceKey)));
    }
  }

  private PdfPCell createNameCell(Font font, String text) {
    int border = StringUtilities.isNullOrTrimEmpty(text) ? Rectangle.BOTTOM : Rectangle.NO_BORDER;
    if (StringUtilities.isNullOrTrimEmpty(text)) {
      text = " "; //$NON-NLS-1$
    }
    return EquipmentEncodingUtilities.createContentCellTable(Color.BLACK, text, font, 0.5f, border, Element.ALIGN_LEFT);
  }
}