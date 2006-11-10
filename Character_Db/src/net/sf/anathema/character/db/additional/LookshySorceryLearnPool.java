package net.sf.anathema.character.db.additional;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.magic.ISpell;

public class LookshySorceryLearnPool extends AbstractSorceryLearnPool {

  String[] allowedSpellIds = new String[] { "Terrestrial.DeathObsidianButterflies",//$NON-NLS-1$
      "Terrestrial.EmeraldCountermagic",//$NON-NLS-1$
      "Terrestrial.ImpenetrableFrostBarrier",//$NON-NLS-1$
      "Terrestrial.InfallibleMessenger",//$NON-NLS-1$
      "Terrestrial.InvulnerableSkinBronze",//$NON-NLS-1$
      "Terrestrial.StormwindRider",//$NON-NLS-1$
      "Terrestrial.CirrusSkiff",//$NON-NLS-1$
      "Terrestrial.CoinDistantVision",//$NON-NLS-1$
      "Terrestrial.CommandingPresenceFire",//$NON-NLS-1$
      "Terrestrial.CommandingBeasts",//$NON-NLS-1$
      "Terrestrial.DanceSmokeCobras",//$NON-NLS-1$
      "Terrestrial.DisguiseNewFace",//$NON-NLS-1$
      "Terrestrial.DragonSmokeSerpentFlame",//$NON-NLS-1$
      "Terrestrial.FlightBrilliantRaptor",//$NON-NLS-1$
      "Terrestrial.FlyingGuillotine",//$NON-NLS-1$
      "Terrestrial.IncantationEffectiveRestoration",//$NON-NLS-1$
      "Terrestrial.Magician'sPleasantPath",//$NON-NLS-1$
      "Terrestrial.MirrorBendingLight",//$NON-NLS-1$
      "Terrestrial.ParalyzingContradiction",//$NON-NLS-1$
      "Terrestrial.PlagueBronzeSnakes",//$NON-NLS-1$
      "Terrestrial.PurifyingFlames",//$NON-NLS-1$
      "Terrestrial.RitualElementalEmpowerment",//$NON-NLS-1$
      "Terrestrial.ShadowSummons",//$NON-NLS-1$
      "Terrestrial.SongWaves",//$NON-NLS-1$
      "Terrestrial.SpiritMight",//$NON-NLS-1$
      "Terrestrial.SpiritSword",//$NON-NLS-1$
      "Terrestrial.SpokeWoodenFace",//$NON-NLS-1$
      "Terrestrial.SproutingShacklesDoom",//$NON-NLS-1$
      "Terrestrial.StalwartEarthGuardian",//$NON-NLS-1$
      "Terrestrial.StingIceHornet",//$NON-NLS-1$
      "Terrestrial.ThunderWolf'sHowl",//$NON-NLS-1$
      "Terrestrial.TongueElementSpirit",//$NON-NLS-1$
      "Terrestrial.UnconquerableSelf",//$NON-NLS-1$
      "Terrestrial.UnstoppableFountainsDepths",//$NON-NLS-1$
      "Terrestrial.ViolentOpeningClosedPortals",//$NON-NLS-1$
      "Terrestrial.VirtuousGuardianFlame",//$NON-NLS-1$
      "Terrestrial.WaterFromStone",//$NON-NLS-1$
      "Terrestrial.WrittenUponWater",//$NON-NLS-1$
      "Terrestrial.PeacockShadowEyes",//$NON-NLS-1$
      "Terrestrial.CallingWind'sKiss",//$NON-NLS-1$
      "Terrestrial.Mast-ShatteringSpell",//$NON-NLS-1$
      "Terrestrial.CallingStalwartServitor",//$NON-NLS-1$
      "Terrestrial.DemonFirstCircle",//$NON-NLS-1$
      "Terrestrial.LesserGolem",//$NON-NLS-1$
      "Terrestrial.OpenSpiritDoor",//$NON-NLS-1$
      "Terrestrial.SummonElemental" };//$NON-NLS-1$

  public LookshySorceryLearnPool(IBackgroundTemplate sorceryTemplate) {
    super(sorceryTemplate);
  }

  @Override
  protected boolean isTerrestrialSpellAllowed(ISpell spell) {
    return ArrayUtilities.contains(allowedSpellIds, spell.getId());
  }
}