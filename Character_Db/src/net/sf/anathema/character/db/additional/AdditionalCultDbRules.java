package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.GenericMagicLearnPool;

public class AdditionalCultDbRules extends DefaultAdditionalRules {

  private final static String[] allowedSpellIds = new String[] { "Terrestrial.DeathObsidianButterflies",//$NON-NLS-1$
      "Terrestrial.EmeraldCountermagic",//$NON-NLS-1$
      "Terrestrial.InfallibleMessenger",//$NON-NLS-1$
      "Terrestrial.InvulnerableSkinBronze",//$NON-NLS-1$
      "Terrestrial.StormwindRider",//$NON-NLS-1$
      "Terrestrial.BurningEyesOffender", //$NON-NLS-1$
      "Terrestrial.CallingWind'sKiss",//$NON-NLS-1$
      "Terrestrial.CommandingBeasts",//$NON-NLS-1$
      "Terrestrial.ConjuringAzureChariot",//$NON-NLS-1$
      "Terrestrial.EmeraldCircleBanishment",//$NON-NLS-1$
      "Terrestrial.FlightSeparation",//$NON-NLS-1$
      "Terrestrial.FlightBrilliantRaptor",//$NON-NLS-1$
      "Terrestrial.FlyingGuillotine",//$NON-NLS-1$
      "Terrestrial.FoodfromAerialTable",//$NON-NLS-1$
      "Terrestrial.InternalFlame",//$NON-NLS-1$
      "Terrestrial.MistsEventide",//$NON-NLS-1$
      "Terrestrial.ParalyzingContradiction",//$NON-NLS-1$
      "Terrestrial.PlagueBronzeSnakes",//$NON-NLS-1$
      "Terrestrial.RavenousFire",//$NON-NLS-1$
      "Terrestrial.RitualElementalEmpowerment",//$NON-NLS-1$
      "Terrestrial.RiverBlood",//$NON-NLS-1$
      "Terrestrial.SacredTongue",//$NON-NLS-1$
      "Terrestrial.SilentWordsDreamsNightmares",//$NON-NLS-1$
      "Terrestrial.SpiritMight",//$NON-NLS-1$
      "Terrestrial.SpiritSword",//$NON-NLS-1$
      "Terrestrial.StingIceHornet",//$NON-NLS-1$
      "Terrestrial.UnbreakableBonesStone",//$NON-NLS-1$
      "Terrestrial.UnconquerableSelf",//$NON-NLS-1$
      "Terrestrial.UnstoppableFountainsDepths",//$NON-NLS-1$
      "Terrestrial.VirtuousGuardianFlame",//$NON-NLS-1$
      "Terrestrial.OpenSpiritDoor",//$NON-NLS-1$     
      "Terrestrial.SummonElemental" };//$NON-NLS-1$

  public AdditionalCultDbRules() {
    super(
        DbCharacterModule.BACKGROUND_ID_BREEDING,
        DbCharacterModule.BACKGROUND_ID_COMMAND,
        DbCharacterModule.BACKGROUND_ID_CONNECTIONS,
        DbCharacterModule.BACKGROUND_ID_HENCHMEN,
        DbCharacterModule.BACKGROUND_ID_REPUTATION);
  }

  public void addSorceryRules(IBackgroundTemplate sorceryTemplate) {
    GenericMagicLearnPool pool = new GenericMagicLearnPool(sorceryTemplate, false);
    for (String id : allowedSpellIds) {
      pool.addIdException(id);
    }
    addMagicLearnPool(pool);
  }
}