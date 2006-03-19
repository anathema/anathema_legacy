package net.sf.anathema.development.reporting.util;

public class ParameterUtilities {
  public static String parameterString(String parameterName) {
    return "$P{" + parameterName + "}"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}