package net.sf.anathema.namegenerator.exalted.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.namegenerator.domain.category.AggregatedTokenCategory;
import net.sf.anathema.namegenerator.domain.category.ICategorizedTokenConfiguration;
import net.sf.anathema.namegenerator.domain.category.TokenCategory;
import net.sf.anathema.namegenerator.domain.general.INameTokenFactory;
import net.sf.anathema.namegenerator.domain.general.RandomChoosingTokenFactory;

public class VoidstateCategorizedTokenRegistry implements ICategorizedTokenConfiguration {

  public static final TokenCategory COLOR = new TokenCategory("Tokenss.Color"); //$NON-NLS-1$
  public static final TokenCategory CONDITION = new TokenCategory("Tokens.Condition"); //$NON-NLS-1$
  public static final TokenCategory EMOTION_NEGATIVE = new TokenCategory("Tokens.EmotionNegative"); //$NON-NLS-1$
  public static final TokenCategory EMOTION_POSITIVE = new TokenCategory("Tokens.EmotionPositive"); //$NON-NLS-1$
  public static final TokenCategory HEROIC = new TokenCategory("Tokens.Heroic"); //$NON-NLS-1$
  public static final TokenCategory MOVEMENT = new TokenCategory("Tokens.Movement"); //$NON-NLS-1$
  public static final TokenCategory NUMBER = new TokenCategory("Tokens.Number"); //$NON-NLS-1$
  public static final TokenCategory ANIMAL = new TokenCategory("Tokens.Animal"); //$NON-NLS-1$
  public static final TokenCategory BODY_PART = new TokenCategory("Tokens.BodyPart"); //$NON-NLS-1$
  public static final TokenCategory BUILDING = new TokenCategory("Tokens.Building"); //$NON-NLS-1$
  public static final TokenCategory CELESTIAL_BODY = new TokenCategory("Tokens.CelestialBody"); //$NON-NLS-1$
  public static final TokenCategory CHARM_GENERAL = new TokenCategory("Tokens.General"); //$NON-NLS-1$
  public static final TokenCategory CHARM_COMBAT = new TokenCategory("Tokens.CharmCombat"); //$NON-NLS-1$
  public static final TokenCategory LOCATION = new TokenCategory("Tokens.Location"); //$NON-NLS-1$
  public static final TokenCategory METAL_STONE = new TokenCategory("Tokens.MetalStone"); //$NON-NLS-1$
  public static final TokenCategory NATURAL_OBJECT = new TokenCategory("Tokens.NaturalObject"); //$NON-NLS-1$
  public static final TokenCategory NEGATIVE = new TokenCategory("Tokens.Negative"); //$NON-NLS-1$
  public static final TokenCategory PERSON = new TokenCategory("Tokens.Person"); //$NON-NLS-1$
  public static final TokenCategory PRECIOUS_MATERIAL = new TokenCategory("Tokens.PreciousMaterial"); //$NON-NLS-1$
  public static final TokenCategory RELATION = new TokenCategory("Tokens.Relation"); //$NON-NLS-1$
  public static final TokenCategory WEAPON = new TokenCategory("Tokens.Weapon"); //$NON-NLS-1$
  public static final TokenCategory DESTROYING = new TokenCategory("Tokens.Destroying"); //$NON-NLS-1$
  public static final TokenCategory LOVING = new TokenCategory("Tokens.Loving"); //$NON-NLS-1$
  public static final TokenCategory AND = new TokenCategory("Tokens.And"); //$NON-NLS-1$
  public static final TokenCategory OF = new TokenCategory("Tokens.Of"); //$NON-NLS-1$
  public static final TokenCategory FROM = new TokenCategory("Tokens.From"); //$NON-NLS-1$
  public static final TokenCategory IN = new TokenCategory("Tokens.In"); //$NON-NLS-1$
  public static final TokenCategory THE = new TokenCategory("Tokens.The"); //$NON-NLS-1$

  private final Map<TokenCategory, String[]> tokensByCategory = new HashMap<TokenCategory, String[]>();
  private final TokenCategory[] rootTokenCategories = new TokenCategory[] {
      new AggregatedTokenCategory("Tokens.Adjectives", new TokenCategory[] { //$NON-NLS-1$
          COLOR, CONDITION, EMOTION_NEGATIVE, EMOTION_POSITIVE, HEROIC, MOVEMENT, NUMBER }),
      new AggregatedTokenCategory("Tokens.Nouns", new TokenCategory[] { //$NON-NLS-1$
              ANIMAL,
              BODY_PART,
              BUILDING,
              CELESTIAL_BODY,
              CHARM_GENERAL,
              CHARM_COMBAT,
              LOCATION,
              METAL_STONE,
              NATURAL_OBJECT,
              NEGATIVE,
              PERSON,
              PRECIOUS_MATERIAL,
              RELATION,
              WEAPON,
              DESTROYING,
              LOVING }),
      new AggregatedTokenCategory("Tokens.Verbs", new TokenCategory[] { //$NON-NLS-1$
          DESTROYING, LOVING, }),
      new AggregatedTokenCategory("Tokens.Glues", new TokenCategory[] { AND, FROM, IN, OF, THE }) }; //$NON-NLS-1$

  public VoidstateCategorizedTokenRegistry() {
    tokensByCategory.put(COLOR, IVoidstateTokenCollection.COLOR_TOKENS);
    tokensByCategory.put(CONDITION, IVoidstateTokenCollection.CONDITION_TOKENS);
    tokensByCategory.put(EMOTION_NEGATIVE, IVoidstateTokenCollection.EMOTION_NEGATIVE_TOKENS);
    tokensByCategory.put(EMOTION_POSITIVE, IVoidstateTokenCollection.EMOTION_POSITIVE_TOKENS);
    tokensByCategory.put(HEROIC, IVoidstateTokenCollection.HEROIC_TOKENS);
    tokensByCategory.put(MOVEMENT, IVoidstateTokenCollection.MOVEMENT_TOKENS);
    tokensByCategory.put(NUMBER, IVoidstateTokenCollection.NUMBER_TOKENS);
    tokensByCategory.put(ANIMAL, IVoidstateTokenCollection.ANIMAL_TOKENS);
    tokensByCategory.put(BODY_PART, IVoidstateTokenCollection.BODY_PART_TOKENS);
    tokensByCategory.put(BUILDING, IVoidstateTokenCollection.BUILDING_TOKENS);
    tokensByCategory.put(CELESTIAL_BODY, IVoidstateTokenCollection.CELESTIAL_BODY_TOKENS);
    tokensByCategory.put(CHARM_GENERAL, IVoidstateTokenCollection.CHARM_GENERAL_TOKENS);
    tokensByCategory.put(CHARM_COMBAT, IVoidstateTokenCollection.CHARM_COMBAT_TOKENS);
    tokensByCategory.put(LOCATION, IVoidstateTokenCollection.LOCATION_TOKENS);
    tokensByCategory.put(METAL_STONE, IVoidstateTokenCollection.METAL_STONE_TOKENS);
    tokensByCategory.put(NATURAL_OBJECT, IVoidstateTokenCollection.NATURAL_OBJECT_TOKENS);
    tokensByCategory.put(NEGATIVE, IVoidstateTokenCollection.NEGATIVE_TOKENS);
    tokensByCategory.put(PERSON, IVoidstateTokenCollection.PERSON_TOKENS);
    tokensByCategory.put(PRECIOUS_MATERIAL, IVoidstateTokenCollection.PRECIOUS_MATERIAL_TOKENS);
    tokensByCategory.put(RELATION, IVoidstateTokenCollection.RELATION_TOKENS);
    tokensByCategory.put(WEAPON, IVoidstateTokenCollection.WEAPON_TOKENS);
    tokensByCategory.put(DESTROYING, IVoidstateTokenCollection.DESTROYING_TOKENS);
    tokensByCategory.put(LOVING, IVoidstateTokenCollection.LOVING_TOKENS);
    tokensByCategory.put(AND, new String[] { "and" }); //$NON-NLS-1$
    tokensByCategory.put(FROM, new String[] { "from" }); //$NON-NLS-1$
    tokensByCategory.put(IN, new String[] { "in" }); //$NON-NLS-1$
    tokensByCategory.put(OF, new String[] { "of" }); //$NON-NLS-1$
    tokensByCategory.put(THE, new String[] { "the" }); //$NON-NLS-1$
  }

  public TokenCategory[] getRootTokenCategories() {
    return rootTokenCategories;
  }

  public INameTokenFactory createTokenFactory(TokenCategory tokenCategory) {
    return new RandomChoosingTokenFactory(getAllAvailableTokens(tokenCategory));
  }

  private String[] getAllAvailableTokens(TokenCategory tokenCategory) {
    if (tokenCategory instanceof AggregatedTokenCategory) {
      AggregatedTokenCategory aggregatedCategory = (AggregatedTokenCategory) tokenCategory;
      List<String> allTokens = new ArrayList<String>();
      for (TokenCategory subCategory : aggregatedCategory.getSubCateogories()) {
        Collections.addAll(allTokens, getAllAvailableTokens(subCategory));
      }
      return allTokens.toArray(new String[allTokens.size()]);
    }
    return tokensByCategory.get(tokenCategory);
  }
}