package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.ComplexMultipleEffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.ElementalMultipleEffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.PrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticPainToleranceCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.SubeffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitCapModifyingCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

import org.dom4j.Element;

public class SpecialCharmBuilder
{
	private static final String ATTRIB_NAME = "name";
	private static final String ATTRIB_MODIFIER = "modifier";
	private static final String ATTRIB_TRAIT = "trait";
	private static final String ATTRIB_VALUE = "value";
	
	private static final String TAG_OXBODY_CHARM = "oxbody";
	private static final String TAG_OXBODY_PICK = "pick";
	private static final String TAG_ZERO_HEALTH = "zeroHealthLevel";
	private static final String TAG_ONE_HEALTH = "oneHealthLevel";
	private static final String TAG_TWO_HEALTH = "twoHealthLevel";
	private static final String TAG_FOUR_HEALTH = "fourHealthLevel";
	private static final String TAG_INCAP_HEALTH = "incapHealthLevel";
	private static final String TAG_DYING_HEALTH = "dyingHealthLevel";
	
	private static final String TAG_PAIN_TOLERANCE = "painTolerance";
	private static final String TAG_LEVEL = "level";
	
	private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
	
	private static final String TAG_TRANSCENDENCE = "transcendence";
	
	private static final String TAG_REPURCHASES = "repurchases";
	private static final String TAG_REPURCHASE = "repurchase";
	private static final String ATTRIB_ABSOLUTE_MAX = "absoluteMax";
	private static final String ATTRIB_LIMITING_TRAIT = "limitingTrait";
	private static final String ATTRIB_LIMIT = "limit";
	private static final String ATTRIB_ESSENCE = "essence";
	
	private static final String TAG_ESSENCE_FIXED_REPURCHASES = "essenceFixedRepurchases";
	
	private static final String TAG_MULTI_EFFECT = "multiEffects";
	private static final String TAG_EFFECT = "effect";
	private static final String ATTRIB_PREREQ_EFFECT = "prereqEffect";
	
	private static final String TAG_ELEMENTAL = "elemental";
	
	private static final String TAG_SUBEFFECTS = "subeffects";
	private static final String TAG_SUBEFFECT = "subeffect";
	private static final String TAG_BP_COST = "bpCost";
	
	public ISpecialCharm readSpecialCharm(Element charmElement, String id)
	{
		ISpecialCharm specialCharm = null;
		specialCharm = specialCharm == null ? readOxBodyCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readPainToleranceCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readTraitCapModifierCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readTranscendenceCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readRepurchaseCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readEssenceFixedRepurchasesCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readMultiEffectCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readElementalCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readSubEffectCharm(charmElement, id) : specialCharm;
		return specialCharm;
	}
	
	private ISpecialCharm readOxBodyCharm(Element charmElement, String id)
	{
		Element oxbodyElement = charmElement.element(TAG_OXBODY_CHARM);
		if (oxbodyElement == null)
			return null;
		String[] traitNameList = oxbodyElement.attributeValue(ATTRIB_TRAIT).split(",");
		ITraitType[] traitList = new ITraitType[traitNameList.length];
		for (int i = 0; i != traitList.length; i++)
			traitList[i] = getTrait(traitNameList[i]);
		LinkedHashMap<String, HealthLevelType[]> healthPicks = new LinkedHashMap<String, HealthLevelType[]>();
		for (Object pickObj : oxbodyElement.elements(TAG_OXBODY_PICK))
		{
			Element pick = (Element)pickObj;
			String name = pick.attributeValue(ATTRIB_NAME);
			List<HealthLevelType> healthLevels = new ArrayList<HealthLevelType>();
			for (Object levelObj : pick.elements())
			{
				Element levelElement = (Element)levelObj;
				if (levelElement.getName().equals(TAG_ZERO_HEALTH))
					healthLevels.add(HealthLevelType.ZERO);
				if (levelElement.getName().equals(TAG_ONE_HEALTH))
					healthLevels.add(HealthLevelType.ONE);
				if (levelElement.getName().equals(TAG_TWO_HEALTH))
					healthLevels.add(HealthLevelType.TWO);
				if (levelElement.getName().equals(TAG_FOUR_HEALTH))
					healthLevels.add(HealthLevelType.FOUR);
				if (levelElement.getName().equals(TAG_INCAP_HEALTH))
					healthLevels.add(HealthLevelType.INCAPACITATED);
				if (levelElement.getName().equals(TAG_DYING_HEALTH))
					healthLevels.add(HealthLevelType.DYING);
			}
			HealthLevelType[] levels = new HealthLevelType[healthLevels.size()];
			healthLevels.toArray(levels);
			healthPicks.put(name, levels);
		}
		
		return new OxBodyTechniqueCharm(id, traitList, healthPicks);
	}
	
	private ISpecialCharm readPainToleranceCharm(Element charmElement, String id)
	{
		Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
		if (painToleranceElement == null)
			return null;
    @SuppressWarnings("unchecked")
    List<Element> elements = painToleranceElement.elements(TAG_LEVEL);
		int[] levelArray = new int[elements.size()];
		for (int i = 0; i != elements.size(); i++)
			levelArray[i] = Integer.parseInt(((Element)elements.get(i)).attributeValue(ATTRIB_VALUE));
				
		return new StaticPainToleranceCharm(id, levelArray.length, levelArray);
		
	}
	
	private ISpecialCharm readTraitCapModifierCharm(Element charmElement, String id)
	{
		Element traitCapModifierElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
		if (traitCapModifierElement == null)
			return null;
		String traitString = traitCapModifierElement.attributeValue(ATTRIB_TRAIT);
		if (traitString == null)
			traitString = id.split("\\.")[2];
		ITraitType trait = getTrait(traitString);
		int modifier = Integer.parseInt(traitCapModifierElement.attributeValue(ATTRIB_MODIFIER));
		return new TraitCapModifyingCharm(id, trait, modifier);
	}
	
	private ISpecialCharm readTranscendenceCharm(Element charmElement, String id)
	{
		Element transcendenceElement = charmElement.element(TAG_TRANSCENDENCE);
		if (transcendenceElement == null)
			return null;
		ITraitType trait = getGenericTraitType(id);
		int modifier = Integer.parseInt(transcendenceElement.attributeValue(ATTRIB_MODIFIER));
		return new PrerequisiteModifyingCharm(id, trait, modifier);
	}
	
	private ISpecialCharm readRepurchaseCharm(Element charmElement, String id)
	{
		Element repurchaseElement = charmElement.element(TAG_REPURCHASES);
		if (repurchaseElement == null)
			return null;
		
		String limitingTraitString = repurchaseElement.attributeValue(ATTRIB_LIMITING_TRAIT);
		if (limitingTraitString != null)
		{
			ITraitType trait = getTrait(limitingTraitString);
			
			int modifier = 0;
			String modifierString = repurchaseElement.attributeValue(ATTRIB_MODIFIER);
			try
			{
				modifier = Integer.parseInt(modifierString);
			} catch (Exception e) { }
			
			int absoluteMax = EssenceTemplate.SYSTEM_ESSENCE_MAX + modifier;
			String maxString = repurchaseElement.attributeValue(ATTRIB_ABSOLUTE_MAX);
			try
			{
				absoluteMax = Integer.parseInt(maxString);
			} catch (Exception e) { }
			
			return new TraitDependentMultiLearnableCharm(id, absoluteMax, trait, modifier);
		}
		
		String limitString = repurchaseElement.attributeValue(ATTRIB_LIMIT);
		if (limitString != null)
		{
			int limit = Integer.parseInt(limitString);
			
			return new StaticMultiLearnableCharm(id, limit);
		}
		
		String traitString = repurchaseElement.attributeValue(ATTRIB_TRAIT);
		ITraitType trait = getTrait(traitString);
		List<CharmTier> tiers = new ArrayList<CharmTier>();
		
		for (Object repurchaseObj : repurchaseElement.elements(TAG_REPURCHASE))
		{
			Element repurchase = (Element) repurchaseObj;
			int essence = Integer.parseInt(repurchase.attributeValue(ATTRIB_ESSENCE));
			CharmTier tier;
			if (trait == null)
				tier = new CharmTier(essence);
			else
			{
				int traitValue = Integer.parseInt(repurchase.attributeValue(ATTRIB_TRAIT));
				tier = new CharmTier(essence, traitValue);
			}
			
			tiers.add(tier);
		}
		
		CharmTier[] tierArray = new CharmTier[tiers.size()];
		tiers.toArray(tierArray);
		
		ISpecialCharm tieredCharm = null;
		
		if (trait == null)
			tieredCharm = new TieredMultiLearnableCharm(id, tierArray);
		else
			tieredCharm = new TieredMultiLearnableCharm(id, trait, tierArray);
		
		return tieredCharm;
	}
	
	private ISpecialCharm readEssenceFixedRepurchasesCharm(Element charmElement, String id)
	{
		Element repurchasesElement = charmElement.element(TAG_ESSENCE_FIXED_REPURCHASES);
		if (repurchasesElement == null)
			return null;
		return new EssenceFixedMultiLearnableCharm(id, EssenceTemplate.SYSTEM_ESSENCE_MAX, OtherTraitType.Essence);
	}
	
	private ISpecialCharm readMultiEffectCharm(Element charmElement, String id)
	{
		Element multiEffectElement = charmElement.element(TAG_MULTI_EFFECT);
		if (multiEffectElement == null)
			return null;
		List<String> effects = new ArrayList<String>();
		Map<String, String> prereqEffectMap = new HashMap<String, String>();
		for (Object effectObj : multiEffectElement.elements(TAG_EFFECT))
		{
			Element effect = (Element)effectObj;
			String name = effect.attributeValue(ATTRIB_NAME);
			effects.add(name);
			
			String prereqEffect = effect.attributeValue(ATTRIB_PREREQ_EFFECT);
			prereqEffectMap.put(name, prereqEffect);			
		}
		String[] effectArray = new String[effects.size()];
		effects.toArray(effectArray);
		return new ComplexMultipleEffectCharm(id, effectArray, prereqEffectMap);
	}
	
	private ISpecialCharm readElementalCharm(Element charmElement, String id)
	{
		Element elementalElement = charmElement.element(TAG_ELEMENTAL);
		if (elementalElement == null)
			return null;
		return new ElementalMultipleEffectCharm(id);
	}
	
	private ISpecialCharm readSubEffectCharm(Element charmElement, String id)
	{
		Element subeffectElement = charmElement.element(TAG_SUBEFFECTS);
		if (subeffectElement == null)
			return null;
		double cost = Double.parseDouble(subeffectElement.attributeValue(TAG_BP_COST));
		List<String> subeffects = new ArrayList<String>();
		for (Object subeffectObj : subeffectElement.elements(TAG_SUBEFFECT))
		{
			Element subeffect = (Element)subeffectObj;
			String name = subeffect.attributeValue(ATTRIB_NAME);
			subeffects.add(name);
		}
		String[] effects = new String[subeffects.size()];
		subeffects.toArray(effects);
		return new SubeffectCharm(id, effects, cost);
	}
	
	private ITraitType getGenericTraitType(String value)
	{
		String[] split = value.split("\\.");
		String traitString = split[split.length - 1];
		return getTrait(traitString);
	}
	
	private ITraitType getTrait(String value)
	{
		ITraitType trait = null;
		trait = trait == null ? getAbilityType(value) : trait;
		trait = trait == null ? getAttributeType(value) : trait;
		trait = trait == null ? getVirtueType(value) : trait;
		trait = trait == null ? getOtherType(value) : trait;
		return trait;
	}
	
	private AbilityType getAbilityType(String value)
	{
		try
		{
			return AbilityType.valueOf(value);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private AttributeType getAttributeType(String value)
	{
		try
		{
			return AttributeType.valueOf(value);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private VirtueType getVirtueType(String value)
	{
		try
		{
			return VirtueType.valueOf(value);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private OtherTraitType getOtherType(String value)
	{
		try
		{
			return OtherTraitType.valueOf(value);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	
}
