package net.sf.anathema.character.reporting.sheet.first;

import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

import net.sf.anathema.character.reporting.sheet.elements.TableList;
import net.sf.anathema.character.reporting.sheet.second.AbstractCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableCell;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionCombatRulesTableEncoder extends AbstractCombatRulesTableEncoder {

  public FirstEditionCombatRulesTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected void addFirstCell(PdfPTable table) {
    table.addCell(new TableCell(createCombatAttackList(), Rectangle.BOX));
  }

  private TableList createCombatAttackList() {
    TableList list = new TableList(getCommentFont());
    list.addHeader(new Chunk(getResources().getString("Sheet.Combat.Sequence"), getFont()), true); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.AttackRoll")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.SubtractPenalties")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.DefenceRoll")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.DetermineDamage")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.CheckKnockdown")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.ApplySoak")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.RollDamage")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.ApplyDamage")); //$NON-NLS-1$
    list.addItem(getResources().getString("Sheet.Combat.Sequence.CheckStun")); //$NON-NLS-1$
    return list;
  }

  @Override
  protected void addSecondCell(PdfPTable table) {
    Phrase knockdownPhrase = new Phrase(getResources().getString("Sheet.Combat.Knockdown.Header") + "\n", getFont()); //$NON-NLS-1$ //$NON-NLS-2$
    knockdownPhrase.add(new Chunk(getResources().getString("Sheet.Combat.Knockdown.First.Comment") + "\n\n", getCommentFont())); //$NON-NLS-1$ //$NON-NLS-2$
    table.addCell(createContentCell(knockdownPhrase));
  }

  @Override
  protected void addThirdCell(PdfPTable table) {
    Phrase stunningPhrase = new Phrase(getResources().getString("Sheet.Combat.Stunning.Header") + "\n", getFont()); //$NON-NLS-1$ //$NON-NLS-2$
    stunningPhrase.add(new Chunk(getResources().getString("Sheet.Combat.Stunning.First.Comment"), getCommentFont())); //$NON-NLS-1$
    table.addCell(createContentCell(stunningPhrase));
  }
}