package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.impl.rules.SourceBook;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.mutations.model.MutationType.Abomination;
import static net.sf.anathema.character.mutations.model.MutationType.Affliction;
import static net.sf.anathema.character.mutations.model.MutationType.Blight;
import static net.sf.anathema.character.mutations.model.MutationType.Debility;
import static net.sf.anathema.character.mutations.model.MutationType.Deficiency;
import static net.sf.anathema.character.mutations.model.MutationType.Deformity;
import static net.sf.anathema.character.mutations.model.MutationType.Pox;

public class MutationProvider {

  public static final IExaltedSourceBook Infernals = new SourceBook("Infernals");
  public static final IExaltedSourceBook CompassEast = new SourceBook("CompassEast");
  public static final IExaltedSourceBook CompassWest = new SourceBook("CompassWest");
  public static final IExaltedSourceBook CompassWyld = new SourceBook("CompassWyld");
  public static final IExaltedSourceBook LandsOfCreation = new SourceBook("LandsOfCreation");
  public static final IExaltedSourceBook Lunars2nd = new SourceBook("Lunars2nd");
  public static final IExaltedSourceBook SecondEdition = new SourceBook("SecondEdition");

  public static IMutation[] getMutations(IMutationRules rules) {
    final List<IMutation> mutations = newArrayList();
    mutations.addAll(getSecondEditionMutations());
    List<IMutation> toRemove = new ArrayList<IMutation>();
    for (IMutation mutation : mutations) {
      if (!rules.acceptMutation(mutation)) {
        toRemove.add(mutation);
      }
    }
    mutations.removeAll(toRemove);
    return mutations.toArray(new IMutation[mutations.size()]);
  }

  private static List<IMutation> getSecondEditionMutations() {
    Mutations mutations = new Mutations();
    addPoxes(mutations);
    addAfflictions(mutations);
    addBlights(mutations);
    addAbominations(mutations);
    addDeficiencies(mutations);
    addDebilities(mutations);
    addDeformities(mutations);
    return mutations.asList();
  }

  private static void addDeformities(Mutations mutations) {
    mutations.add("AuraOfPower", Deformity, Infernals, 63);
    mutations.add("CreatureOfDarkness", Deformity, Infernals, 64);
    mutations.add("Decomposing", Deformity, SecondEdition, 289);
    mutations.add("MagicalPlagueCarrier", Deformity, Lunars2nd, 209);
    mutations.add("OngoingMutation", Deformity, LandsOfCreation, 131);
    mutations.add("PlagueCarrier", Deformity, SecondEdition, 290);
    mutations.add("ShortLife", Deformity, CompassWyld, 148);
    mutations.add("SurrenderingFlesh", Deformity, CompassWyld, 148);
    mutations.add("WalkingBlasphemy", Deformity, Infernals, 64);
    mutations.add("Wracking", Deformity, CompassWyld, 148);
  }

  private static void addDebilities(Mutations mutations) {
    mutations.add("Blindness", Debility, CompassWyld, 147);
    mutations.add("Deterioration", Debility, CompassWyld, 147);
    mutations.add("Delusion", Debility, CompassWyld, 149);
    mutations.add("Diet", Debility, SecondEdition, 289);
    mutations.add("EyesOfWickedMadness", Debility, Infernals, 64);
    mutations.add("Fragile", Debility, CompassWyld, 147);
    mutations.add("HeartsBloodAddiction", Debility, Lunars2nd, 208);
    mutations.add("Lame", Debility, CompassWyld, 147);
    mutations.add("SlowHealing", Debility, CompassWyld, 147);
    mutations.add("WyldAddiction", Debility, SecondEdition, 289);
  }

  private static void addDeficiencies(Mutations mutations) {
    mutations.add("Allergy", Deficiency, CompassWyld, 146);
    mutations.add("Atrophy", Deficiency, SecondEdition, 288);
    mutations.add("DisgustingScent", Deficiency, Lunars2nd, 207);
    mutations.add("DisturbingVoice", Deficiency, Lunars2nd, 207);
    mutations.add("Hungry", Deficiency, CompassWyld, 146);
    mutations.add("LostSenseHearing", Deficiency, CompassWyld, 146);
    mutations.add("LostSenseTouch", Deficiency, CompassWyld, 146);
    mutations.add("LostSenseSmellTaste", Deficiency, CompassWyld, 146);
    mutations.add("MoodSwings", Deficiency, SecondEdition, 288);
    mutations.add("Rotundity", Deficiency, CompassWyld, 146);
    mutations.add("SecondMouth", Deficiency, Lunars2nd, 207);
    mutations.add("TemperatureSensitivity", Deficiency, CompassWyld, 146);
    mutations.add("Ugly", Deficiency, SecondEdition, 289);
  }

  private static void addAbominations(Mutations mutations) {
    mutations.add("AwakenedEssence", Abomination, Infernals, 64);
    mutations.add("DragonsBreath", Abomination, CompassWyld, 148);
    mutations.add("FishBody", Abomination, CompassWest, 152);
    mutations.add("FogCarrier", Abomination, CompassWest, 152);
    mutations.add("Gargantuan", Abomination, LandsOfCreation, 131);
    mutations.add("Hive", Abomination, SecondEdition, 290);
    mutations.add("ImmortalFlesh", Abomination, LandsOfCreation, 131);
    mutations.add("ExtraArmLegHead", Abomination, SecondEdition, 290);
    mutations.add("OrdinationOfLies", Abomination, Infernals, 64);
    mutations.add("OrdinationOfPain", Abomination, Infernals, 64);
    mutations.add("SerpentsBody", Abomination, Lunars2nd, 209);
    mutations.add("SpiderLegs", Abomination, Lunars2nd, 209);
    mutations.add("StoneBody", Abomination, CompassWyld, 148);
    mutations.add("TerrifyingMane", Abomination, Lunars2nd, 209);
    mutations.add("Wings", Abomination, SecondEdition, 290);
  }

  private static void addBlights(Mutations mutations) {
    mutations.add("AcidicPustules", Blight, Lunars2nd, 208);
    mutations.add("ArmoredHide", Blight, SecondEdition, 289);
    mutations.add("CheetahsPace", Blight, Lunars2nd, 208);
    mutations.add("EnlightenedEssence", Blight, CompassWyld, 148);
    mutations.add("Giant", Blight, CompassWyld, 148);
    mutations.add("Glider", Blight, SecondEdition, 289);
    mutations.add("HideousMaw", Blight, Lunars2nd, 208);
    mutations.add("LidlessDemonEye", Blight, Lunars2nd, 208);
    mutations.add("Miniscule", Blight, CompassWyld, 148);
    mutations.add("PrehensileBodyHair", Blight, Lunars2nd, 208);
    mutations.add("Quills", Blight, SecondEdition, 289);
    mutations.add("SerpentineHair", Blight, Lunars2nd, 208);
    mutations.add("Tentacles", Blight, SecondEdition, 289);
    mutations.add("WallWalking", Blight, SecondEdition, 289);
  }

  private static void addAfflictions(Mutations mutations) {
    mutations.add("Brachiation", Affliction, CompassEast, 149);
    mutations.add("ChakraEye", Affliction, Lunars2nd, 207);
    mutations.add("Chameleon", Affliction, SecondEdition, 289);
    mutations.add("ExaltedHealing", Affliction, CompassWyld, 146);
    mutations.add("EyesOfWickedMadness", Affliction, Infernals, 64);
    mutations.add("FrogTongue", Affliction, SecondEdition, 289);
    mutations.add("GazellesPace", Affliction, Lunars2nd, 207);
    mutations.add("Gills", Affliction, SecondEdition, 289);
    mutations.add("GreatHooves", Affliction, Lunars2nd, 207);
    mutations.add("Huge", Affliction, CompassWyld, 146);
    mutations.add("ImpossibleJoints", Affliction, CompassWyld, 146);
    mutations.add("Inexhaustible", Affliction, CompassWyld, 146);
    mutations.add("MagicalAttunement", Affliction, Infernals, 64);
    mutations.add("MarkOfInfernalFavor", Affliction, Infernals, 64);
    mutations.add("Omnidexterity", Affliction, CompassEast, 149);
    mutations.add("PrehensileTail", Affliction, SecondEdition, 289);
    mutations.add("ScorpionsTail", Affliction, Lunars2nd, 207);
    mutations.add("SharkSight", Affliction, CompassWest, 152);
    mutations.add("ShortGestation", Affliction, CompassWyld, 147);
    mutations.add("TalonsTusksHorns", Affliction, Lunars2nd, 208);
    mutations.add("ThickSkin", Affliction, Lunars2nd, 208);
    mutations.add("Tiny", Affliction, CompassWyld, 147);
    mutations.add("Toxin", Affliction, SecondEdition, 289);
    mutations.add("WyldAssimilation", Affliction, CompassWyld, 147);
  }

  private static void addPoxes(Mutations mutations) {
    mutations.add("BioLuminescence", Pox, CompassWest, 152);
    mutations.add("BlurredFate", Pox, Infernals, 63);
    mutations.add("Brachiation", Pox, CompassEast, 149);
    mutations.add("ChangingColoration", Pox, CompassWest, 152);
    mutations.add("Claws", Pox, SecondEdition, 288);
    mutations.add("EnhancedSense", Pox, SecondEdition, 288);
    mutations.add("Fangs", Pox, SecondEdition, 288);
    mutations.add("ElementalAdaptationAir", Pox, CompassWyld, 145);
    mutations.add("ElementalAdaptationFire", Pox, CompassWyld, 145);
    mutations.add("ElementalAdaptationWater", Pox, CompassWyld, 145);
    mutations.add("ElementalAdaptationWood", Pox, CompassWyld, 145);
    mutations.add("EnhancedSensesFogsense", Pox, CompassWest, 152);
    mutations.add("FurFeathersLeavesScales", Pox, SecondEdition, 288);
    mutations.add("Gatekeeper", Pox, Infernals, 64);
    mutations.add("Hooves", Pox, Lunars2nd, 207);
    mutations.add("KinSense", Pox, LandsOfCreation, 131);
    mutations.add("Large", Pox, CompassWyld, 144);
    mutations.add("Longevity", Pox, CompassWyld, 144);
    mutations.add("MouthTentacles", Pox, CompassWest, 152);
    mutations.add("NightVision", Pox, CompassWyld, 144);
    mutations.add("SerpentineTongue", Pox, Lunars2nd, 207);
    mutations.add("SkinHair", Pox, SecondEdition, 288);
    mutations.add("Small", Pox, CompassWyld, 144);
    mutations.add("Sonar", Pox, CompassWest, 152);
    mutations.add("Tail", Pox, SecondEdition, 288);
    mutations.add("ThirdEye", Pox, Lunars2nd, 207);
    mutations.add("WolfsPace", Pox, Lunars2nd, 207);
  }
}