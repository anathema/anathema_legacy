package net.sf.anathema.character.reporting.sheet.common.magic;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.LINE_HEIGHT;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.generic.character.IGenericCharacter;
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

  public float encodeCombos(PdfContentByte directContent, IGenericCharacter character, Bounds restOfPage)
      throws DocumentException {
    IGenericCombo[] combos = character.getCombos();
    if (combos.length == 0) {
      return 0;
    }
    Bounds contentBounds = boxEncoder.calculateContentBounds(restOfPage);
    Phrase phrase = createComboPhrase(combos);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, contentBounds, LINE_HEIGHT).getYLine();
    Bounds actualBoxBounds = calculateActualBoxBounds(restOfPage, yPosition);
    boxEncoder.encodeBox(directContent, new NullPdfContentEncoder(), null, actualBoxBounds, "Combos"); //$NON-NLS-1$
    return actualBoxBounds.getHeight();
  }

  private Phrase createComboPhrase(IGenericCombo[] combos) {
    Phrase phrase = new Phrase();
    for (IGenericCombo combo : combos) {
      if (!phrase.isEmpty()) {
        phrase.add(new Chunk("\n", font)); //$NON-NLS-1$
      }
      phrase.add(new Chunk(combo.getName() + ": ", nameFont)); //$NON-NLS-1$
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
    String[] charmNames = ArrayUtilities.transform(combo.getCharms(), String.class, new ITransformer<ICharm, String>() {
      public String transform(ICharm input) {
        return resources.getString(input.getId());
      }
    });
    String charmString = AnathemaStringUtilities.concat(charmNames, ", "); //$NON-NLS-1$
    return charmString;
  }
}