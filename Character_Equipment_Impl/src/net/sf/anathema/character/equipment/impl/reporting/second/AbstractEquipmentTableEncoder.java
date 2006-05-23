package net.sf.anathema.character.equipment.impl.reporting.second;

import java.awt.Color;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.equipment.character.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditonalModelTemplate;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractEquipmentTableEncoder<T extends IEquipment> extends AbstractTableEncoder {

  private final Font font;
  private final Font headerFont;

  public AbstractEquipmentTableEncoder(BaseFont baseFont) {
    this.headerFont = new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE - 1, Font.NORMAL, Color.BLACK);
    this.font = new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE - 0.5f, Font.NORMAL, Color.BLACK);
  }

  protected final Font getFont() {
    return font;
  }

  @Override
  protected PdfPTable createTable(IGenericCharacter character) {
    IEquipmentStatsGroup<T>[] groups = createEquipmentGroups(character);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    for (int index = 0; index < groups.length; index++) {
      Font usedFont = index == 0 ? font : headerFont;
      table.addCell(createHeaderCell(
          groups[index].getTitle(),
          groups[index].getColumnCount(),
          index != groups.length - 1,
          usedFont));
    }
    T[] printEquipments = getPrintEquipments(character);
    for (int line = 0; line < Math.min(printEquipments.length, getLineCount()); line++) {
      encodeContentLine(table, groups, printEquipments[line]);
    }
    for (int line = printEquipments.length; line < getLineCount(); line++) {
      encodeContentLine(table, groups, null);
    }
    return table;
  }

  protected abstract IGenericTrait getTrait(IGenericCharacter character, T equipment);

  protected abstract T[] getPrintEquipments(IGenericCharacter character);

  protected abstract int getLineCount();

  protected abstract IEquipmentStatsGroup<T>[] createEquipmentGroups(IGenericCharacter character);

  private void encodeContentLine(PdfPTable table, IEquipmentStatsGroup<T>[] groups, T equipment) {
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        table.addCell(createSpaceCell());
      }
      groups[index].addContent(table, font, equipment);
    }
  }

  private float[] calculateColumnWidths(IEquipmentStatsGroup[] groups) {
    Float[] columnWidths = new Float[0];
    for (IEquipmentStatsGroup group : groups) {
      if (columnWidths.length != 0) {
        columnWidths = ArrayUtilities.concat(columnWidths, new Float(0.2));
      }
      columnWidths = ArrayUtilities.concat(columnWidths, group.getColumnWeights());
    }
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(columnWidths);
  }

  protected PdfPCell createSpaceCell() {
    PdfPCell cell = new PdfPCell(new Phrase("", font)); //$NON-NLS-1$
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private PdfPCell createHeaderCell(String text, int columnSpan, boolean useSpaceCell, Font textFont) {
    PdfPCell cell = new PdfPCell(new Phrase(text, textFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(useSpaceCell ? columnSpan + 1 : columnSpan);
    cell.setPaddingLeft(0);
    cell.setPaddingRight(0);
    return cell;
  }

  protected final IEquipmentAdditionalModel getEquipmentModel(IGenericCharacter character) {
    return (IEquipmentAdditionalModel) character.getAdditionalModel(EquipmentAdditonalModelTemplate.ID);
  }
}