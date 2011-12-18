package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.ComplexPoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.NullPoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.OverdrivePoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.PeripheralPoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.PersonalPoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.pools.PoolRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.NaturalRecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.NullRecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.RecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.SpecialRecoveryRow;
import net.sf.anathema.character.reporting.pdf.content.essence.recovery.TotalRecoveryRow;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IdentifiedInteger;

import java.util.ArrayList;
import java.util.List;

public class ExtendedEssenceContent extends AbstractSubContent {

  private IGenericCharacter character;

  public ExtendedEssenceContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public List<PoolRow> getPoolRows() {
    List<PoolRow> rows = getAvailablePoolRows();
    addMissingPoolRows(rows);
    return rows;
  }

  public List<RecoveryRow> getRecoveryRows() {
    List<RecoveryRow> rows = getAvailableRecoveryRows();
    addMissingRecoveryRows(rows);
    return rows;
  }

  @Override
  public String getHeaderKey() {
    return "Essence"; //$NON-NLS-1$
  }

  public int getNumberOfContentLines() {
    int availableRows = Math.max(getAvailablePoolRows().size(), getAvailableRecoveryRows().size());
    return Math.max(availableRows, 5);
  }
  
  public int getOverallLineCount() {
    return getNumberOfContentLines() + 1;
  }

  private List<PoolRow> getAvailablePoolRows() {
    List<PoolRow> rows = new ArrayList<PoolRow>();
    addPool(rows, new PersonalPoolRow(getResources(), character));
    addPool(rows, new PeripheralPoolRow(getResources(), character));
    addPool(rows, new OverdrivePoolRow(getResources(), character));
    addComplexPools(rows);
    return rows;
  }

  private void addComplexPools(List<PoolRow> rows) {
    for (IdentifiedInteger complexPool : character.getComplexPools()) {
      ComplexPoolRow poolRow = new ComplexPoolRow(getResources(), character, complexPool);
      addPool(rows, poolRow);
    }
  }

  private void addPool(List<PoolRow> rows, PoolRow personalPoolRow) {
    if (personalPoolRow.isDisplayable()) {
       rows.add(personalPoolRow);
    }
  }

  private void addMissingPoolRows(List<PoolRow> rows) {
    int missingCount = getNumberOfContentLines() - rows.size();
    for (int index = 0; index < missingCount; index++)  {
      rows.add(new NullPoolRow());
    }
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

  public int getEssenceValue() {
    return  character.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
  }

  public String getRegainHeaderLabel() {
    return getResources().getString("Sheet.Essence.Regaining");
  }

  public String getEssenceTotalHeaderLabel() {
    return getResources().getString("Sheet.Essence.Total");
  }

  public String getCommittedHeaderLabel() {
    return getResources().getString("Sheet.Essence.Committed");
  }

  public String getAvailableHeaderLabel() {
    return getResources().getString("Sheet.Essence.Available");
  }

  public String getAtEaseHeaderLabel() {
    return getResources().getString("Sheet.Essence.AtEase");
  }

  public String getRelaxHeaderLabel() {
    return getResources().getString("Sheet.Essence.Relaxed");
  }

  public int getEssenceMax() {
    return character.getEssenceLimitation().getAbsoluteLimit(character);
  }
}
