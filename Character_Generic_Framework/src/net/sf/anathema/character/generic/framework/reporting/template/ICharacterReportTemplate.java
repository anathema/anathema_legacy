package net.sf.anathema.character.generic.framework.reporting.template;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharacterReportTemplate {

  public IIdentificate getType();

  public void addParameterClasses(Map<String, String> parameterClassesByName);

  public void fillInParameters(
      Map<Object, Object> parameters,
      IGenericCharacter character,
      IGenericDescription description) throws ReportException;

  public boolean supports(IGenericCharacter character);

  public String getFileBase();
}