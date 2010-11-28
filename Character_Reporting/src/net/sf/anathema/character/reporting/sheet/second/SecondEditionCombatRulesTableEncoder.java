package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.reporting.sheet.common.combat.AbstractCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.elements.TableList;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class SecondEditionCombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  public SecondEditionCombatRulesTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected void addFirstCell(PdfPTable table) {
    table.addCell(new TableCell(createCombatAttackList(), Rectangle.BOX));
  }

  @Override
  protected void addSecondCell(PdfPTable table) {
    Phrase knockdownAndStunningPhrase = new Phrase(
        getResources().getString("Sheet.Combat.Knockdown.Header") + "\n", getFont()); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownAndStunningPhrase.add(new Chunk(
        getResources().getString("Sheet.Combat.Knockdown.Second.Comment") + "\n\n", getCommentFont())); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownAndStunningPhrase.add(new Chunk(getResources().getString("Sheet.Combat.Stunning.Header") + "\n", getFont())); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownAndStunningPhrase.add(new Chunk(
        getResources().getString("Sheet.Combat.Stunning.Second.Comment"), getCommentFont())); //$NON-NLS-1$
    table.addCell(createContentCell(knockdownAndStunningPhrase));
  }

  @Override
  protected void addThirdCell(PdfPTable table) {
    table.addCell(createCommonActionsTable());
  }

  private PdfPTable createCombatAttackList() {
    TableList list = new TableList(getCommentFont());
    list.addHeader(new Chunk(getResources().getString("Sheet.Combat.OrderAttackEvents"), getFont()), true); //$NON-NLS-1$
    list.addHeader(new Chunk("\n", getCommentFont()), false); //$NON-NLS-1$
    list.addHeader(new Chunk("\n", getCommentFont()), false); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.DeclareAttack")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.DeclareDefence")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.AttackRoll")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.AttackReroll")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.SubstractPenalties")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.DefenseReroll")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.CalculateRawDamage")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.RollDamage")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.Counterattack")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.AttackList.ApplyDamage")); //$NON-NLS-1$
    TableCell spaceCell = new TableCell(new Phrase(" ", getCommentFont()), Rectangle.NO_BORDER); //$NON-NLS-1$
    spaceCell.setPadding(0);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    TableCell rulesCommentCell = new TableCell(new Phrase(
        getResources().getString("Sheet.Combat.Comment.Rules"), getCommentFont()), Rectangle.NO_BORDER); //$NON-NLS-1$
    rulesCommentCell.setPadding(0);
    list.addCell(rulesCommentCell);
    return list.getTable();
  }

  private PdfPTable createCommonActionsTable() {
    float[] columnWidths = new float[] { 5, 1.5f, 1.5f };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    String header = getResources().getString("Sheet.Combat.CommonActions.Header"); //$NON-NLS-1$
    final TableCell headerCell = createCommonActionsCell(new Phrase(header, getFont()));
    headerCell.setColspan(columnWidths.length);
    table.addCell(headerCell);
    String actionSubheader = getResources().getString("Sheet.Combat.CommonActions.Action"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(actionSubheader, getCommentFont())));
    String speedSubheader = getResources().getString("Sheet.Combat.CommonActions.Speed"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(speedSubheader, getCommentFont())));
    String dvPenSubheader = getResources().getString("Sheet.Combat.CommonActions.DV"); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(dvPenSubheader, getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase(" ", getCommentFont()))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", getCommentFont()))); //$NON-NLS-1$
    table.addCell(createCommonActionsCell(new Phrase(" ", getCommentFont()))); //$NON-NLS-1$
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
    String actionName = getResources().getString("Sheet.Combat.CommonActions." + actionId + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionName, getCommentFont())));
    String actionSpeed = getResources().getString("Sheet.Combat.CommonActions." + actionId + ".Speed"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionSpeed, getCommentFont())));
    String actionDV = getResources().getString("Sheet.Combat.CommonActions." + actionId + ".DV"); //$NON-NLS-1$//$NON-NLS-2$
    table.addCell(createCommonActionsCell(new Phrase(actionDV, getCommentFont())));
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }
}