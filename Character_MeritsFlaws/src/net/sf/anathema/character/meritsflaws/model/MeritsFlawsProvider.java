package net.sf.anathema.character.meritsflaws.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.MultiValuePerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.character.meritsflaws.model.perk.cost.FixedPerkCost;
import net.sf.anathema.character.meritsflaws.model.perk.evaluator.CasteEvaluator;
import net.sf.anathema.character.meritsflaws.model.perk.evaluator.CharacterTypeEvaluator;

public class MeritsFlawsProvider {

  public static IPerk[] getAllPerks() {
    IPerk pastLives = createPastLives();
    IPerk weakImmuneSystem = createWeakImmuneSystem();
    MultiValuePerk sterile = createSterile();
    MultiValuePerk daredevil = createDareDevil();
    MultiValuePerk prescientDreamer = createPrescientDreamer();
    MultiValuePerk mute = createMute();
    MultiValuePerk destiny = createDestiny();
    MultiValuePerk lucky = createLucky();
    MultiValuePerk sunSeared = new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "Sun-Seared", new int[] { //$NON-NLS-1$
        2, 3, 6 });
    MultiValuePerk amputee = createAmputee();
    MultiValuePerk tacticalInstincts = createTacticalInstincts();
    MultiValuePerk addiction = createAddiction();
    MultiValuePerk priest = createPriest();
    MultiValuePerk swornBrotherhood = createSwornBrotherHood();
    IPerk[] perks = new IPerk[] { new MultiValuePerk(PerkType.Merit, PerkCategory.Physical, "SelectiveConception", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Physical, "Ambidextrous", new int[] { 1, 2 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Physical, "Double-Jointed", new int[] { 1, 3 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Physical, "PainTolerance", new int[] { 3, 5, 7 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Physical, "LargeSize", new int[] { 4, 6 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Physical, "SpecialResistanceLimited", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Mental, "CommonSense", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Mental, "DrivingPassion", 3), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Mental, "TrueLove", 3), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Mental, "InternalCompass", 1), //$NON-NLS-1$
        tacticalInstincts,
        new MultiValuePerk(PerkType.Merit, PerkCategory.Mental, "JackAllTrades", 4), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Social, "BornToRule", 2), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Social, "EnchantingFeature", 2), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Social, "FavorMerit", new int[] { 1, 2, 3 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Social, "CelestialFavorMerit", new int[] { 2, 4, 6 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Property, "Heirloom", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Property, "LegendaryArtifact", 10), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Property, "HeirApparent", new int[] { 1, 2, 3, 4, 5 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "Innocuous", 4), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "TerrestrialBloodline", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "TaintsWarning", //$NON-NLS-1$
            new int[] { 2 },
            new CharacterType[] { CharacterType.SOLAR }),
        new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "TaintsWarning", //$NON-NLS-1$
            new int[] { 1 },
            new CharacterType[] { CharacterType.ABYSSAL }),
        new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "EternalVowMerit", //$NON-NLS-1$
            new int[] { 3 },
            new CharacterType[] { CharacterType.ABYSSAL, CharacterType.SOLAR, CharacterType.LUNAR }),
        swornBrotherhood,
        priest,
        destiny,
        lucky,
        pastLives,
        prescientDreamer,
        daredevil,
        mute,
        sterile,
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "OneEye", 2), //$NON-NLS-1$
        sunSeared,
        amputee,
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "Dying", new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 10 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "Small", 3), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "SlowHealing", 4), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "UnusualAppearance", new int[] { 1, 2 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "ClimateSensitive", new int[] { 2, 3, 4 }), //$NON-NLS-1$
        weakImmuneSystem,
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Supernatural, "Unlucky", new int[] { 1, 2, 3, 4, 5 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Supernatural, "DarkFate", new int[] { 1, 2, 3, 4, 5 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Supernatural, "UnbiddenOracle", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Supernatural, "EternalVowFlaw", //$NON-NLS-1$
            new int[] { 1 },
            new CharacterType[] { CharacterType.ABYSSAL, CharacterType.LUNAR }),
        createPermanentCastemark(),
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "Child", 3), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "Disturbing", new int[] { 2, 3 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "Barbarian", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "Disciple", new int[] { 1, 2, 3, 4, 5 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "EnemyRival", new int[] { 1, 2, 3, 4, 5 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "FavorFlaw", new int[] { 1, 2, 3 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "CelestialFavorFlaw", new int[] { 2, 4, 6 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "Secrets", new int[] { 1, 2, 3 }), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Social, "Wanted", new int[] { 1, 2, 3, 4, 5 }), //$NON-NLS-1$

        new MultiValuePerk(PerkType.Flaw, PerkCategory.Mental, "Pacifist", 1), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Mental, "Nightmares", 3), //$NON-NLS-1$
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Mental, "Superstition", new int[] { 1, 2, 3 }), //$NON-NLS-1$
        addiction,
        new MultiValuePerk(PerkType.Flaw, PerkCategory.Mental, "AmnesiaKnown", new int[] { 1, 2, 5 }) }; //$NON-NLS-1$
    return perks;
  }

  private static MultiValuePerk createPermanentCastemark() {
    MultiValuePerk permanentCastemark = new MultiValuePerk(
        PerkType.Flaw,
        PerkCategory.Supernatural,
        "PermanentCastemark", //$NON-NLS-1$
        new int[] { 2 },
        CharacterType.getCelestialExaltTypes());
    addOneCharacterTypeSpecialCost(permanentCastemark, CharacterType.SIDEREAL, new int[] { 1 });
    return permanentCastemark;
  }

  private static MultiValuePerk createSwornBrotherHood() {
    MultiValuePerk swornBrotherhood = new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "SwornBrotherhood", //$NON-NLS-1$
        new int[] { 2 },
        CharacterType.getAllExaltTypes());
    return swornBrotherhood;
  }

  private static MultiValuePerk createPriest() {
    MultiValuePerk priest = new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "Priest", new int[] { 1, 7 }); //$NON-NLS-1$
    int[] priestCost = new int[] { 6 };
    addOneCharacterTypeSpecialCost(priest, CharacterType.SIDEREAL, priestCost);
    priest.setSpecialFixedCost(new FixedPerkCost(new CasteEvaluator(new String[] { "Zenith", "NoMoon", "Midnight" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        priestCost));
    return priest;
  }

  private static MultiValuePerk createAddiction() {
    MultiValuePerk addiction = new MultiValuePerk(PerkType.Flaw, PerkCategory.Mental, "Addiction", new int[] { //$NON-NLS-1$
        1, 2, 3, 4, 5 });
    setMultiCharacterTypeSpecialCost(addiction, CharacterType.getAllExaltTypes(), new int[] { 1, 2, 3, 4 });
    return addiction;
  }

  private static MultiValuePerk createTacticalInstincts() {
    MultiValuePerk tacticalInstincts = new MultiValuePerk(PerkType.Merit, PerkCategory.Mental, "TacticalInstincts", 3); //$NON-NLS-1$
    tacticalInstincts.setSpecialFixedCost(new FixedPerkCost(new CasteEvaluator(
        new String[] { "Dawn", "Dusk", "Battles" }), new int[] { 2 })); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return tacticalInstincts;
  }

  private static MultiValuePerk createAmputee() {
    MultiValuePerk amputee = new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "Amputee", new int[] { //$NON-NLS-1$
        2, 3, 4, 5, 6, 7, 8 });
    setMultiCharacterTypeSpecialCost(amputee, CharacterType.getAllExaltTypes(), new int[] { 1, 2, 3, 4, 5, 6 });
    return amputee;
  }

  private static MultiValuePerk createLucky() {
    MultiValuePerk lucky = new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "Lucky", new int[] { //$NON-NLS-1$
        1, 2, 3, 4, 5 });
    addOneCharacterTypeSpecialCost(lucky, CharacterType.SIDEREAL, new int[] { 2 });
    return lucky;
  }

  private static MultiValuePerk createDestiny() {
    MultiValuePerk destiny = new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "Destiny", new int[] { //$NON-NLS-1$
        1, 2, 3, 4, 5 });
    setMultiCharacterTypeSpecialCost(destiny, CharacterType.getCelestialExaltTypes(), new int[] { 1, 2 });
    return destiny;
  }

  private static MultiValuePerk createMute() {
    MultiValuePerk mute = new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "Mute", new int[] { 1, 3, 4 }); //$NON-NLS-1$
    setMultiCharacterTypeSpecialCost(mute, CharacterType.getAllExaltTypes(), new int[] { 2, 3 });
    return mute;
  }

  private static IPerk createPastLives() {
    IPerk pastLives = createCharacterTypeLimitedPerk(PerkType.Merit, PerkCategory.Supernatural, "PastLives", new int[] { //$NON-NLS-1$
        1, 2, 3, 4, 5 },
        new CharacterType[] { CharacterType.SIDEREAL });
    setMultiCharacterTypeSpecialCost(
        pastLives,
        new CharacterType[] { CharacterType.DB, CharacterType.MORTAL },
        new int[] { 1, 2 });
    return pastLives;
  }

  private static MultiValuePerk createPrescientDreamer() {
    MultiValuePerk prescientDreamer = new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "PrescientDreamer", //$NON-NLS-1$
        3);
    addOneCharacterTypeSpecialCost(prescientDreamer, CharacterType.SIDEREAL, new int[] { 2 });
    return prescientDreamer;
  }

  private static MultiValuePerk createDareDevil() {
    MultiValuePerk daredevil = new MultiValuePerk(PerkType.Merit, PerkCategory.Supernatural, "Daredevil", 4); //$NON-NLS-1$
    addOneCharacterTypeSpecialCost(daredevil, CharacterType.MORTAL, new int[] { 3 });
    return daredevil;
  }

  private static void addOneCharacterTypeSpecialCost(IPerk perk, CharacterType type, int[] cost) {
    perk.setSpecialFixedCost(new FixedPerkCost(new CharacterTypeEvaluator(new CharacterType[] { type }), cost));
  }

  private static void setMultiCharacterTypeSpecialCost(IPerk perk, CharacterType[] types, int[] cost) {
    perk.setSpecialFixedCost(new FixedPerkCost(new CharacterTypeEvaluator(types), cost));
  }

  private static MultiValuePerk createSterile() {
    MultiValuePerk sterile = new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "Sterile", new int[] { 1, 2, 3 }); //$NON-NLS-1$
    addOneCharacterTypeSpecialCost(sterile, CharacterType.DB, new int[] { 3 });
    return sterile;
  }

  private static IPerk createWeakImmuneSystem() {
    IPerk weakImmuneSystem = new MultiValuePerk(PerkType.Flaw, PerkCategory.Physical, "WeakImmuneSystem", 4); //$NON-NLS-1$
    setMultiCharacterTypeSpecialCost(weakImmuneSystem, CharacterType.getAllExaltTypes(), new int[] { 3 });
    return weakImmuneSystem;
  }

  private static IPerk createCharacterTypeLimitedPerk(
      PerkType type,
      PerkCategory category,
      String id,
      int[] pointValues,
      CharacterType[] bannedTypes) {
    List<CharacterType> allowedTypes = new ArrayList<CharacterType>();
    Collections.addAll(allowedTypes, CharacterType.getAllCharacterTypes());
    allowedTypes.removeAll(Arrays.asList(bannedTypes));
    return new MultiValuePerk(
        type,
        category,
        id,
        pointValues,
        allowedTypes.toArray(new CharacterType[allowedTypes.size()]));
  }
}