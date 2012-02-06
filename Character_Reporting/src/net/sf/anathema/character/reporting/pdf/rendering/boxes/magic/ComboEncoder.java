package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combo.ComboContent;
import net.sf.anathema.character.reporting.pdf.content.combo.DisplayCombo;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SimpleColumn;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.LINE_HEIGHT;

public class ComboEncoder {

  public String getHeader(ReportContent reportContent) {
    return createContent(reportContent).getHeader();
  }

  public boolean hasContent(ReportContent reportContent) {
    return createContent(reportContent).hasContent();
  }

  public ComboContent createContent(ReportContent reportContent) {
    return reportContent.createSubContent(ComboContent.class);
  }

  public float encode(SheetGraphics graphics, ReportContent reportContent, Bounds maxContentBounds) throws DocumentException {
    if (!hasContent(reportContent)) {
      return 0;
    }
    ComboContent content = createContent(reportContent);
    SimpleColumn column = graphics.createSimpleColumn(maxContentBounds).withLeading(LINE_HEIGHT).get();
    addCombos(graphics, column, content);
    return column.getYLine();
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

  public Bounds calculateActualBoxBounds(Bounds restOfPage, float textEndY) {
    float boxY = textEndY - IVoidStateFormatConstants.TEXT_PADDING;
    return new Bounds(restOfPage.x, boxY, restOfPage.width, restOfPage.getMaxY() - boxY);
  }
}
