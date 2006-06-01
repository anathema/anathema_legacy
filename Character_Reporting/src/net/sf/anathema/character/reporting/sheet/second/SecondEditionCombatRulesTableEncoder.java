package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.elements.TableList;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class SecondEditionCombatRulesTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font commentFont;
  private final Font font;

  public SecondEditionCombatRulesTableEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.font = TableEncodingUtilities.createFont(baseFont);
  }

  @Override
  protected PdfPTable createTable(IGenericCharacter character, Bounds bounds) {
    float cellPadding = 0.05f;
    PdfPTable table = new PdfPTable(new float[] { 1f, cellPadding, 1.1f, cellPadding, 1f });

    Phrase knockdownAndStunningPhrase = new Phrase(resources.getString("Sheet.Combat.Knockdown.Header") + "\n", font); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownAndStunningPhrase.add(new Chunk(
        resources.getString("Sheet.Combat.Knockdown.Comment") + "\n\n", commentFont)); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownAndStunningPhrase.add(new Chunk(resources.getString("Sheet.Combat.Stunning.Header") + "\n", font)); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownAndStunningPhrase.add(new Chunk(resources.getString("Sheet.Combat.Stunning.Comment"), commentFont)); //$NON-NLS-1$

    table.addCell(new TableCell(createCombatAttackList(), Rectangle.BOX));
    table.addCell(createSpaceCell());
    table.addCell(createContentCell(knockdownAndStunningPhrase));
    table.addCell(createSpaceCell());
    table.addCell(createCommonActionsTable());
    return table;
  }

  private TableList createCombatAttackList() {
    TableList list = new TableList(commentFont);
    list.addHeader(new Chunk(resources.getString("Sheet.Combat.OrderAttackEvents"), font), true); //$NON-NLS-1$
    list.addHeader(new Chunk("\n", commentFont), false); //$NON-NLS-1$
    list.addHeader(new Chunk("\n", commentFont), false); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.DeclareAttack")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.DeclareDefence")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.AttackRoll")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.AttackReroll")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.SubstractPenalties")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.DefenseReroll")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.CalculateRawDamage")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.RollDamage")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.Counterattack")); //$NON-NLS-1$
    list.addItem(resources.getString("Sheet.Combat.AttackList.ApplyDamage")); //$NON-NLS-1$
    TableCell spaceCell = new TableCell(new Phrase(" ", commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
    spaceCell.setPadding(0);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    TableCell rulesCommentCell = new TableCell(new Phrase(
        resources.getString("Sheet.Combat.Comment.Rules"), commentFont), Rectangle.NO_BORDER); //$NON-NLS-1$
    rulesCommentCell.setPadding(0);
    list.addCell(rulesCommentCell);
    return list;
  }

  private PdfPTable createCommonActionsTable() {
    float[] columnWidths = new float[] { 5, 1.5f, 1.5f };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = resources.getString("Sheet.Combat.CommonActions.Header"); //$NON-NLS-1$
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, font));
    headerCell.setColspan(columnWidths.length);
    table.addCell(headerCell);
    String actionSubheader = resources.getString("Sheet.Combat.CommonActions.Action"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(actionSubheader, commentFont)));
    String speedSubheader = resources.getString("Sheet.Combat.CommonActions.Speed"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(speedSubheader, commentFont)));
    String dvPenSubheader = resources.getString("Sheet.Combat.CommonActions.DV"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(dvPenSubheader, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", commentFont))); //$NON-NLS-1$
    addCommonActionsRow(table, "JoinBattle"); //$NON-NLS-1$
    addCommonActionsRow(table, "ReadyWeapon"); //$NON-NLS-1$
    addCommonActionsRow(table, "PhysicalAttack"); //$NON-NLS-1$
    addCommonActionsRow(table, "CoordinateAttack"); //$NON-NLS-1$
    addCommonActionsRow(table, "Aim"); //$NON-NLS-1$
    addCommonActionsRow(table, "Guard"); //$NON-NLS-1$
    addCommonActionsRow(table, "Move"); //$NON-NLS-1$
    addCommonActionsRow(table, "Dash"); //$NON-NLS-1$
    addCommonActionsRow(table, "Misc"); //$NON-NLS-1$
    addCommonActionsRow(table, "Jump"); //$NON-NLS-1$
    addCommonActionsRow(table, "Rise"); //$NON-NLS-1$
    addCommonActionsRow(table, "Inactive"); //$NON-NLS-1$
    return table;
  }

  private void addCommonActionsRow(PdfPTable table, String actionId) {
    String actionName = resources.getString("Sheet.Combat.CommonActions." + actionId + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionName, commentFont)));
    String actionSpeed = resources.getString("Sheet.Combat.CommonActions." + actionId + ".Speed"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionSpeed, commentFont)));
    String actionDV = resources.getString("Sheet.Combat.CommonActions." + actionId + ".DV"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionDV, commentFont)));
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }

  private PdfPCell createSpaceCell() {
    return new TableCell(new Phrase(" ", font), Rectangle.NO_BORDER); //$NON-NLS-1$
  }

  private PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }
}