package net.sf.anathema.character.db.additional;

public class NativeLookshyDbRules extends BasicAdditionalLookshyDbRules {
  private final String[] compulsiveCharmIDs = new String[] {
      "Dragon-Blooded.Wind-CarriedWordsTechnique", "Dragon-Blooded.ElementalBoltAttack" }; //$NON-NLS-1$ //$NON-NLS-2$;

  @Override
  public String[] getCompulsiveCharmIDs() {
    return compulsiveCharmIDs;
  }
}