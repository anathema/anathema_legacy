package net.sf.anathema.character.reporting.first.rendering.combat;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.first.content.combat.CombatStatsContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.AbstractCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableList;

public class CombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  @Override
  protected void addFirstCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    CombatStatsContent content = reportContent.createSubContent(CombatStatsContent.class);
    TableCell cell = new TableCell(createCombatAttackList(graphics, content), Rectangle.BOX);
    cell.setPaddingBottom(2f);
    table.addCell(cell);
  }

  private PdfPTable createCombatAttackList(SheetGraphics graphics, CombatStatsContent content) {
    TableList list = new TableList(graphics.createCommentFont());
    list.addHeader(new Chunk(content.getSequenceHeader(), graphics.createTextFont()), true);
    for (String sequenceItem : content.getSequenceItems()) {
      list.addItem(sequenceItem);
    }
    return list.getTable();
  }

  @Override
  protected void addSecondCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    CombatStatsContent content = reportContent.createSubContent(CombatStatsContent.class);
    addAsCell(graphics, table, content.getKnockdownChunks());
  }

  @Override
  protected void addThirdCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    CombatStatsContent content = reportContent.createSubContent(CombatStatsContent.class);
    addAsCell(graphics, table, content.getStunningChunks());
  }
}
