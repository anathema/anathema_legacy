package net.sf.anathema.character.reporting.sheet.common.magic;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.LINE_HEIGHT;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.reporting.sheet.common.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfComboEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final PdfBoxEncoder boxEncoder;

  public PdfComboEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public float encodeCombos(PdfContentByte directContent, IGenericCombo[] combos, Bounds restOfPage)
      throws DocumentException {
    Bounds contentBounds = boxEncoder.calculateContentBounds(restOfPage);
    Phrase phrase = new Phrase();
    Font font = PdfTextEncodingUtilities.createTextFont(baseFont);
    Font nameFont = new Font(font);
    nameFont.setStyle(Font.BOLD);
    for (IGenericCombo combo : combos) {
      if (!phrase.isEmpty()) {
        phrase.add(new Chunk("\n", font)); //$NON-NLS-1$
      }
      phrase.add(new Chunk(combo.getName() + ": ", nameFont)); //$NON-NLS-1$
      String charmString = getCharmString(combo);
      phrase.add(new Chunk(charmString, font));
    }
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, contentBounds, LINE_HEIGHT).getYLine();
    Bounds actualBounds = calculateActualBounds(restOfPage, yPosition);
    boxEncoder.encodeBox(directContent, new NullPdfContentEncoder(), null, actualBounds, "Combos"); //$NON-NLS-1$
    return actualBounds.y;

  }

  private Bounds calculateActualBounds(Bounds restOfPage, float textEndY) {
    float boxY = textEndY - 3;
    return new Bounds(restOfPage.x, boxY, restOfPage.width, restOfPage.getMaxY() - boxY);
  }

  private String getCharmString(IGenericCombo combo) {
    String[] charmNames = ArrayUtilities.transform(combo.getCharms(), String.class, new ITransformer<ICharm, String>() {
      public String transform(ICharm input) {
        return resources.getString(input.getId());
      }
    });
    String charmString = AnathemaStringUtilities.concat(charmNames, ", "); //$NON-NLS-1$
    return charmString;
  }
}