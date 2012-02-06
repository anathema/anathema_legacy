package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combo.ComboContent;
import net.sf.anathema.character.reporting.pdf.content.combo.DisplayCombo;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SimpleColumn;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.LINE_HEIGHT;

public class ComboEncoder {

  private final IResources resources;
  private final PdfBoxEncoder boxEncoder;

  public ComboEncoder(IResources resources) {
    this.resources = resources;
    this.boxEncoder = new PdfBoxEncoder();
  }

  public float encodeCombos(SheetGraphics graphics, ReportContent reportContent, Bounds maxBounds) throws DocumentException {
    ComboContent content = reportContent.createSubContent(ComboContent.class);
    if (!content.hasContent()) {
      return 0;
    }
    Bounds maxContentBounds = boxEncoder.calculateContentBounds(maxBounds);
    SimpleColumn column = graphics.createSimpleColumn(maxContentBounds).withLeading(LINE_HEIGHT).get();
    addCombos(graphics, column, content);
    float yPosition = column.getYLine();
    Bounds actualBoxBounds = calculateActualBoxBounds(maxBounds, yPosition);
    boxEncoder.encodeBox(graphics, actualBoxBounds, content.getHeader());
    return actualBoxBounds.getHeight();
  }

  private void addCombos(SheetGraphics graphics, SimpleColumn columnText, ComboContent content) throws DocumentException {
    List<DisplayCombo> combos = content.getCombos();
    while (!combos.isEmpty()) {
      Phrase comboPhrase = createPhrase(graphics, combos.get(0));
      float yLine = columnText.getYLine();
      columnText.addText(comboPhrase);
      int status = columnText.simulate();
      columnText.setYLine(yLine);
      columnText.setText(comboPhrase);
      if (SimpleColumn.hasMoreText(status)) {
        break;
      } else {
        columnText.encode();
        combos.remove(0);
      }
    }
  }

  private Phrase createPhrase(SheetGraphics graphics, DisplayCombo combo) {
    Phrase phrase = new Phrase();
    phrase.add(new Chunk(combo.name + ": ", graphics.createBoldFont())); //$NON-NLS-1$
    phrase.add(new Chunk(combo.charms, graphics.createTextFont()));
    return phrase;
  }

  private Bounds calculateActualBoxBounds(Bounds restOfPage, float textEndY) {
    float boxY = textEndY - IVoidStateFormatConstants.TEXT_PADDING;
    return new Bounds(restOfPage.x, boxY, restOfPage.width, restOfPage.getMaxY() - boxY);
  }
}
