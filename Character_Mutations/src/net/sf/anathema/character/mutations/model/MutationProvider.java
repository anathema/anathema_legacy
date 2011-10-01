package net.sf.anathema.character.mutations.model;

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
	  mutations.add(new Mutation("EnhancedSense", MutationType.Pox, ExaltedSourceBook.SecondEdition, 288));
	  mutations.add(new Mutation("Claws", MutationType.Pox, ExaltedSourceBook.SecondEdition, 288));
	  mutations.add(new Mutation("Fangs", MutationType.Pox));
	  mutations.add(new Mutation("FurFeathersLeavesScales", MutationType.Pox));
	  mutations.add(new Mutation("Hooves", MutationType.Pox));
	  mutations.add(new Mutation("Large", MutationType.Pox));
	  mutations.add(new Mutation("Longevity", MutationType.Pox));
	  mutations.add(new Mutation("NightVision", MutationType.Pox));
	  mutations.add(new Mutation("SerpentineTongue", MutationType.Pox));
	  mutations.add(new Mutation("SkinHair", MutationType.Pox));
	  mutations.add(new Mutation("Small", MutationType.Pox));
	  mutations.add(new Mutation("Tail", MutationType.Pox));
	  mutations.add(new Mutation("ThirdEye", MutationType.Pox));
	  mutations.add(new Mutation("WolfsPace", MutationType.Pox));
	  mutations.add(new Mutation("ElementalAdaptationAir", MutationType.Pox));
	  mutations.add(new Mutation("ElementalAdaptationFire", MutationType.Pox));
	  mutations.add(new Mutation("ElementalAdaptationWood", MutationType.Pox));
	  mutations.add(new Mutation("ElementalAdaptationWater", MutationType.Pox));
	  mutations.add(new Mutation("ChakraEye", MutationType.Affliction));
	  mutations.add(new Mutation("Chameleon", MutationType.Affliction));
	  mutations.add(new Mutation("ExaltedHealing", MutationType.Affliction));
	  mutations.add(new Mutation("FrogTongue", MutationType.Affliction));
	  mutations.add(new Mutation("GazellesPace", MutationType.Affliction));
	  mutations.add(new Mutation("Gills", MutationType.Affliction));
	  mutations.add(new Mutation("Huge", MutationType.Affliction));
	  mutations.add(new Mutation("ImpossibleJoints", MutationType.Affliction));
	  mutations.add(new Mutation("Inexhaustible", MutationType.Affliction));
	  mutations.add(new Mutation("ShortGestation", MutationType.Affliction));
	  mutations.add(new Mutation("PrehensileTail", MutationType.Affliction));
	  mutations.add(new Mutation("ScorpionsTail", MutationType.Affliction));
	  mutations.add(new Mutation("TalonsTusksHorns", MutationType.Affliction));
	  mutations.add(new Mutation("ThickSkin", MutationType.Affliction));
	  mutations.add(new Mutation("Toxin", MutationType.Affliction));
	  mutations.add(new Mutation("Tiny", MutationType.Affliction));
	  mutations.add(new Mutation("WyldAssimilation", MutationType.Affliction));
	  mutations.add(new Mutation("AcidicPustules", MutationType.Blight));
	  mutations.add(new Mutation("ArmoredHide", MutationType.Blight));
	  mutations.add(new Mutation("CheetahsPace", MutationType.Blight));
	  mutations.add(new Mutation("Glider", MutationType.Blight));
	  mutations.add(new Mutation("HideousMaw", MutationType.Blight));
	  mutations.add(new Mutation("LidlessDemonEye", MutationType.Blight));
	  mutations.add(new Mutation("PrehensileBodyHair", MutationType.Blight));
	  mutations.add(new Mutation("Quills", MutationType.Blight));
	  mutations.add(new Mutation("SerpentineHair", MutationType.Blight));
	  mutations.add(new Mutation("Tentacles", MutationType.Blight));
	  mutations.add(new Mutation("WallWalking", MutationType.Blight));
	  mutations.add(new Mutation("DragonsBreath", MutationType.Abomination));
	  mutations.add(new Mutation("Hive", MutationType.Abomination));
	  mutations.add(new Mutation("ExtraArmLegHead", MutationType.Abomination));
	  mutations.add(new Mutation("SerpentsBody", MutationType.Abomination));
	  mutations.add(new Mutation("SpiderLegs", MutationType.Abomination));
	  mutations.add(new Mutation("StoneBody", MutationType.Abomination));
	  mutations.add(new Mutation("TerrifyingMane", MutationType.Abomination));
	  mutations.add(new Mutation("Wings", MutationType.Abomination));

	  mutations.add(new Mutation("Atrophy", MutationType.Deficiency));
	  mutations.add(new Mutation("Allergy", MutationType.Deficiency));
	  mutations.add(new Mutation("Hungry", MutationType.Deficiency));
	  mutations.add(new Mutation("LostSenseHearing", MutationType.Deficiency));
	  mutations.add(new Mutation("LostSenseTouch", MutationType.Deficiency));
	  mutations.add(new Mutation("LostSenseSmellTaste", MutationType.Deficiency));
	  mutations.add(new Mutation("Rotundity", MutationType.Deficiency));
	  mutations.add(new Mutation("TemperatureSensitivity", MutationType.Deficiency));
	  mutations.add(new Mutation("Blindness", MutationType.Debility));
	  mutations.add(new Mutation("Deterioration", MutationType.Debility));
	  mutations.add(new Mutation("Fragile", MutationType.Debility));
	  mutations.add(new Mutation("Lame", MutationType.Debility));
	  mutations.add(new Mutation("SlowHealing", MutationType.Debility));
	  mutations.add(new Mutation("ShortLife", MutationType.Deformity));
	  mutations.add(new Mutation("SurrenderingFlesh", MutationType.Deformity));
	  mutations.add(new Mutation("Wracking", MutationType.Deformity));
	  
	  return mutations;
  }
  
  private static List<IMutation> getFirstEditionMutations()
  {
	  return null;
  }
}