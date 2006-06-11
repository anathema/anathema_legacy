package net.sf.anathema.character.intimacies;

import java.util.Iterator;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.model.IntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
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

public class IntimaciesEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private final static int LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final BaseFont baseFont;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public IntimaciesEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  public String getHeaderKey() {
    return "Intimacies";
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    IIntimaciesAdditionalModel additionalModel = (IntimaciesAdditionalModel) character.getAdditionalModel(IntimaciesTemplate.ID);
    IIntimaciesModel model = additionalModel.getIntimaciesModel();
    Font font = TableEncodingUtilities.createFont(baseFont);
    Phrase intimacyPhrase = new Phrase();
    for (Iterator<IIntimacy> intimacies = model.getEntries().iterator(); intimacies.hasNext();) {
      IIntimacy intimacy = intimacies.next();
      String text = intimacy.getName();
      if (!intimacy.isComplete()) {
        text += " (" + intimacy.getTrait().getCurrentValue() + "/" + intimacy.getTrait().getMaximalValue() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      text += intimacies.hasNext() ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
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