package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.NaturalRecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.NullRecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.RecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.SpecialRecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.TotalRecoveryRow;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class RegainEssenceContent extends AbstractSubContent implements SubBoxContent{

  private IGenericCharacter character;

  public RegainEssenceContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public List<RecoveryRow> getRecoveryRows() {
    List<RecoveryRow> rows = getAvailableRecoveryRows();
    addMissingRecoveryRows(rows);
    return rows;
  }

  public int getNumberOfContentLines() {
    return getAvailableRecoveryRows().size();
  }

  public int getOverallLineCount() {
    return getNumberOfContentLines() + 1;
  }

  private List<RecoveryRow> getAvailableRecoveryRows() {
    List<RecoveryRow> rows = new ArrayList<RecoveryRow>();
    rows.add(new NaturalRecoveryRow(getResources()));
    addSpecialRecoveryRows(rows);
    rows.add(new TotalRecoveryRow(getResources()));
    return rows;
  }

  private void addSpecialRecoveryRows(List<RecoveryRow> rows) {
    String[] specialRecoveryRows = new String[]{"Sheet.Essence.Hearthstones", "Sheet.Essence.Cult"};
    for (String row : specialRecoveryRows) {
      SpecialRecoveryRow recoveryRow = new SpecialRecoveryRow(getResources(), row);
      rows.add(recoveryRow);
    }
  }

  private void addMissingRecoveryRows(List<RecoveryRow> rows) {
    int missingCount = getNumberOfContentLines() - rows.size();
    for (int index = 0; index < missingCount; index++)  {
      rows.add(rows.size() - 1, new NullRecoveryRow());
    }
  }

  public String getAtEaseHeaderLabel() {
    return getResources().getString("Sheet.Essence.AtEase");
  }

  public String getRelaxHeaderLabel() {
    return getResources().getString("Sheet.Essence.Relaxed");
  }

  @Override
  public String getHeader() {
    return getResources().getString("Sheet.Essence.Regaining");
  }
}
