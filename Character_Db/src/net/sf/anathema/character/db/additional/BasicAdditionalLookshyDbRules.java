package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.module.IBackgroundIds;
import net.sf.anathema.character.generic.framework.xml.rules.TraitTypeAdapter;
import net.sf.anathema.character.generic.impl.additional.AdditionalEssencePool;
import net.sf.anathema.character.generic.impl.additional.BackgroundPool;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.DefaultTraitCostModifier;
import net.sf.anathema.character.generic.impl.additional.GenericMagicLearnPool;
import net.sf.anathema.character.generic.traits.ITraitType;

public class BasicAdditionalLookshyDbRules extends DefaultAdditionalRules {

  private static final String[] allowedSpellIds = new String[] { "Terrestrial.DeathObsidianButterflies",//$NON-NLS-1$
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

  public BasicAdditionalLookshyDbRules() {
    super(
        IBackgroundIds.BACKGROUND_ID_CONTACTS,
        IBackgroundIds.BACKGROUND_ID_INFLUENCE,
        IBackgroundIds.BACKGROUND_ID_FOLLOWERS,
        DbCharacterModule.BACKGROUND_ID_HENCHMEN);
  }

  public void addSorceryRules(IBackgroundTemplate sorceryTemplate) {
    GenericMagicLearnPool pool = new GenericMagicLearnPool(sorceryTemplate, false);
    for (String id : allowedSpellIds) {
      pool.addIdException(id);
    }
    addMagicLearnPool(pool);
  }

  public void addBreedingRules(IBackgroundTemplate breedingTemplate) {
    AdditionalEssencePool peripheralPool = new AdditionalEssencePool(0);
    peripheralPool.setFixedValue(0, 0);
    peripheralPool.setFixedValue(1, 2);
    peripheralPool.setFixedValue(2, 3);
    peripheralPool.setFixedValue(3, 5);
    peripheralPool.setFixedValue(4, 7);
    peripheralPool.setFixedValue(5, 9);
    peripheralPool.setFixedValue(6, 11);
    addEssencePool(new BackgroundPool(breedingTemplate, new AdditionalEssencePool(1), peripheralPool));
  }

  @Override
  public ITraitCostModifier getCostModifier(ITraitType type) {
    final ITraitCostModifier[] modifier = new ITraitCostModifier[] { super.getCostModifier(type) };
    type.accept(new TraitTypeAdapter() {
      @Override
      public void visitBackground(IBackgroundTemplate template) {
        if (template.getId().equals(DbCharacterModule.BACKGROUND_ID_BREEDING)) {
          modifier[0] = new DefaultTraitCostModifier() {
            @Override
            public int getAdditionalDotsToSpend(int traitValue) {
              return Math.max(0, traitValue - 2);
            }
          };
        }
      }
    });
    return modifier[0];
  }
}