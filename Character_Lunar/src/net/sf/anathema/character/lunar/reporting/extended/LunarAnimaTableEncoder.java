package net.sf.anathema.character.lunar.reporting.extended;

import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.common.boxes.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.common.stats.anima.*;
import net.sf.anathema.lib.resources.IResources;

public class LunarAnimaTableEncoder extends AnimaTableEncoder implements IPdfTableEncoder {

  private final Chunk symbolChunk;

  public LunarAnimaTableEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, float fontSize) {
    super(resources, baseFont, fontSize);
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  @Override
  protected ColumnDescriptor[] getColumns() {
    return new ColumnDescriptor[] { new ColumnDescriptor(0.135f, "Sheet.AnimaTable.Header.Motes"), //$NON-NLS-1$
        new ColumnDescriptor(0.495f, "Sheet.AnimaTable.Header.BannerFlare"), //$NON-NLS-1$
        new ColumnDescriptor(0.145f, "Sheet.AnimaTable.Header.FormsLocked"), //$NON-NLS-1$
        new ColumnDescriptor(0.225f, "Sheet.AnimaTable.Header.Stealth") }; //$NON-NLS-1$
  }

  @Override
  protected void addAnimaRow(PdfPTable table, int level, IGenericCharacter character, String descriptionPrefix) {
    table.addCell(createRangeCell(level, character));
    table.addCell(createDescriptionCell(level, descriptionPrefix));
    table.addCell(createFormCell(level));
    table.addCell(createStealthCell(level));
  }

  protected final PdfPCell createFormCell(int level) {
    if (level < 2) {
      return createContentCell(""); //$NON-NLS-1$
    }
    PdfPCell cell = new PdfPCell(new Phrase(symbolChunk));
    configureCell(cell);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    return cell;
  }
}
