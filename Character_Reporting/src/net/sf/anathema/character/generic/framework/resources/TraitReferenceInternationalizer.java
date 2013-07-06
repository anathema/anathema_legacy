package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.reporting.pdf.content.stats.ValuedTraitReference;
import net.sf.anathema.lib.resources.Resources;

public class TraitReferenceInternationalizer {

  private static final String SHEET_MESSAGE = "SubtraitSheetMessage";
  private final Resources resources;

  public TraitReferenceInternationalizer(Resources resources) {
    this.resources = resources;
  }

  public String getSheetName(ValuedTraitReference reference) {
    return getI18nForMessage(reference, SHEET_MESSAGE);
  }

  private String getI18nForMessage(ValuedTraitReference reference, String message) {
    String i18nedId = new TraitTypeInternationalizer(resources).getScreenName(reference.getTraitType());
    String name = reference.getName();
    if (name == null) {
      return i18nedId;
    }
    String subtraitNameKey = reference.getTraitType().getId() + "." + name;
    if (!resources.supportsKey(subtraitNameKey)) {
      return resources.getString(message, i18nedId, name);
    }
    String i18nedSubtraitName = resources.getString(subtraitNameKey);
    return resources.getString(message, i18nedId, i18nedSubtraitName);
  }
}