package net.sf.anathema.framework.reporting;

import java.net.URL;
import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;

public class ExaltedReportUtilities {

  public static final String EXALTED_LOGO_URL = "ExaltedLogoUrl"; //$NON-NLS-1$

  private ExaltedReportUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static void addLogoParameterClass(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(EXALTED_LOGO_URL, URL.class.getName());
  }

  public static void fillLogoParameter(Map<Object, Object> parameters) {
    URL logoUrl = ExaltedReportUtilities.class.getClassLoader().getResource("ExaltedLogo.png"); //$NON-NLS-1$
    parameters.put(EXALTED_LOGO_URL, logoUrl);
  }
}