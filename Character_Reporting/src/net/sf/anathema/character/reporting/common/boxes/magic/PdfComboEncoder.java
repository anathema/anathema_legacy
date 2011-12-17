package net.sf.anathema.character.reporting.common.boxes.magic;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.PdfLineEncodingUtilities;
import net.sf.anathema.character.reporting.common.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.reporting.common.boxes.magic.CharmPrintNameTransformer;
import net.sf.anathema.character.reporting.common.encoder.PdfBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  public float encodeCombos(PdfContentByte directContent, IGenericCharacter character, Bounds maxBounds) throws DocumentException {
    List<IGenericCombo> combos = new ArrayList<IGenericCombo>(Arrays.asList(character.getCombos()));
    return encodeCombos(directContent, combos, maxBounds, false);
  }

  public float encodeCombos(PdfContentByte directContent, List<IGenericCombo> combos, Bounds maxBounds, boolean overflow) throws DocumentException {
    if (combos.isEmpty()) {
      return 0;
    }

    Bounds contentBounds = boxEncoder.calculateContentBounds(maxBounds);
    ColumnText column = PdfTextEncodingUtilities.createColumn(directContent, contentBounds, LINE_HEIGHT, Element.ALIGN_LEFT);
    addCombos(column, combos);

    float yPosition = column.getYLine();
    Bounds actualBoxBounds = calculateActualBoxBounds(maxBounds, yPosition);
    String headerString;
    if (overflow) {
      headerString = resources.getString("Sheet.Header.CombosOverflow"); //$NON-NLS-1$
    }
    else {
      headerString = resources.getString("Sheet.Header.Combos"); //$NON-NLS-1$
    }
    boxEncoder.encodeBox(directContent, actualBoxBounds, headerString);
    return actualBoxBounds.getHeight();
  }

  public float encodeFixedCombos(PdfContentByte directContent, List<IGenericCombo> combos, Bounds bounds) throws DocumentException {
    Bounds contentBounds = boxEncoder.calculateContentBounds(bounds);
    ColumnText column = PdfTextEncodingUtilities.createColumn(directContent, contentBounds, LINE_HEIGHT, Element.ALIGN_LEFT);
    addCombos(column, combos);

    float yPosition = column.getYLine();
    int remainingLines = (int) ((yPosition - contentBounds.getMinY()) / LINE_HEIGHT);
    Position lineStartPosition = new Position(contentBounds.getMinX(), yPosition - LINE_HEIGHT);
    PdfLineEncodingUtilities.encodeHorizontalLines(directContent, lineStartPosition, contentBounds.getMinX(), contentBounds.getMaxX(), LINE_HEIGHT,
                                                   remainingLines);

    String headerString = resources.getString("Sheet.Header.Combos"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, bounds, headerString);
    return bounds.getHeight();
  }

  private void addCombos(ColumnText columnText, List<IGenericCombo> combos) throws DocumentException {
    while (!combos.isEmpty()) {
      Phrase comboPhrase = createComboPhrase(combos.get(0));

      float yLine = columnText.getYLine();
      columnText.addText(comboPhrase);
      int status = columnText.go(true);
      columnText.setYLine(yLine);
      columnText.setText(comboPhrase);
      if (ColumnText.hasMoreText(status)) {
        break;
      }
      else {
        columnText.go();
        combos.remove(0);
      }
    }
  }

  private Phrase createComboPhrase(IGenericCombo combo) {
    Phrase phrase = new Phrase();

    String printName = combo.getName() == null ? "???" : combo.getName(); //$NON-NLS-1$
    phrase.add(new Chunk(printName + ": ", nameFont)); //$NON-NLS-1$

    String charmString = getCharmString(combo);
    phrase.add(new Chunk(charmString, font));

    return phrase;
  }

  private Bounds calculateActualBoxBounds(Bounds restOfPage, float textEndY) {
    float boxY = textEndY - IVoidStateFormatConstants.TEXT_PADDING;
    return new Bounds(restOfPage.x, boxY, restOfPage.width, restOfPage.getMaxY() - boxY);
  }

  private String getCharmString(IGenericCombo combo) {
    CharmPrintNameTransformer transformer = new CharmPrintNameTransformer(resources);
    String[] charmNames = ArrayUtilities.transform(combo.getCharms(), String.class, transformer);
    return AnathemaStringUtilities.concat(charmNames, ", "); //$NON-NLS-1$
  }
}
