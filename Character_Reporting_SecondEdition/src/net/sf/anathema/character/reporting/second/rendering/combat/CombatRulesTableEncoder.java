package net.sf.anathema.character.reporting.second.rendering.combat;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combat.CombatAction;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.AbstractCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableList;
import net.sf.anathema.character.reporting.second.content.combat.CombatStatsContent;

public class CombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  @Override
  protected void addFirstCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    CombatStatsContent content = reportContent.createSubContent(CombatStatsContent.class);
    table.addCell(new TableCell(createCombatAttackList(graphics, content), Rectangle.BOX));
  }

  @Override
  protected void addSecondCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    CombatStatsContent content = reportContent.createSubContent(CombatStatsContent.class);
    addAsCell(graphics, table, content.getKnockdownAndStunningTexts());
  }

  @Override
  protected void addThirdCell(SheetGraphics graphics, ReportContent reportContent, PdfPTable table) {
    CombatStatsContent content = reportContent.createSubContent(CombatStatsContent.class);
    table.addCell(createCommonActionsTable(graphics, content));
  }

  private PdfPTable createCombatAttackList(SheetGraphics graphics, CombatStatsContent content) {
    TableList list = new TableList(graphics.createCommentFont());
    list.addHeader(new Chunk(content.getAttackHeader(), graphics.createTextFont()), true); //$NON-NLS-1$
    list.addHeader(new Chunk("\n", graphics.createCommentFont()), false); //$NON-NLS-1$
    for (String attack : content.getAttacks()) {
      list.addItem(attack);
    }
    list.addCell(createSpaceCell(graphics));
    list.addCell(createSpaceCell(graphics));
    list.addCell(createSpaceCell(graphics));
    TableCell rulesCommentCell =
            new TableCell(new Phrase(content.getAttackComment(), graphics.createCommentFont()), Rectangle.NO_BORDER); //$NON-NLS-1$
    list.addCell(rulesCommentCell);
    return list.getTable();
  }

  private TableCell createSpaceCell(SheetGraphics graphics) {
    return new TableCell(new Phrase(" ", graphics.createCommentFont()), Rectangle.NO_BORDER);
  }

  private PdfPTable createCommonActionsTable(SheetGraphics graphics, CombatStatsContent content) {
    float[] columnWidths = new float[]{5f, 1.5f, 1.5f};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = content.getActionHeader();
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, graphics.createTextFont()));
    headerCell.setColspan(columnWidths.length);
    table.addCell(headerCell);
    for (CombatAction combatAction : content.getCombatActions()) {
      addCommonActionsCell(graphics, table, combatAction.name);
      addCommonActionsCell(graphics, table, combatAction.speed);
      addCommonActionsCell(graphics, table, combatAction.dv);
    }
    return table;
  }

  private void addCommonActionsCell(SheetGraphics graphics, PdfPTable table, String text) {
    Phrase phrase = new Phrase(text, graphics.createCommentFont());
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    table.addCell(cell);
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }
}
