package net.sf.anathema.namegenerator.exalted.domain;

public interface IVoidstateTokenCollection {

  public static final String[] COLOR_TOKENS = { "Alabaster", //$NON-NLS-1$
      "Amber", //$NON-NLS-1$
      "Amythest", //$NON-NLS-1$
      "Ashen", //$NON-NLS-1$
      "Azure", //$NON-NLS-1$
      "Black", //$NON-NLS-1$
      "Bright", //$NON-NLS-1$
      "Brilliant", //$NON-NLS-1$
      "Cerulean", //$NON-NLS-1$
      "Citrine", //$NON-NLS-1$
      "Crimson", //$NON-NLS-1$
      "Ebon", //$NON-NLS-1$
      "Flaxen", //$NON-NLS-1$
      "Golden", //$NON-NLS-1$
      "Grey", //$NON-NLS-1$
      "Pale", //$NON-NLS-1$
      "Quicksilver", //$NON-NLS-1$
      "Radiant", //$NON-NLS-1$
      "Saffron", //$NON-NLS-1$
      "Scarlet", //$NON-NLS-1$
      "Shadowy", //$NON-NLS-1$
      "Silver", //$NON-NLS-1$
      "Vermillion", //$NON-NLS-1$
      "Violet", //$NON-NLS-1$
      "White" //$NON-NLS-1$ 
  };
  public static final String[] CONDITION_TOKENS = { "Condition", //$NON-NLS-1$
      "Adamant", //$NON-NLS-1$
      "Blazing", //$NON-NLS-1$
      "Blind", //$NON-NLS-1$
      "Celestial", //$NON-NLS-1$
      "Distant", //$NON-NLS-1$
      "Drunken", //$NON-NLS-1$
      "Elder", //$NON-NLS-1$
      "Empty", //$NON-NLS-1$
      "Eyeless", //$NON-NLS-1$
      "Final", //$NON-NLS-1$
      "First and Forsaken", //$NON-NLS-1$
      "Flaming", //$NON-NLS-1$
      "Forsaken", //$NON-NLS-1$
      "Hidden", //$NON-NLS-1$
      "Hungry", //$NON-NLS-1$
      "Invisible", //$NON-NLS-1$
      "Last", //$NON-NLS-1$
      "Masked", //$NON-NLS-1$
      "Ravenous", //$NON-NLS-1$
      "Secret", //$NON-NLS-1$
      "Silent", //$NON-NLS-1$
      "Veiled", //$NON-NLS-1$
      "Weeping", //$NON-NLS-1$
      "Whispering" }; //$NON-NLS-1$
  public static final String[] EMOTION_NEGATIVE_TOKENS = { "Bitter", //$NON-NLS-1$
      "Blighted", //$NON-NLS-1$
      "Deadly", //$NON-NLS-1$
      "Death", //$NON-NLS-1$
      "Dirgeful", //$NON-NLS-1$
      "Ferocious", //$NON-NLS-1$
      "Funereal", //$NON-NLS-1$
      "Grieving", //$NON-NLS-1$
      "Grievious", //$NON-NLS-1$
      "Hateful", //$NON-NLS-1$
      "Poison", //$NON-NLS-1$
      "Rancourous", //$NON-NLS-1$
      "Sad", //$NON-NLS-1$
      "Savage", //$NON-NLS-1$
      "Sorrowful", //$NON-NLS-1$
      "Sour", //$NON-NLS-1$
      "Unrepentant", //$NON-NLS-1$
      "Vengeful", //$NON-NLS-1$
      "Venomous", //$NON-NLS-1$
      "Wrathful" }; //$NON-NLS-1$
  public static final String[] EMOTION_POSITIVE_TOKENS = { "Auspicious", //$NON-NLS-1$
      "Benificent", //$NON-NLS-1$
      "Blessed", //$NON-NLS-1$
      "Bounteous", //$NON-NLS-1$
      "Exaltant", //$NON-NLS-1$
      "Excellent", //$NON-NLS-1$
      "Flawless", //$NON-NLS-1$
      "Harmonious", //$NON-NLS-1$
      "Heavenly", //$NON-NLS-1$
      "Incomparable", //$NON-NLS-1$
      "Joyous", //$NON-NLS-1$
      "Jubilant", //$NON-NLS-1$
      "Perfect", //$NON-NLS-1$
      "Sagacious", //$NON-NLS-1$
      "Silken", //$NON-NLS-1$
      "Virtuous" }; //$NON-NLS-1$
  public static final String[] HEROIC_TOKENS = { "Battleworn", //$NON-NLS-1$
      "Glorious", //$NON-NLS-1$
      "Great", //$NON-NLS-1$
      "Invincible", //$NON-NLS-1$
      "Mighty", //$NON-NLS-1$
      "Stalwart", //$NON-NLS-1$
      "Triumphal", //$NON-NLS-1$
      "Triumphant", //$NON-NLS-1$
      "Unconquered", //$NON-NLS-1$
      "Undefeated", //$NON-NLS-1$
      "Victorious" }; //$NON-NLS-1$

  public static final String[] MOVEMENT_TOKENS = { "Ascending", //$NON-NLS-1$
      "Descending", //$NON-NLS-1$
      "Flickering", //$NON-NLS-1$
      "Flowing", //$NON-NLS-1$
      "Fluttering", //$NON-NLS-1$
      "Leaping", //$NON-NLS-1$
      "Running", //$NON-NLS-1$
      "Soaring", //$NON-NLS-1$
      "Still", //$NON-NLS-1$
      "Towering", //$NON-NLS-1$
      "Unfettered", //$NON-NLS-1$
      "Wandering" }; //$NON-NLS-1$
  public static final String[] NUMBER_TOKENS = { "Eighth", //$NON-NLS-1$
      "Eleventh", //$NON-NLS-1$
      "Fifth", //$NON-NLS-1$
      "First", //$NON-NLS-1$
      "Fourth", //$NON-NLS-1$
      "Last", //$NON-NLS-1$
      "Ninth", //$NON-NLS-1$
      "Number One", //$NON-NLS-1$
      "Number Ten", //$NON-NLS-1$
      "One Hundredth", //$NON-NLS-1$
      "One Thousandth", //$NON-NLS-1$
      "Second", //$NON-NLS-1$
      "Seventh", //$NON-NLS-1$
      "Sixth", //$NON-NLS-1$
      "Ten Thousandth", //$NON-NLS-1$
      "Tenth", //$NON-NLS-1$
      "Third", //$NON-NLS-1$
      "Twentieth" }; //$NON-NLS-1$
  public static final String[] ANIMAL_TOKENS = { "Bat", //$NON-NLS-1$
      "Bear", //$NON-NLS-1$
      "Bull", //$NON-NLS-1$
      "Carp", //$NON-NLS-1$
      "Coyote", //$NON-NLS-1$
      "Crane", //$NON-NLS-1$
      "Cricket", //$NON-NLS-1$
      "Crow", //$NON-NLS-1$
      "Dog", //$NON-NLS-1$
      "Dolphin", //$NON-NLS-1$
      "Dragon", //$NON-NLS-1$
      "Eagle", //$NON-NLS-1$
      "Ghost", //$NON-NLS-1$
      "Hyena", //$NON-NLS-1$
      "Jackal", //$NON-NLS-1$
      "Leopard", //$NON-NLS-1$
      "Lion", //$NON-NLS-1$
      "Locust", //$NON-NLS-1$
      "Mammoth", //$NON-NLS-1$
      "Mantis", //$NON-NLS-1$
      "Monkey", //$NON-NLS-1$
      "Owl", //$NON-NLS-1$
      "Ox", //$NON-NLS-1$
      "Panther", //$NON-NLS-1$
      "Phoenix", //$NON-NLS-1$
      "Rat", //$NON-NLS-1$
      "Raven", //$NON-NLS-1$
      "Scarab", //$NON-NLS-1$
      "Serpent", //$NON-NLS-1$
      "Shark", //$NON-NLS-1$
      "Snake", //$NON-NLS-1$
      "Spider", //$NON-NLS-1$
      "Tiger", //$NON-NLS-1$
      "Viper", //$NON-NLS-1$
      "Vulture", //$NON-NLS-1$
      "Wolf" }; //$NON-NLS-1$
  public static final String[] BODY_PART_TOKENS = { "Blood", //$NON-NLS-1$
      "Claw", //$NON-NLS-1$
      "Eyes", //$NON-NLS-1$
      "Face", //$NON-NLS-1$
      "Finger", //$NON-NLS-1$
      "Fist", //$NON-NLS-1$
      "Hand", //$NON-NLS-1$
      "Heart", //$NON-NLS-1$
      "Mind", //$NON-NLS-1$
      "Nail", //$NON-NLS-1$
      "Silhouette", //$NON-NLS-1$
      "Soul", //$NON-NLS-1$
      "Stance", //$NON-NLS-1$
      "Tooth", //$NON-NLS-1$
      "Wing" }; //$NON-NLS-1$
  public static final String[] BUILDING_TOKENS = { "Bier", //$NON-NLS-1$
      "Catacomb", //$NON-NLS-1$
      "Citadel", //$NON-NLS-1$
      "Demesne", //$NON-NLS-1$
      "Forge", //$NON-NLS-1$
      "Fortress", //$NON-NLS-1$
      "Gate", //$NON-NLS-1$
      "Keep", //$NON-NLS-1$
      "Kingdom", //$NON-NLS-1$
      "Labyrinth", //$NON-NLS-1$
      "Manse", //$NON-NLS-1$
      "Monastary", //$NON-NLS-1$
      "Monolith", //$NON-NLS-1$
      "Obelisk", //$NON-NLS-1$
      "Pillar", //$NON-NLS-1$
      "Prison", //$NON-NLS-1$
      "Rampart", //$NON-NLS-1$
      "Redoubt", //$NON-NLS-1$
      "Refuge", //$NON-NLS-1$
      "Ruin", //$NON-NLS-1$
      "Spire", //$NON-NLS-1$
      "Temple", //$NON-NLS-1$
      "Tomb", //$NON-NLS-1$
      "Tower", //$NON-NLS-1$
      "Vault" }; //$NON-NLS-1$
  public static final String[] CELESTIAL_BODY_TOKENS = { "Cloud", //$NON-NLS-1$
      "Heaven", //$NON-NLS-1$
      "Heavens", //$NON-NLS-1$
      "Horizon", //$NON-NLS-1$
      "Moon", //$NON-NLS-1$
      "Rainbow", //$NON-NLS-1$
      "Sky", //$NON-NLS-1$
      "Star", //$NON-NLS-1$
      "Starfall", //$NON-NLS-1$
      "Sun", //$NON-NLS-1$
      "Sunrise", //$NON-NLS-1$
      "Void", //$NON-NLS-1$
      "Wyld" }; //$NON-NLS-1$
  public static final String[] CHARM_GENERAL_TOKENS = { "Attack", //$NON-NLS-1$
      "Attitude", //$NON-NLS-1$
      "Discipline", //$NON-NLS-1$
      "Evasion", //$NON-NLS-1$
      "Exercise", //$NON-NLS-1$
      "Form", //$NON-NLS-1$
      "Glance", //$NON-NLS-1$
      "Intuition", //$NON-NLS-1$
      "Invigoration", //$NON-NLS-1$
      "Meditation", //$NON-NLS-1$
      "Method", //$NON-NLS-1$
      "Prana", //$NON-NLS-1$
      "Spirit", //$NON-NLS-1$
      "Stance", //$NON-NLS-1$
      "Style", //$NON-NLS-1$
      "Technique", //$NON-NLS-1$
      "Temper", //$NON-NLS-1$
      "Understanding" }; //$NON-NLS-1$
  public static final String[] CHARM_COMBAT_TOKENS = { "Attack", //$NON-NLS-1$
      "Block", //$NON-NLS-1$
      "Blow", //$NON-NLS-1$
      "Counterattack", //$NON-NLS-1$
      "Evasion", //$NON-NLS-1$
      "Fist", //$NON-NLS-1$
      "Form", //$NON-NLS-1$
      "Hammer", //$NON-NLS-1$
      "Kick", //$NON-NLS-1$
      "Prana", //$NON-NLS-1$
      "Stance", //$NON-NLS-1$
      "Strike", //$NON-NLS-1$
      "Technique", //$NON-NLS-1$
      "Throw" }; //$NON-NLS-1$
  public static final String[] LOCATION_TOKENS = { "City", //$NON-NLS-1$
      "Depths", //$NON-NLS-1$
      "East", //$NON-NLS-1$
      "Forests", //$NON-NLS-1$
      "Heavens", //$NON-NLS-1$
      "Land", //$NON-NLS-1$
      "Mountains", //$NON-NLS-1$
      "North", //$NON-NLS-1$
      "Oceans", //$NON-NLS-1$
      "Seas", //$NON-NLS-1$
      "Sky", //$NON-NLS-1$
      "South", //$NON-NLS-1$
      "Underworld", //$NON-NLS-1$
      "Wasteland", //$NON-NLS-1$
      "Waves", //$NON-NLS-1$
      "West", //$NON-NLS-1$
      "Woods" }; //$NON-NLS-1$
  public static final String[] METAL_STONE_TOKENS = { "Bronze", //$NON-NLS-1$
      "Copper", //$NON-NLS-1$
      "Granite", //$NON-NLS-1$
      "Iron", //$NON-NLS-1$
      "Iron", //$NON-NLS-1$
      "Metal", //$NON-NLS-1$
      "Obsidian", //$NON-NLS-1$
      "Onyx", //$NON-NLS-1$
      "Ore", //$NON-NLS-1$
      "Steel", //$NON-NLS-1$
      "Stone" }; //$NON-NLS-1$
  public static final String[] NATURAL_OBJECT_TOKENS = { "Blossom", //$NON-NLS-1$
      "Boulder", //$NON-NLS-1$
      "Cliff", //$NON-NLS-1$
      "Dawn", //$NON-NLS-1$
      "Day", //$NON-NLS-1$
      "Dusk", //$NON-NLS-1$
      "Forest", //$NON-NLS-1$
      "Frost", //$NON-NLS-1$
      "Garden", //$NON-NLS-1$
      "Hill", //$NON-NLS-1$
      "Ice", //$NON-NLS-1$
      "Iceflow", //$NON-NLS-1$
      "Lightning", //$NON-NLS-1$
      "Lotus", //$NON-NLS-1$
      "Midnight", //$NON-NLS-1$
      "Mist", //$NON-NLS-1$
      "Monsoon", //$NON-NLS-1$
      "Mountain", //$NON-NLS-1$
      "Night", //$NON-NLS-1$
      "Oak", //$NON-NLS-1$
      "Ocean", //$NON-NLS-1$
      "Orchid", //$NON-NLS-1$
      "Paradise", //$NON-NLS-1$
      "Rainbow", //$NON-NLS-1$
      "Reed", //$NON-NLS-1$
      "River", //$NON-NLS-1$
      "Rose", //$NON-NLS-1$
      "Skies", //$NON-NLS-1$
      "Sky", //$NON-NLS-1$
      "Storm", //$NON-NLS-1$
      "Thunder", //$NON-NLS-1$
      "Thunderhead", //$NON-NLS-1$
      "Tsunami", //$NON-NLS-1$
      "Twilight", //$NON-NLS-1$
      "Waterfall", //$NON-NLS-1$
      "Willow", //$NON-NLS-1$
      "Wind" }; //$NON-NLS-1$
  public static final String[] NEGATIVE_TOKENS = { "Abyss", //$NON-NLS-1$
      "Anger", //$NON-NLS-1$
      "Ash", //$NON-NLS-1$
      "Ashes", //$NON-NLS-1$
      "Bitterness", //$NON-NLS-1$
      "Death", //$NON-NLS-1$
      "Dirge", //$NON-NLS-1$
      "Dust", //$NON-NLS-1$
      "Havoc", //$NON-NLS-1$
      "Loss", //$NON-NLS-1$
      "Misery", //$NON-NLS-1$
      "Oblivion", //$NON-NLS-1$
      "Pain", //$NON-NLS-1$
      "Plague", //$NON-NLS-1$
      "Sadness", //$NON-NLS-1$
      "Vengeance", //$NON-NLS-1$
      "Venom", //$NON-NLS-1$
      "Wrath" }; //$NON-NLS-1$
  public static final String[] PERSON_TOKENS = { "Assassin", //$NON-NLS-1$
      "Bodhissatva", //$NON-NLS-1$
      "Bureaucrat", //$NON-NLS-1$
      "Empress", //$NON-NLS-1$
      "Guardian", //$NON-NLS-1$
      "Hero", //$NON-NLS-1$
      "Hunter", //$NON-NLS-1$
      "Killer", //$NON-NLS-1$
      "King", //$NON-NLS-1$
      "Lord", //$NON-NLS-1$
      "Lover", //$NON-NLS-1$
      "Maiden", //$NON-NLS-1$
      "Master", //$NON-NLS-1$
      "Prince", //$NON-NLS-1$
      "Princess", //$NON-NLS-1$
      "Queen", //$NON-NLS-1$
      "Sage", //$NON-NLS-1$
      "Scholar", //$NON-NLS-1$
      "Tyrant", //$NON-NLS-1$
      "Warrior" }; //$NON-NLS-1$
  public static final String[] PRECIOUS_MATERIAL_TOKENS = { "Crystal", //$NON-NLS-1$
      "Diamond", //$NON-NLS-1$
      "Emerald", //$NON-NLS-1$
      "Gemstone", //$NON-NLS-1$
      "Glass", //$NON-NLS-1$
      "Ivory", //$NON-NLS-1$
      "Jade", //$NON-NLS-1$
      "Moonsilver", //$NON-NLS-1$
      "Moonstone", //$NON-NLS-1$
      "Oricalchum", //$NON-NLS-1$
      "Platinum", //$NON-NLS-1$
      "Ruby", //$NON-NLS-1$
      "Sapphire", //$NON-NLS-1$
      "Silk", //$NON-NLS-1$
      "Soulsteel", //$NON-NLS-1$
      "Starmetal" }; //$NON-NLS-1$
  public static final String[] RELATION_TOKENS = { "Ally", //$NON-NLS-1$
      "Child", //$NON-NLS-1$
      "Daimyo", //$NON-NLS-1$
      "Daughter", //$NON-NLS-1$
      "Father", //$NON-NLS-1$
      "Lord", //$NON-NLS-1$
      "Master", //$NON-NLS-1$
      "Mother", //$NON-NLS-1$
      "Ovalisque", //$NON-NLS-1$
      "Serf", //$NON-NLS-1$
      "Servant", //$NON-NLS-1$
      "Slave", //$NON-NLS-1$
      "Son" }; //$NON-NLS-1$
  public static final String[] WEAPON_TOKENS = { "Axe", //$NON-NLS-1$
      "Blade", //$NON-NLS-1$
      "Bow", //$NON-NLS-1$
      "Fist", //$NON-NLS-1$
      "Gauntlet", //$NON-NLS-1$
      "Hammer", //$NON-NLS-1$
      "Knife", //$NON-NLS-1$
      "Lance", //$NON-NLS-1$
      "Shield", //$NON-NLS-1$
      "Spear", //$NON-NLS-1$
      "Staff", //$NON-NLS-1$
      "Sword" }; //$NON-NLS-1$
  public static final String[] DESTROYING_TOKENS = { "Breaking", //$NON-NLS-1$
      "Consuming", //$NON-NLS-1$
      "Crushing", //$NON-NLS-1$
      "Destroying", //$NON-NLS-1$
      "Devouring", //$NON-NLS-1$
      "Piercing", //$NON-NLS-1$
      "Scorning", //$NON-NLS-1$
      "Smashing", //$NON-NLS-1$
      "Smiting", //$NON-NLS-1$
      "Stealing", //$NON-NLS-1$
      "Sundering", //$NON-NLS-1$
      "Tearing", //$NON-NLS-1$
      "Thrashing" }; //$NON-NLS-1$
  public static final String[] LOVING_TOKENS = { "Esteeming", //$NON-NLS-1$
      "Glorifying", //$NON-NLS-1$
      "Kissing", //$NON-NLS-1$
      "Loving", //$NON-NLS-1$
      "Praising", //$NON-NLS-1$
      "Treasuring", //$NON-NLS-1$
      "Worshipping" }; //$NON-NLS-1$
}