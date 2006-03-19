package net.sf.anathema.character.generic.framework.reporting.template;

import java.io.InputStream;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.framework.reporting.parameters.CharacterParameterUtilities;
import net.sf.anathema.framework.reporting.ExaltedReportUtilities;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public abstract class AbstractCharacterReportTemplate implements ICharacterReportTemplate {

  private final IResources resources;
  private final String fileBase;

  public AbstractCharacterReportTemplate(IResources resources, String fileBase) {
    this.resources = resources;
    this.fileBase = fileBase;
  }

  public String getFileBase() {
    return fileBase;
  }

  protected final IResources getResources() {
    return resources;
  }

  public void addParameterClasses(Map<String, String> parameterClassesByName) {
    ExaltedReportUtilities.addLogoParameterClass(parameterClassesByName);
    CharacterParameterUtilities.addCharacterDescriptionParameterClasses(parameterClassesByName);
  }

  public void fillInParameters(
      Map<Object, Object> parameters,
      IGenericCharacter charcter,
      IGenericDescription description) throws ReportException {
    ExaltedReportUtilities.fillLogoParameter(parameters);
    CharacterParameterUtilities.fillInCharacterDescription(description, parameters);
  }

  protected final void addSubreportParameter(
      Map<Object, Object> parameters,
      String paramName,
      InputStream systemResource) throws ReportException {
    try {
      JasperReport abilitySubreport = (JasperReport) JRLoader.loadObject(systemResource);
      parameters.put(paramName, abilitySubreport);
    }
    catch (JRException e) {
      throw new ReportException(e);
    }
  }
}