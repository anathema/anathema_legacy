package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.model.QualityExclusion;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.lunar.beastform.model.gift.weapons.SavageMoonsilverTalonsTemplate;
import net.sf.anathema.character.lunar.beastform.model.gift.weapons.TerribleBeastClawsTemplate;

public class GiftProvider {

  public static IGift[] getAllGifts(IExaltedEdition edition)
  {
	  if (edition == ExaltedEdition.FirstEdition)
		  return getFirstEditionGifts();
	  if (edition == ExaltedEdition.SecondEdition)
		  return getSecondEditionGifts();
	  return null;
  }
  
  private static IGift[] getSecondEditionGifts()
  {
	  Gifts gifts = new Gifts();
	  gifts.add("EnhancedSense", 1);
	  gifts.add("Claws", 1);
	  gifts.add("Fangs", 1);
	  gifts.add("FurFeathersLeavesScales", 1);
	  gifts.add("Hooves", 1);
	  gifts.add("Large", 1);
	  gifts.add("NightVision", 1);
	  gifts.add("SerpentineTongue", 1);
	  gifts.add("SkinHair", 1);
	  gifts.add("Small", 1);
	  gifts.add("Tail", 1);
	  gifts.add("ThirdEye", 1);
	  gifts.add("WolfsPace", 1);
	  gifts.add("ElementalAdaptationAir", 1);
	  gifts.add("ElementalAdaptationFire", 1);
	  gifts.add("ElementalAdaptationWater", 1);
	  gifts.add("ElementalAdaptationWood", 1);
	  gifts.add("ChakraEye", 2);
	  gifts.add("Chameleon", 2);
	  gifts.add("FrogTongue", 2);
	  gifts.add("GazellesPace", 2);
	  gifts.add("Gills", 2);
	  gifts.add("Huge", 2);
	  gifts.add("ImpossibleJoints", 2);
	  gifts.add("Inexhaustable", 2);
	  gifts.add("ShortGestation", 2);
	  gifts.add("PrehensileTail", 2);
	  gifts.add("ScorpionsTail", 2);
	  gifts.add("TalonsTusksHorns", 2);
	  gifts.add("ThickSkin", 2);
	  gifts.add("Toxin", 2);
	  gifts.add("Tiny", 2);
	  gifts.add("AcidicPustules", 4);
	  gifts.add("ArmoredHide", 4);
	  gifts.add("CheetahsPace", 4);
	  gifts.add("Glider", 4);
	  gifts.add("HideousMaw", 4);
	  gifts.add("LidlessDemonEye", 4);
	  gifts.add("PrehensileBodyHair", 4);
	  gifts.add("Quills", 4);
	  gifts.add("SerpentineHair", 4);
	  gifts.add("Tentacles", 4);
	  gifts.add("WallWalking", 4);
	  gifts.add("DragonsBreath", 6);
	  gifts.add("Hive", 6);
	  gifts.add("ExtraArmLegHead", 6);
	  gifts.add("SerpentsBody", 6);
	  gifts.add("SpiderLegs", 6);
	  gifts.add("StoneBody", 6);
	  gifts.add("TerrifyingMane", 6);
	  gifts.add("Wings", 6);
	  
	  return gifts.asArray();
  }
  
  private static IGift[] getFirstEditionGifts()
  {
    List<IGift> gifts = new ArrayList<IGift>();
    Gift horrifyingFirst = new AttributePointsProvidingGift("HorrifyingMightFirst", 2);//$NON-NLS-1$
    gifts.add(horrifyingFirst);
    Gift horrifyingLater = new AttributePointsProvidingGift("HorrifyingMightLater", 1);//$NON-NLS-1$
    gifts.add(horrifyingLater);
    horrifyingFirst.addCondition(new QualityExclusion(horrifyingLater));
    horrifyingLater.addCondition(new QualityExclusion(horrifyingFirst));
    Gift bestialReflexesI = new Gift("BestialReflexesI");//$NON-NLS-1$
    gifts.add(bestialReflexesI);
    Gift bestialReflexesII = new Gift("BestialReflexesII");//$NON-NLS-1$
    bestialReflexesII.addCondition(new QualityPrerequisite(bestialReflexesI));
    gifts.add(bestialReflexesII);
    Gift lightningSpeed = new Gift("LightningSpeed");//$NON-NLS-1$
    gifts.add(lightningSpeed);
    Gift spiderFoot = new Gift("Spider-FootClimbing");//$NON-NLS-1$
    spiderFoot.addCondition(new QualityPrerequisite(new IGift[] { bestialReflexesI, lightningSpeed }));
    gifts.add(spiderFoot);
    Gift glueFoot = new Gift("Glue-FootClimbing");//$NON-NLS-1$
    glueFoot.addCondition(new QualityPrerequisite(spiderFoot));
    gifts.add(glueFoot);
    Gift giftHands = new Gift("GiftHands");//$NON-NLS-1$)
    gifts.add(giftHands);
    Gift armArray = new Gift("Arm-Array"); //$NON-NLS-1$
    armArray.addCondition(new QualityPrerequisite(giftHands));
    gifts.add(armArray);
    Gift beastClaws = new BrawlWeaponProvidingGift("TerribleBeastClaws", new TerribleBeastClawsTemplate()); //$NON-NLS-1$
    gifts.add(beastClaws);
    Gift savageTalons = new BrawlWeaponProvidingGift("SavageMoonsilverTalons", new SavageMoonsilverTalonsTemplate()); //$NON-NLS-1$
    savageTalons.addCondition(new QualityPrerequisite(beastClaws));
    gifts.add(savageTalons);
    Gift resilienceNature = new Gift("ResilienceNature"); //$NON-NLS-1$
    gifts.add(resilienceNature);
    Gift woundKnitting = new Gift("Wound-KnittingPower"); //$NON-NLS-1$
    woundKnitting.addCondition(new QualityPrerequisite(resilienceNature));
    gifts.add(woundKnitting);
    Gift appearance = new Gift("FearsomeAppearance"); //$NON-NLS-1$
    gifts.add(appearance);
    Gift visage = new Gift("TerrifyingBestialVisage"); //$NON-NLS-1$
    visage.addCondition(new QualityPrerequisite(appearance));
    gifts.add(visage);
    Gift ruggedHide = new SoakProvidingGift("RuggedHide", 2, false); //$NON-NLS-1$
    gifts.add(ruggedHide);
    Gift beastArmor = new SoakProvidingGift("ImpenetrableBeast-Armor", 6, true); //$NON-NLS-1$
    beastArmor.addCondition(new QualityPrerequisite(ruggedHide));
    gifts.add(beastArmor);
    Gift poisonBite = new Gift("PoisonBite"); //$NON-NLS-1$
    gifts.add(poisonBite);
    Gift deadlyBreath = new Gift("DeadlyBreath"); //$NON-NLS-1$
    deadlyBreath.addCondition(new QualityPrerequisite(poisonBite));
    gifts.add(deadlyBreath);
    Gift senses = new AttributeEnhancingGift("EnhancedSenses", AttributeType.Perception, 2); //$NON-NLS-1$
    gifts.add(senses);
    Gift senses2 = new AttributeEnhancingGift("EnhancedSensesII", AttributeType.Perception, 2); //$NON-NLS-1$
    senses2.addCondition(new QualityPrerequisite(senses));
    gifts.add(senses2);
    Gift sight = new Gift("GhostSight"); //$NON-NLS-1$
    sight.addCondition(new QualityPrerequisite(senses));
    gifts.add(sight);
    gifts.add(new Gift("AspectGillman"));//$NON-NLS-1$)
    Gift flutteringWings = new Gift("FlutteringWings");//$NON-NLS-1$
    gifts.add(flutteringWings);
    Gift soaringPinions = new Gift("SoaringPinions");//$NON-NLS-1$
    soaringPinions.addCondition(new QualityPrerequisite(flutteringWings));
    gifts.add(soaringPinions);
    return gifts.toArray(new IGift[gifts.size()]);
  }
}