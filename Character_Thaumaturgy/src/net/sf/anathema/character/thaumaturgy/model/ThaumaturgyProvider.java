package net.sf.anathema.character.thaumaturgy.model;

public class ThaumaturgyProvider
{
	public static String[] getArts()
	{
		return new String[] { "Alchemy", "Astrology", "TheDead", "DemonSummoning", "ElementalSummoning",
				              "Enchantment", "Geomancy", "Husbandry", "SpiritBeckoning",
				              "WardingandExorcism", "WeatherWorking" };
	}
	
	public static String[] getProcedures(String art)
	{
		if (art.equals("Astrology"))
			return new String[] { "CompileChart", "LesserDivination", "Divination", 
				                  "GreaterDivination", "VarangianCasting", "PredeterminedAttributes",
				                  "ReactivePlanning", "ReverseBirthEngineering", "BrighterStar",
				                  "NaturalVirtues", "TheFallenStar", "ExcellentOrreryDesign"};
		if (art.equals("Alchemy"))
			return new String[] { "AlchemicalTouchstone", "Life'sLittleLuxuryBlends",
				                  "Blood-StaunchingCompress", "DraughtofBlessedRespite", "EaglesEyePotion",
				                  "HerosRecovery", "(Type)Venom-AllayingDraught", "Wound-CleansingUnguent",
				                  "Age-StavingCordial", "ArdentEmbraceResin", "FinalVengeance",
				                  "MunificentAntivenin", "PhiltreofDesire", "TigersHeartElixir", 
				                  "ValiantWarriorFormula", "DeathlordsBreath", "8-ScreamDevilPowder",
				                  "HeavenlyTransmutationProcesses", "InternalAlchemy", "SevenBountiesPaste",
				                  "SweetCordial", "Wind-FirePotion"};
		if (art.equals("TheDead"))
			return new String[] { "Alarm WardAgainst(Creature)", "LesserWardAgainst(Creature)",
				                  "WardAgainst(Creature)", "GreaterWardAgainst(Creature)", "AlarmWardMaintenance",
				                  "LesserWardMaintenance", "WardMaintenance", "KeyedWard",
				                  "Dishonest(Creature)sRebuke", "Expulsion", "Banish(Creature)",
				                  "PierceShadowland", "SummonGhost", "BloodMagic", "Deathsight",
				                  "BodyPreservationTechnique", "SpeakwithCorpse", "RaiseCorpse",
				                  "ThreeDaysofHunandPo", "SummonNephwrack"};
		if (art.equals("DemonSummoning"))
			return new String[] { "AlarmWardAgainst(Creature)", "LesserWardAgainst(Creature)",
				                  "WardAgainst(Creature)", "GreaterWardAgainst(Creature)", "AlarmWardMaintenance",
				                  "LesserWardMaintenance", "WardMaintenance", "KeyedWard",
				                  "Dishonest(Creature)sRebuke", "Expulsion", "Banish(Creature)",
				                  "Demonsight", "Summon(Species)", "BanishtheFaithlessServant", 
				                  "Beckon(Unique Demon)", "FiveDaysForesight", "TheYearandaDay"};
		if (art.equals("ElementalSummoning"))
			return new String[] { "AlarmWardAgainst(Creature)", "LesserWardAgainst(Creature)",
				                  "WardAgainst(Creature)", "GreaterWardAgainst(Creature)", "AlarmWardMaintenance",
				                  "LesserWardMaintenance", "WardMaintenance", "KeyedWard",
				                  "Dishonest(Creature)sRebuke",  "Banish(Creature)", "JadeExtractionMethod",
				                  "ElementalSight", "Summon(Species)", 
				                  "Beckon(Species)", "InvoketheElementalBenediction"};
		if (art.equals("Enchantment"))
			return new String[] { "LeastWonder", "LesserWonder", "Talisman", "AnalyzeTalisman",
				                  "FadingColorTechnique", "StrengthenIronwood", "EnchantLuckyRock",
				                  "ProcessSteelsilk", "Aegis-InsetAmulets", "WardingTalisman"};
		if (art.equals("Geomancy"))
			return new String[] { "EssenceSense", "Blessing", "Curse", "DragonLineCompass",
				                  "AlloyedEssenceIndicator", "MagicalAttunement", "BathingintheRiverMeditation",
				                  "DragonNestCompass", "PearlCollectingRite", "RitualofDedicatedPurification",
				                  "PulseoftheDemensesHeart", "RattletheSanctumsGate", "EssenceEnlighteningSutra",
				                  "YogaofCelestialRefinement", "HouseofGood/IllFortune",
				                  "GeomanticCountermagic", "Scrying"};
		if (art.equals("Husbandry"))
			return new String[] { "AlarmWardAgainst(Creature)", "LesserWardAgainst(Creature)",
				                  "WardAgainst(Creature)", "GreaterWardAgainst(Creature)", "AlarmWardMaintenance",
				                  "LesserWardMaintenance", "WardMaintenance", "KeyedWard",
				                  "JudgethePureBeast", "WardingtheCrops", "Control(Species)",
				                  "SummonHuman", "Summon(Species)", "Improved(Species)Breeding"};
		if (art.equals("SpiritBeckoning"))
			return new String[] { "AlarmWardAgainst(Creature)", "LesserWardAgainst(Creature)",
				                  "WardAgainst(Creature)", "GreaterWardAgainst(Creature)", "AlarmWardMaintenance",
				                  "LesserWardMaintenance", "WardMaintenance", "KeyedWard",
				                  "Banish(Creature)", "Beckon(LittleGod)", "TheHecatomb", 
				                  "SpiritSight", "Beckon(GreaterGod)", "TheTaurobeleum"};
		if (art.equals("WardingandExorcism"))
			return new String[] { "AlarmWardAgainst(Creature)", "LesserWardAgainst(Creature)",
				                  "WardAgainst(Creature)", "GreaterWardAgainst(Creature)", "AlarmWardMaintenance",
				                  "LesserWardMaintenance", "WardMaintenance", "KeyedWard",
				                  "Dishonest(Creature)sRebuke", "Expulsion", "Banish(Creature)",
				                  "SensingtheBarrier", "Thrice-WardedGateway", "Deathsight", 
				                  "Demonsight", "ElementalSight", "SpiritSight", "WardingofUndueInfluence",
				                  "BreakingtheWard"};
		if (art.equals("WeatherWorking"))
			return new String[] { "ForetellWeather", "MinorChanges", "TransformWeather", "MajorChanges"};


		return new String[0];
	}
	
	
}
