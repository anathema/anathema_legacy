package net.sf.anathema.character.mutations.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class MutationProvider {

  public static IMutation[] getAllMutations(IExaltedEdition edition)
  {
	  if (edition == ExaltedEdition.FirstEdition)
		  return getFirstEditionMutations();
	  if (edition == ExaltedEdition.SecondEdition)
		  return getSecondEditionMutations();
	  return null;
  }
  
  private static IMutation[] getSecondEditionMutations()
  {
	  List<IMutation> Mutations = new ArrayList<IMutation>();
	  Mutations.add(new Mutation("EnhancedSense", 1));
	  Mutations.add(new Mutation("Claws", 1));
	  Mutations.add(new Mutation("Fangs", 1));
	  Mutations.add(new Mutation("FurFeathersLeavesScales", 1));
	  Mutations.add(new Mutation("Hooves", 1));
	  Mutations.add(new Mutation("SerpentineTongue", 1));
	  Mutations.add(new Mutation("SkinHair", 1));
	  Mutations.add(new Mutation("Small", 1));
	  Mutations.add(new Mutation("Tail", 1));
	  Mutations.add(new Mutation("ThirdEye", 1));
	  Mutations.add(new Mutation("WolfsPace", 1));
	  Mutations.add(new Mutation("ChakraEye", 2));
	  Mutations.add(new Mutation("Chameleon", 2));
	  Mutations.add(new Mutation("FrogTongue", 2));
	  Mutations.add(new Mutation("GazellesPace", 2));
	  Mutations.add(new Mutation("Gills", 2));
	  Mutations.add(new Mutation("PrehensileTail", 2));
	  Mutations.add(new Mutation("ScorpionsTail", 2));
	  Mutations.add(new Mutation("TalonsTusksHorns", 2));
	  Mutations.add(new Mutation("ThickSkin", 2));
	  Mutations.add(new Mutation("Toxin", 2));
	  Mutations.add(new Mutation("AcidicPustules", 4));
	  Mutations.add(new Mutation("ArmoredHide", 4));
	  Mutations.add(new Mutation("CheetahsPace", 4));
	  Mutations.add(new Mutation("Glider", 4));
	  Mutations.add(new Mutation("HideousMaw", 4));
	  Mutations.add(new Mutation("LidlessDemonEye", 4));
	  Mutations.add(new Mutation("PrehensileBodyHair", 4));
	  Mutations.add(new Mutation("Quills", 4));
	  Mutations.add(new Mutation("SerpentineHair", 4));
	  Mutations.add(new Mutation("Tentacles", 4));
	  Mutations.add(new Mutation("WallWalking", 4));
	  Mutations.add(new Mutation("Hive", 6));
	  Mutations.add(new Mutation("ExtraArmLegHead", 6));
	  Mutations.add(new Mutation("SerpentsBody", 6));
	  Mutations.add(new Mutation("SpiderLegs", 6));
	  Mutations.add(new Mutation("TerrifyingMane", 6));
	  Mutations.add(new Mutation("Wings", 6));
	  
	  return Mutations.toArray(new IMutation[Mutations.size()]);
  }
  
  private static IMutation[] getFirstEditionMutations()
  {
	  return null;
  }
}