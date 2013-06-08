package net.sf.anathema.platform.fx;

import org.apache.commons.lang3.SystemUtils;

public class FxUtilities {
  public static boolean systemSupportsEmbeddedTooltips() {
    return !SystemUtils.IS_OS_MAC;
  }
}
