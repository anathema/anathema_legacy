package net.sf.anathema.character.db.additional;

public class AdditionalSequesteredTabernacleDbRules extends AdditionalCultDbRules {
  private final String[] compulsiveCharmIDs = new String[] {
      "Dragon-Blooded.Walker-Among-IrisesPerception", "Dragon-Blooded.Iris-BulbDiscourse" }; //$NON-NLS-1$ //$NON-NLS-2$;

  @Override
  public String[] getCompulsiveCharmIDs() {
    return compulsiveCharmIDs;
  }
}