package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.model.QualityExclusion;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;
import net.sf.anathema.character.lunar.beastform.model.gift.weapons.SavageMoonsilverTalonsTemplate;
import net.sf.anathema.character.lunar.beastform.model.gift.weapons.TerribleBeastClawsTemplate;

public class GiftProvider {

  public static IGift[] getAllGifts() {
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