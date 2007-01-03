package net.sf.anathema.character.db.additional;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.magic.ISpell;

public class CultSorceryLearnPool extends AbstractSorceryLearnPool {

  String[] allowedSpellIds = new String[] { "Terrestrial.DeathObsidianButterflies",//$NON-NLS-1$
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

  public CultSorceryLearnPool(IBackgroundTemplate sorceryTemplate) {
    super(sorceryTemplate);
  }

  @Override
  protected boolean isTerrestrialSpellAllowed(ISpell spell) {
    return ArrayUtilities.contains(allowedSpellIds, spell.getId());
  }
}