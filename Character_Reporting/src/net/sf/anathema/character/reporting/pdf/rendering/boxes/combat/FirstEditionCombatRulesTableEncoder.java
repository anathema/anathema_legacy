package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.Chunk;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combat.FirstEditionCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableList;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionCombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  @Override
  protected void addFirstCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    FirstEditionCombatStatsContent content = reportContent.createSubContent(FirstEditionCombatStatsContent.class);
    TableCell cell = new TableCell(createCombatAttackList(graphics, content), Rectangle.BOX);
    cell.setPaddingBottom(2f);
    table.addCell(cell);
  }

  private PdfPTable createCombatAttackList(SheetGraphics graphics, FirstEditionCombatStatsContent content) {
    TableList list = new TableList(graphics.createCommentFont());
    list.addHeader(new Chunk(content.getSequenceHeader(), graphics.createTextFont()), true);
    for (String sequenceItem : content.getSequenceItems()) {
      list.addItem(sequenceItem);
    }
    return list.getTable();
  }

  @Override
  protected void addSecondCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    FirstEditionCombatStatsContent content = reportContent.createSubContent(FirstEditionCombatStatsContent.class);
    addAsCell(graphics, table, content.getKnockdownChunks());
  }

  @Override
  protected void addThirdCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    FirstEditionCombatStatsContent content = reportContent.createSubContent(FirstEditionCombatStatsContent.class);
     addAsCell(graphics, table, content.getStunningChunks());
  }
}
