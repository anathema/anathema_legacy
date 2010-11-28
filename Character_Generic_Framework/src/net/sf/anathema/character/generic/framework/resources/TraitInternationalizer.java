package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.lib.resources.IResources;

public class TraitInternationalizer {

  private static final String SHEET_MESSAGE = "SubtraitSheetMessage";//$NON-NLS-1$
  private static final String SCREEN_MESSAGE = "SubtraitScreenMessage";//$NON-NLS-1$
  private final IResources resources;

  public TraitInternationalizer(IResources resources) {
    this.resources = resources;
  }

  public String getScreenName(ITraitReference reference) {
    return getI18nForMessage(reference, SCREEN_MESSAGE);
  }

  public String getSheetName(ITraitReference reference) {
    return getI18nForMessage(reference, SHEET_MESSAGE);
  }

  private String getI18nForMessage(ITraitReference reference, String message) {
    String id = reference.getTraitType().getId();
    String i18nedId = resources.getString(id);
    String name = reference.getName();
    if (name == null) {
      return i18nedId;
    }
    String subtraitNameKey = id + "." + name; //$NON-NLS-1$
    if (!resources.supportsKey(subtraitNameKey)) {
      return resources.getString(message, i18nedId, name);
    }
    String i18nedSubtraitName = resources.getString(subtraitNameKey);
    return resources.getString(message, i18nedId, i18nedSubtraitName);
  }
}