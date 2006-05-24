package net.sf.anathema.character.generic.framework.reporting.template;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.datasource.CharmDataSource;
import net.sf.anathema.character.generic.framework.reporting.datasource.ComboTextPartDataSource;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.jasper.ReportDataSourceAdapter;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractMagicUserCharacterReportTemplate extends AbstractStattedCharacterReportTemplate {

  public static final String CASTE = "caste"; //$NON-NLS-1$
  public static final String CHARM_DATA_SOURCE = "charmDataSource"; //$NON-NLS-1$  
  public static final String MELEE_WEAPON_DATA_SOURCE = "meleeWeaponDataSource"; //$NON-NLS-1$
  public static final String COMBO_STYLED_CDATA = "comboStyledText"; //$NON-NLS-1$

  public AbstractMagicUserCharacterReportTemplate(IResources resources, String filePath) {
    super(resources, filePath);
  }

  @Override
  public void addExtendedParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(CASTE, String.class.getName());
    parameterClassesByName.put(CHARM_DATA_SOURCE, IReportDataSource.class.getName());
    parameterClassesByName.put(COMBO_STYLED_CDATA, String.class.getName());
    addCharacterTypeSpecificParameterClasses(parameterClassesByName);
  }

  protected abstract void addCharacterTypeSpecificParameterClasses(Map<String, String> parameterClassesByName);

  @Override
  protected void fillInExtendedParameters(Map<Object, Object> parameters, IGenericCharacter character)
      throws ReportException {
    fillInCaste(parameters, character);
    CharmDataSource charmDataSource = new CharmDataSource(getResources(), character);
    parameters.put(CHARM_DATA_SOURCE, charmDataSource);
    parameters.put(ExaltVoidstateReportTemplate.CHARMPAGE_DATASOURCE, new ReportDataSourceAdapter(charmDataSource, 29));
    parameters.put(COMBO_STYLED_CDATA, new ComboTextPartDataSource(getResources(), character).getStyledCData());
    fillInCharacterTypeSpecificParameters(parameters, character);
  }

  private void fillInCaste(Map<Object, Object> parameters, IGenericCharacter character) {
    String casteId = character.getConcept().getCasteType().getId();
    String printName = null;
    if (casteId != null) {
      printName = getResources().getString("Caste." + casteId); //$NON-NLS-1$
    }
    parameters.put(CASTE, printName);
  }

  protected abstract void fillInCharacterTypeSpecificParameters(
      Map<Object, Object> parameters,
      IGenericCharacter character) throws ReportException;
}