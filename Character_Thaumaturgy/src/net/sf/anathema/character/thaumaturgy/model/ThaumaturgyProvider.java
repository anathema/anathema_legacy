package net.sf.anathema.character.thaumaturgy.model;

public class ThaumaturgyProvider
{
	public static String[] getArts()
	{
		return new String[] { "Alchemy", "Astrology", "The Dead", "Demon Summoning", "Elemental Summoning",
				              "Enchantment", "Geomancy", "Husbandry", "Spirit Beckoning",
				              "Warding and Exorcism", "Weather Working" };
	}
	
	public static String[] getProcedures(String art)
	{
		if (art.equals("Astrology"))
			return new String[] { "CompileChart", "Lesser Divination", "Divination", 
				                  "Greater Divination", "VarangianCasting", "Predetermined Attributes",
				                  "Reactive Planning", "Reverse Birth Engineering", "Brighter Star",
				                  "Natural Virtues", "The Fallen Star", "Excellent Orrery Design"};
		if (art.equals("Alchemy"))
			return new String[] { "Alchemical Touchstone", "Life's Little Luxury Blends",
				                  "Blood-Staunching Compress", "Draught of Blessed Respite", "Eagle's Eye Potion",
				                  "Hero's Recovery", "(Type) Venom-Allaying Draught", "Wound-Cleansing Unguent",
				                  "Age-Staving Cordial", "Ardent Embrace Resin", "Final Vengeance",
				                  "Munificent Antivenin", "Philtre of Desire", "Tiger's Heart Elixir", 
				                  "Valiant Warrior Formula", "Deathlord's Breath", "8-Scream Devil Powder",
				                  "Heavenly Transmutation Processes", "Internal Alchemy", "Seven Bounties Paste",
				                  "Sweet Cordial", "Wind-Fire Potion"};
		if (art.equals("The Dead"))
			return new String[] { "Alarm Ward Against (Creature)", "Lesser Ward Against (Creature)",
				                  "Ward Against (Creature)", "Greater Ward Against (Creature)", "Alarm Ward Maintenance",
				                  "Lesser Ward Maintenance", "Ward Maintenance", "Keyed Ward",
				                  "Dishonest (Creature)'s Rebuke", "Expulsion", "Banish (Creature)",
				                  "Pierce Shadowland", "Summon Ghost", "Blood Magic", "Deathsight",
				                  "Body Preservation Technique", "Speak with Corpse", "Raise Corpse",
				                  "Three Days of Hun and Po", "Summon Nephwrack"};
		if (art.equals("Demon Summoning"))
			return new String[] { "Alarm War Against (Creature)", "Lesser Ward Against (Creature)",
				                  "Ward Against (Creature)", "Greater Ward Against (Creature)", "Alarm Ward Maintenance",
				                  "Lesser Ward Maintenance", "Ward Maintenance", "Keyed Ward",
				                  "Dishonest (Creature)'s Rebuke", "Expulsion", "Banish (Creature)",
				                  "Demonsight", "Summon (Species)", "Banish the Faithless Servant", 
				                  "Beckon (Unique Demon)", "Five Days Foresight", "The Year and a Day"};
		if (art.equals("Elemental Summoning"))
			return new String[] { "Alarm War Against (Creature)", "Lesser Ward Against (Creature)",
				                  "Ward Against (Creature)", "Greater Ward Against (Creature)", "Alarm Ward Maintenance",
				                  "Lesser Ward Maintenance", "Ward Maintenance", "Keyed Ward",
				                  "Dishonest (Creature)'s Rebuke",  "Banish (Creature)", "Jade Extraction Method",
				                  "Elemental Sight", "Summon (Species)", 
				                  "Beckon (Species)", "Invoke the Elemental Benediction"};
		if (art.equals("Enchantment"))
			return new String[] { "Least Wonder", "Lesser Wonder", "Talisman", "Analyze Talisman",
				                  "Fading Color Technique", "Strengthen Ironwood", "Enchant Lucky Rock",
				                  "Process Steelsilk", "Aegis-Inset Amulats", "Warding Talisman"};
		if (art.equals("Geomancy"))
			return new String[] { "Essence Sense", "Blessing", "Curse", "Dragon Line Compass",
				                  "Alloyed Essence Indicator", "Magical Attunement", "Bathing in the River Meditation",
				                  "Dragon Nest Compass", "Pearl Collecting Rite", "Ritual of Dedicated Purification",
				                  "Pulse of the Demense's Heart", "Rattle the Sanctum's Gate", "Essence Enlightening Sutra",
				                  "Yoga of Celestial Refinement", "House of Good/Ill Fortune",
				                  "Geomantic Countermagic", "Scrying"};
		if (art.equals("Husbandry"))
			return new String[] { "Alarm Ward Against (Creature)", "Lesser Ward Against (Creature)",
				                  "Ward Against (Creature)", "Greater Ward Against (Creature)", "Alarm Ward Maintenance",
				                  "Lesser Ward Maintenance", "Ward Maintenance", "Keyed Ward",
				                  "Judge the Pure Beast", "Warding the Crops", "Control (Species)",
				                  "Summon Human", "Summon (Species)", "Improved (Species) Breeding"};
		if (art.equals("Spirit Beckoning"))
			return new String[] { "Alarm War Against (Creature)", "Lesser Ward Against (Creature)",
				                  "Ward Against (Creature)", "Greater Ward Against (Creature)", "Alarm Ward Maintenance",
				                  "Lesser Ward Maintenance", "Ward Maintenance", "Keyed Ward",
				                  "Banish (Creature)", "Beckon (Little God)", "The Hecatomb", 
				                  "Spirit Sight", "Beckon (Greater God)", "The Taurobeleum"};
		if (art.equals("Warding and Exorcism"))
			return new String[] { "Alarm Ward Against (Creature)", "Lesser Ward Against (Creature)",
				                  "Ward Against (Creature)", "Greater Ward Against (Creature)", "Alarm Ward Maintenance",
				                  "Lesser Ward Maintenance", "Ward Maintenance", "Keyed Ward",
				                  "Dishonest (Creature)'s Rebuke", "Expulsion", "Banish (Creature)",
				                  "Sensing the Barrier", "Thrice-Warded Gateway", "Deathsight", 
				                  "Demonsight", "Elemental Sight", "Spirit Sight", "Warding of Undue Influence",
				                  "Breaking the Ward"};
		if (art.equals("Weather Working"))
			return new String[] { "Foretell Weather", "Minor Changes", "Transform Weather", "Major Changes"};


		return new String[0];
	}
	
	
}
