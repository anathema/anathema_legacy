package net.sf.anathema.hero.combat.sheet.combat.encoder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.combat.sheet.combat.content.CombatAction;
import net.sf.anathema.hero.combat.sheet.combat.content.CombatStatsContent;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TableCell;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TableList;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public class CombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  @Override
  protected void addFirstCell(SheetGraphics graphics, ReportSession reportSession, PdfPTable table) {
    CombatStatsContent content = reportSession.createContent(CombatStatsContent.class);
    table.addCell(new TableCell(createCombatAttackList(graphics, content), Rectangle.BOX));
  }

  @Override
  protected void addSecondCell(SheetGraphics graphics, ReportSession reportSession, PdfPTable table) {
    CombatStatsContent content = reportSession.createContent(CombatStatsContent.class);
    addAsCell(graphics, table, content.getKnockdownAndStunningTexts());
  }

  @Override
  protected void addThirdCell(SheetGraphics graphics, ReportSession reportSession, PdfPTable table) {
    CombatStatsContent content = reportSession.createContent(CombatStatsContent.class);
    table.addCell(createCommonActionsTable(graphics, content));
  }

  private PdfPTable createCombatAttackList(SheetGraphics graphics, CombatStatsContent content) {
    TableList list = new TableList(graphics.createCommentFont());
    list.addHeader(new Chunk(content.getAttackHeader(), graphics.createTextFont()), true);
    list.addHeader(new Chunk("\n", graphics.createCommentFont()), false);
    for (String attack : content.getAttacks()) {
      list.addItem(attack);
    }
    list.addCell(createSpaceCell(graphics));
    list.addCell(createSpaceCell(graphics));
    list.addCell(createSpaceCell(graphics));
    TableCell rulesCommentCell = new TableCell(new Phrase(content.getAttackComment(), graphics.createCommentFont()), Rectangle.NO_BORDER);
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
    TableCell headerCell = createCommonActionsCell(new Phrase(header, graphics.createTextFont()));
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
