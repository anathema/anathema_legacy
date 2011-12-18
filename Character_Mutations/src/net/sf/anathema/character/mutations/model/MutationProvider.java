package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook.SecondEdition;
import static net.sf.anathema.character.mutations.model.MutationType.Abomination;
import static net.sf.anathema.character.mutations.model.MutationType.Affliction;
import static net.sf.anathema.character.mutations.model.MutationType.Blight;
import static net.sf.anathema.character.mutations.model.MutationType.Debility;
import static net.sf.anathema.character.mutations.model.MutationType.Deficiency;
import static net.sf.anathema.character.mutations.model.MutationType.Deformity;
import static net.sf.anathema.character.mutations.model.MutationType.Pox;

public class MutationProvider {

  public static IMutation[] getMutations(IExaltedEdition edition, IMutationRules rules) {
    List<IMutation> mutations = null;
    List<IMutation> toRemove = new ArrayList<IMutation>();
    if (edition == ExaltedEdition.FirstEdition) {
      mutations = getFirstEditionMutations();
    }
    if (edition == ExaltedEdition.SecondEdition) {
      mutations = getSecondEditionMutations();
    }

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
    mutations.add("ShortLife", Deformity);
    mutations.add("SurrenderingFlesh", Deformity);
    mutations.add("Wracking", Deformity);
  }

  private static void addDebilities(Mutations mutations) {
    mutations.add("Blindness", Debility);
    mutations.add("Deterioration", Debility);
    mutations.add("Fragile", Debility);
    mutations.add("Lame", Debility);
    mutations.add("SlowHealing", Debility);
  }

  private static void addDeficiencies(Mutations mutations) {
    mutations.add("Atrophy", Deficiency);
    mutations.add("Allergy", Deficiency);
    mutations.add("Hungry", Deficiency);
    mutations.add("LostSenseHearing", Deficiency);
    mutations.add("LostSenseTouch", Deficiency);
    mutations.add("LostSenseSmellTaste", Deficiency);
    mutations.add("Rotundity", Deficiency);
    mutations.add("TemperatureSensitivity", Deficiency);
  }

  private static void addAbominations(Mutations mutations) {
    mutations.add("DragonsBreath", Abomination);
    mutations.add("Hive", Abomination);
    mutations.add("ExtraArmLegHead", Abomination);
    mutations.add("SerpentsBody", Abomination);
    mutations.add("SpiderLegs", Abomination);
    mutations.add("StoneBody", Abomination);
    mutations.add("TerrifyingMane", Abomination);
    mutations.add("Wings", Abomination);
  }

  private static void addBlights(Mutations mutations) {
    mutations.add("AcidicPustules", Blight);
    mutations.add("ArmoredHide", Blight);
    mutations.add("CheetahsPace", Blight);
    mutations.add("Glider", Blight);
    mutations.add("HideousMaw", Blight);
    mutations.add("LidlessDemonEye", Blight);
    mutations.add("PrehensileBodyHair", Blight);
    mutations.add("Quills", Blight);
    mutations.add("SerpentineHair", Blight);
    mutations.add("Tentacles", Blight);
    mutations.add("WallWalking", Blight);
  }

  private static void addAfflictions(Mutations mutations) {
    mutations.add("ChakraEye", Affliction);
    mutations.add("Chameleon", Affliction);
    mutations.add("ExaltedHealing", Affliction);
    mutations.add("FrogTongue", Affliction);
    mutations.add("GazellesPace", Affliction);
    mutations.add("Gills", Affliction);
    mutations.add("Huge", Affliction);
    mutations.add("ImpossibleJoints", Affliction);
    mutations.add("Inexhaustible", Affliction);
    mutations.add("ShortGestation", Affliction);
    mutations.add("PrehensileTail", Affliction);
    mutations.add("ScorpionsTail", Affliction);
    mutations.add("TalonsTusksHorns", Affliction);
    mutations.add("ThickSkin", Affliction);
    mutations.add("Toxin", Affliction);
    mutations.add("Tiny", Affliction);
    mutations.add("WyldAssimilation", Affliction);
  }

  private static void addPoxes(Mutations mutations) {
    mutations.add("EnhancedSense", Pox, SecondEdition, 288);
    mutations.add("Claws", Pox, SecondEdition, 288);
    mutations.add("Fangs", Pox);
    mutations.add("FurFeathersLeavesScales", Pox);
    mutations.add("Hooves", Pox);
    mutations.add("Large", Pox);
    mutations.add("Longevity", Pox);
    mutations.add("NightVision", Pox);
    mutations.add("SerpentineTongue", Pox);
    mutations.add("SkinHair", Pox);
    mutations.add("Small", Pox);
    mutations.add("Tail", Pox);
    mutations.add("ThirdEye", Pox);
    mutations.add("WolfsPace", Pox);
    mutations.add("ElementalAdaptationAir", Pox);
    mutations.add("ElementalAdaptationFire", Pox);
    mutations.add("ElementalAdaptationWood", Pox);
    mutations.add("ElementalAdaptationWater", Pox);
  }

  private static List<IMutation> getFirstEditionMutations() {
    return null;
  }
}
