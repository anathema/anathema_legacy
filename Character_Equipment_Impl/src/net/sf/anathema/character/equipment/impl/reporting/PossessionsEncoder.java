package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditonalModelTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.elements.Line;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PossessionsEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final static int LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final BaseFont baseFont;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public PossessionsEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  public String getHeaderKey() {
    return "Possessions"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getAdditionalModel(EquipmentAdditonalModelTemplate.ID);
    Font font = TableEncodingUtilities.createFont(baseFont);
    Phrase intimacyPhrase = new Phrase();
    for (int index = 0; index < model.getEquipmentItems().length; index++) {
      IEquipmentItem item = model.getEquipmentItems()[index];
      if (item.getStats().length > 0) {
        continue;
      }
      String text = item.getTemplateId();
      text += index + 1 < model.getEquipmentItems().length ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      intimacyPhrase.add(new Chunk(text, font));
    }
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - 2);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, intimacyPhrase, textBounds, LINE_HEIGHT)
        .getYLine();
    yPosition -= LINE_HEIGHT;
    while (yPosition > bounds.y) {
      Line.createHorizontalByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode(directContent);
      yPosition -= LINE_HEIGHT;
    }
  }
}