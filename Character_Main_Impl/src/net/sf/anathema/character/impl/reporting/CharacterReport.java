package net.sf.anathema.character.impl.reporting;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.generic.GenericCharacter;
import net.sf.anathema.character.model.generic.GenericDescription;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.repository.IItem;

public final class CharacterReport implements IJasperReport {

  private String printName;
  private final ICharacterReportTemplate template;

  public CharacterReport(String printName, ICharacterReportTemplate template) {
    this.printName = printName;
    this.template = template;
  }

  @Override
  public final String toString() {
    return printName;
  }

  public final InputStream createReportInputStream() {
    return CharacterReport.class.getClassLoader().getResourceAsStream(getJasperReportFilePath());
  }

  protected final String getJasperReportFilePath() {
    return template.getFileBase() + ".jasper"; //$NON-NLS-1$;
  }

  public final boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    IItemData itemData = item.getItemData();
    if (!(itemData instanceof ICharacter)) {
      return false;
    }
    ICharacter characterData = (ICharacter) itemData;
    if (!characterData.hasStatistics()) {
      return template.supports(null);
    }
    return template.supports(GenericCharacterUtilities.createGenericCharacter(characterData.getStatistics()));
  }

  private final IReportDataSource createSheetDataSource() {
    return new IReportDataSource() {
      public int getRowCount() {
        return 1;
      }

      public Object getValue(int currentRow, String columnName) {
        return null;
      }
    };
  }

  public IReportDataSource getDataSource(IItem item) {
    return createSheetDataSource();
  }

  public Map getParameters(IItem item) throws ReportException {
    ICharacter characterData = (ICharacter) item.getItemData();
    Map<Object, Object> parameters = new HashMap<Object, Object>();
    GenericCharacter character = GenericCharacterUtilities.createGenericCharacter(characterData.getStatistics());
    GenericDescription description = new GenericDescription(characterData.getDescription());
    template.fillInParameters(parameters, character, description);
    return parameters;
  }
}