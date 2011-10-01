package net.sf.anathema.character.mutations.model;

import static net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook.SecondEdition;
import static net.sf.anathema.character.mutations.model.MutationType.Abomination;
import static net.sf.anathema.character.mutations.model.MutationType.Affliction;
import static net.sf.anathema.character.mutations.model.MutationType.Blight;
import static net.sf.anathema.character.mutations.model.MutationType.Debility;
import static net.sf.anathema.character.mutations.model.MutationType.Deficiency;
import static net.sf.anathema.character.mutations.model.MutationType.Deformity;
import static net.sf.anathema.character.mutations.model.MutationType.Pox;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class MutationProvider {
	
  public static IMutation[] getMutations(IExaltedEdition edition, IMutationRules rules)
  {
	  List<IMutation> mutations = null;
	  List<IMutation> toRemove = new ArrayList<IMutation>();
	  if (edition == ExaltedEdition.FirstEdition)
		  mutations = getFirstEditionMutations();
	  if (edition == ExaltedEdition.SecondEdition)
		  mutations = getSecondEditionMutations();
	  
	  for (IMutation mutation : mutations)
		  if (!rules.acceptMutation(mutation))
			  toRemove.add(mutation);
	  
	  mutations.removeAll(toRemove);
	  
	  return mutations.toArray(new IMutation[mutations.size()]);
  }
  
  private static List<IMutation> getSecondEditionMutations()
  {
	  List<IMutation> mutations = new ArrayList<IMutation>();
	  mutations.add(new Mutation("EnhancedSense", Pox, SecondEdition, 288));
	  mutations.add(new Mutation("Claws", Pox, SecondEdition, 288));
	  mutations.add(new Mutation("Fangs", Pox));
	  mutations.add(new Mutation("FurFeathersLeavesScales", Pox));
	  mutations.add(new Mutation("Hooves", Pox));
	  mutations.add(new Mutation("Large", Pox));
	  mutations.add(new Mutation("Longevity", Pox));
	  mutations.add(new Mutation("NightVision", Pox));
	  mutations.add(new Mutation("SerpentineTongue", Pox));
	  mutations.add(new Mutation("SkinHair", Pox));
	  mutations.add(new Mutation("Small", Pox));
	  mutations.add(new Mutation("Tail", Pox));
	  mutations.add(new Mutation("ThirdEye", Pox));
	  mutations.add(new Mutation("WolfsPace", Pox));
	  mutations.add(new Mutation("ElementalAdaptationAir", Pox));
	  mutations.add(new Mutation("ElementalAdaptationFire", Pox));
	  mutations.add(new Mutation("ElementalAdaptationWood", Pox));
	  mutations.add(new Mutation("ElementalAdaptationWater", Pox));
	  mutations.add(new Mutation("ChakraEye", Affliction));
	  mutations.add(new Mutation("Chameleon", Affliction));
	  mutations.add(new Mutation("ExaltedHealing", Affliction));
	  mutations.add(new Mutation("FrogTongue", Affliction));
	  mutations.add(new Mutation("GazellesPace", Affliction));
	  mutations.add(new Mutation("Gills", Affliction));
	  mutations.add(new Mutation("Huge", Affliction));
	  mutations.add(new Mutation("ImpossibleJoints", Affliction));
	  mutations.add(new Mutation("Inexhaustible", Affliction));
	  mutations.add(new Mutation("ShortGestation", Affliction));
	  mutations.add(new Mutation("PrehensileTail", Affliction));
	  mutations.add(new Mutation("ScorpionsTail", Affliction));
	  mutations.add(new Mutation("TalonsTusksHorns", Affliction));
	  mutations.add(new Mutation("ThickSkin", Affliction));
	  mutations.add(new Mutation("Toxin", Affliction));
	  mutations.add(new Mutation("Tiny", Affliction));
	  mutations.add(new Mutation("WyldAssimilation", Affliction));
	  mutations.add(new Mutation("AcidicPustules", Blight));
	  mutations.add(new Mutation("ArmoredHide", Blight));
	  mutations.add(new Mutation("CheetahsPace", Blight));
	  mutations.add(new Mutation("Glider", Blight));
	  mutations.add(new Mutation("HideousMaw", Blight));
	  mutations.add(new Mutation("LidlessDemonEye", Blight));
	  mutations.add(new Mutation("PrehensileBodyHair", Blight));
	  mutations.add(new Mutation("Quills", Blight));
	  mutations.add(new Mutation("SerpentineHair", Blight));
	  mutations.add(new Mutation("Tentacles", Blight));
	  mutations.add(new Mutation("WallWalking", Blight));
	  mutations.add(new Mutation("DragonsBreath", Abomination));
	  mutations.add(new Mutation("Hive", Abomination));
	  mutations.add(new Mutation("ExtraArmLegHead", Abomination));
	  mutations.add(new Mutation("SerpentsBody", Abomination));
	  mutations.add(new Mutation("SpiderLegs", Abomination));
	  mutations.add(new Mutation("StoneBody", Abomination));
	  mutations.add(new Mutation("TerrifyingMane", Abomination));
	  mutations.add(new Mutation("Wings", Abomination));

	  mutations.add(new Mutation("Atrophy", Deficiency));
	  mutations.add(new Mutation("Allergy", Deficiency));
	  mutations.add(new Mutation("Hungry", Deficiency));
	  mutations.add(new Mutation("LostSenseHearing", Deficiency));
	  mutations.add(new Mutation("LostSenseTouch", Deficiency));
	  mutations.add(new Mutation("LostSenseSmellTaste", Deficiency));
	  mutations.add(new Mutation("Rotundity", Deficiency));
	  mutations.add(new Mutation("TemperatureSensitivity", Deficiency));
	  mutations.add(new Mutation("Blindness", Debility));
	  mutations.add(new Mutation("Deterioration", Debility));
	  mutations.add(new Mutation("Fragile", Debility));
	  mutations.add(new Mutation("Lame", Debility));
	  mutations.add(new Mutation("SlowHealing", Debility));
	  mutations.add(new Mutation("ShortLife", Deformity));
	  mutations.add(new Mutation("SurrenderingFlesh", Deformity));
	  mutations.add(new Mutation("Wracking", Deformity));
	  
	  return mutations;
  }
  
  private static List<IMutation> getFirstEditionMutations()
  {
	  return null;
  }
}