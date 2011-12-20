package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combat.CombatAction;
import net.sf.anathema.character.reporting.pdf.content.combat.SecondEditionCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.content.general.QualifiedText;
import net.sf.anathema.character.reporting.pdf.content.general.TextType;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableCell;
import net.sf.anathema.character.reporting.pdf.rendering.elements.TableList;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionCombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  public SecondEditionCombatRulesTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected void addFirstCell(ReportContent reportContent, PdfPTable table) {
    SecondEditionCombatStatsContent content = reportContent.createSubContent(SecondEditionCombatStatsContent.class);
    table.addCell(new TableCell(createCombatAttackList(content), Rectangle.BOX));
  }

  @Override
  protected void addSecondCell(ReportContent reportContent, PdfPTable table) {
    SecondEditionCombatStatsContent content = reportContent.createSubContent(SecondEditionCombatStatsContent.class);
    Phrase knockdownAndStunningPhrase = new Phrase("");
    for (QualifiedText text : content.getKnockdownAndStunningTexts()) {
      knockdownAndStunningPhrase.add(createChunk(text));
    }
    table.addCell(createContentCell(knockdownAndStunningPhrase));
  }

  @Override
  protected void addThirdCell(ReportContent reportContent, PdfPTable table) {
    SecondEditionCombatStatsContent content = reportContent.createSubContent(SecondEditionCombatStatsContent.class);
    table.addCell(createCommonActionsTable(content));
  }

  private PdfPTable createCombatAttackList(SecondEditionCombatStatsContent content) {
    TableList list = new TableList(getCommentFont());
    list.addHeader(new Chunk(content.getAttackHeader(), getFont()), true); //$NON-NLS-1$
    list.addHeader(new Chunk("\n", getCommentFont()), false); //$NON-NLS-1$
    for (String attack : content.getAttacks()) {
      list.addItem(attack);
    }
    list.addCell(createSpaceCell());
    list.addCell(createSpaceCell());
    list.addCell(createSpaceCell());
    TableCell rulesCommentCell = new TableCell(new Phrase(content.getAttackComment(), getCommentFont()), Rectangle.NO_BORDER); //$NON-NLS-1$
    list.addCell(rulesCommentCell);
    return list.getTable();
  }

  private TableCell createSpaceCell() {
    return new TableCell(new Phrase(" ", getCommentFont()), Rectangle.NO_BORDER);
  }

  private PdfPTable createCommonActionsTable(SecondEditionCombatStatsContent content) {
    float[] columnWidths = new float[] { 5f, 1.5f, 1.5f };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = content.getActionHeader();
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, getFont()));
    headerCell.setColspan(columnWidths.length);
    table.addCell(headerCell);
    for (CombatAction combatAction : content.getCombatActions()) {
      addCommonActionsCell(table, combatAction.name);
      addCommonActionsCell(table, combatAction.speed);
      addCommonActionsCell(table, combatAction.dv);
    }
    return table;
  }

  private void addCommonActionsCell(PdfPTable table, String text) {
    Phrase phrase = new Phrase(text, getCommentFont());
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    table.addCell(cell);
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }

  private Chunk createChunk(QualifiedText text) {
    Font font = text.type == TextType.Comment ? getCommentFont() : getFont();
    return new Chunk(text.text, font);
  }
}
