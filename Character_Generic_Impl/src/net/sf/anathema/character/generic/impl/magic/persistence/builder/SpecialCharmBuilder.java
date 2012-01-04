package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.charm.special.*;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.*;

public class SpecialCharmBuilder
{
	public static final String ATTRIB_NAME = "name";
	private static final String ATTRIB_MODIFIER = "modifier";
	public static final String ATTRIB_TRAIT = "trait";
	private static final String ATTRIB_VALUE = "value";
	private static final String ATTRIB_ESSENCE = "essence";

	private static final String TAG_PAIN_TOLERANCE = "painTolerance";
	private static final String TAG_LEVEL = "level";

	private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";

	private static final String TAG_TRANSCENDENCE = "transcendence";

	private static final String TAG_REPURCHASES = "repurchases";
	private static final String TAG_REPURCHASE = "repurchase";
	private static final String ATTRIB_ABSOLUTE_MAX = "absoluteMax";
	private static final String ATTRIB_LIMITING_TRAIT = "limitingTrait";
	private static final String ATTRIB_LIMIT = "limit";

	private static final String TAG_ESSENCE_FIXED_REPURCHASES = "essenceFixedRepurchases";

	private static final String TAG_MULTI_EFFECT = "multiEffects";
	private static final String TAG_EFFECT = "effect";
	private static final String ATTRIB_PREREQ_EFFECT = "prereqEffect";

	private static final String TAG_UPGRADABLE = "upgradeable";
	private static final String TAG_UPGRADE = "upgrade";
	private static final String ATTRIB_BP_COST = "bpCost";
	private static final String ATTRIB_XP_COST = "xpCost";
	private static final String ATTRIB_REQUIRES_BASE = "requiresBase";
	private static final String ATTRIB_TRAIT_VALUE = "traitValue";

	private static final String TAG_ELEMENTAL = "elemental";

	private static final String TAG_SUBEFFECTS = "subeffects";
	private static final String TAG_SUBEFFECT = "subeffect";
	private static final String TAG_BP_COST = "bpCost";
    private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();
    private final OxBodyCharmBuilder oxBodyCharmBuilder = new OxBodyCharmBuilder();

  public ISpecialCharm readSpecialCharm(Element charmElement, String id)
	{
		ISpecialCharm specialCharm = oxBodyCharmBuilder.readOxBodyCharm(charmElement, id);
		specialCharm = specialCharm == null ? readPainToleranceCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readTraitCapModifierCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readTranscendenceCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readRepurchaseCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readEssenceFixedRepurchasesCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readMultiEffectCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readUpgradableCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readElementalCharm(charmElement, id) : specialCharm;
		specialCharm = specialCharm == null ? readSubEffectCharm(charmElement, id) : specialCharm;
		return specialCharm;
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
			levelArray[i] = Integer.parseInt(elements.get(i).attributeValue(ATTRIB_VALUE));

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
		ITraitType trait = traitTypeFinder.getTrait(traitString);
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
			ITraitType trait = traitTypeFinder.getTrait(limitingTraitString);

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
		ITraitType trait = traitTypeFinder.getTrait(traitString);
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
        return createTieredCharm(id, trait, tierArray);
	}

    private ISpecialCharm createTieredCharm(String id, ITraitType trait, CharmTier[] tierArray) {
        if (trait == null)
            return new TieredMultiLearnableCharm(id, tierArray);
        else
            return new TieredMultiLearnableCharm(id, trait, tierArray);
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

	private ISpecialCharm readUpgradableCharm(Element charmElement, String id)
	{
		Element upgradableElement = charmElement.element(TAG_UPGRADABLE);
		if (upgradableElement == null)
			return null;
		boolean requiresBase =
			ElementUtilities.getBooleanAttribute(upgradableElement, ATTRIB_REQUIRES_BASE, true);
		List<String> upgrades = new ArrayList<String>();
		Map<String, Integer> bpCosts = new HashMap<String, Integer>();
		Map<String, Integer> xpCosts = new HashMap<String, Integer>();
		Map<String, Integer> essenceMins = new HashMap<String, Integer>();
		Map<String, Integer> traitMins = new HashMap<String, Integer>();
		Map<String, ITraitType> traits = new HashMap<String, ITraitType>();

		for (Object upgradeObj : upgradableElement.elements(TAG_UPGRADE))
		{
			Element upgrade = (Element)upgradeObj;
			String name = upgrade.attributeValue(ATTRIB_NAME);
			upgrades.add(name);

			try
			{
				Integer bpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_BP_COST, -1);
				if (bpCost != -1) bpCosts.put(name, bpCost);

				Integer xpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_XP_COST, 1);
				xpCosts.put(name, xpCost);

				Integer essenceMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_ESSENCE, -1);
				if (essenceMin != -1) essenceMins.put(name, essenceMin);

				Integer traitMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_TRAIT_VALUE, -1);
				if (traitMin != -1) traitMins.put(name, essenceMin);

				String trait = upgrade.attributeValue(ATTRIB_TRAIT);
				if (trait != null) traits.put(name, traitTypeFinder.getTrait(trait));

			} catch (Exception e) { }
		}
		String[] upgradeArray = new String[upgrades.size()];
		upgrades.toArray(upgradeArray);
		return new UpgradableCharm(id, upgradeArray, requiresBase,
				bpCosts, xpCosts, essenceMins, traitMins, traits);
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
		return traitTypeFinder.getTrait(traitString);
	}
}