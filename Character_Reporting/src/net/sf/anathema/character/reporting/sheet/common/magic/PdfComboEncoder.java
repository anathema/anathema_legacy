package net.sf.anathema.character.reporting.sheet.common.magic;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.PdfBoxEncoder;
import net.sf.anathema.character.reporting.common.PdfTextEncodingUtilities;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants.LINE_HEIGHT;

public class PdfComboEncoder {

  private final IResources resources;
  private final PdfBoxEncoder boxEncoder;
  private final Font font;
  private final Font nameFont;

  public PdfComboEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
    this.font = PdfTextEncodingUtilities.createTextFont(baseFont);
    this.nameFont = new Font(font);
    this.nameFont.setStyle(Font.BOLD);
  }

  public float encodeCombos(PdfContentByte directContent, IGenericCharacter character, Bounds restOfPage) throws DocumentException {
    IGenericCombo[] combos = character.getCombos();
    if (combos.length == 0) {
      return 0;
    }
    Bounds contentBounds = boxEncoder.calculateContentBounds(restOfPage);
    Phrase phrase = createComboPhrase(combos);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, contentBounds, LINE_HEIGHT).getYLine();
    Bounds actualBoxBounds = calculateActualBoxBounds(restOfPage, yPosition);
    String headerString = resources.getString("Sheet.Header.Combos"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, actualBoxBounds, headerString);
    return actualBoxBounds.getHeight();
  }

  private Phrase createComboPhrase(IGenericCombo[] combos) {
    Phrase phrase = new Phrase();
    for (IGenericCombo combo : combos) {
      if (!phrase.isEmpty()) {
        phrase.add(new Chunk("\n", font)); //$NON-NLS-1$
      }
      String printName = combo.getName() == null ? "???" : combo.getName(); //$NON-NLS-1$
      phrase.add(new Chunk(printName + ": ", nameFont)); //$NON-NLS-1$
      String charmString = getCharmString(combo);
      phrase.add(new Chunk(charmString, font));
    }
    return phrase;
  }

  private Bounds calculateActualBoxBounds(Bounds restOfPage, float textEndY) {
    float boxY = textEndY - 3;
    return new Bounds(restOfPage.x, boxY, restOfPage.width, restOfPage.getMaxY() - boxY);
  }

  private String getCharmString(IGenericCombo combo) {
    CharmPrintNameTransformer transformer = new CharmPrintNameTransformer(resources);
    String[] charmNames = ArrayUtilities.transform(combo.getCharms(), String.class, transformer);
    return AnathemaStringUtilities.concat(charmNames, ", "); //$NON-NLS-1$
  }
}
