package net.sf.anathema.namegenerator.exalted.domain;

import net.sf.anathema.lib.random.RandomUtilities;
import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.category.CategorizedTokenNameFactory;
import net.sf.anathema.namegenerator.domain.category.CategorizedTokenNameTemplate;
import net.sf.anathema.namegenerator.domain.category.ICategorizedTokenConfiguration;
import net.sf.anathema.namegenerator.domain.category.ICategorizedTokenNameTemplate;
import net.sf.anathema.namegenerator.domain.category.TokenCategory;

public class ThresholdNameGenerator implements INameGenerator {

  private final ICategorizedTokenNameTemplate[] templates = new ICategorizedTokenNameTemplate[] {
  // VoidState a)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.MOVEMENT,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.MOVEMENT,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.BODY_PART }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.NEGATIVE }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.WEAPON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      // VoidState b)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.DESTROYING,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY,
          VoidstateCategorizedTokenRegistry.DESTROYING,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.DESTROYING,
          VoidstateCategorizedTokenRegistry.PERSON }),
      // VoidState c)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.RELATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.RELATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.NEGATIVE,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.NEGATIVE,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.BUILDING,
          VoidstateCategorizedTokenRegistry.RELATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.MOVEMENT,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.NEGATIVE,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.NEGATIVE,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      // VoidState d)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.RELATION,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.LOCATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.NUMBER,
          VoidstateCategorizedTokenRegistry.PERSON,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.NUMBER,
          VoidstateCategorizedTokenRegistry.PERSON }),
      // VoidState e)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.WEAPON,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.PERSON }),
      // VoidState f)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.WEAPON,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.WEAPON,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.RELATION }),
      // VoidState g)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.LOCATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.WEAPON,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.LOCATION }),
      // VoidState h)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.AND,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY,
          VoidstateCategorizedTokenRegistry.RELATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.NEGATIVE,
          VoidstateCategorizedTokenRegistry.AND,
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.AND,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.LOCATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.AND,
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.PERSON }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.RELATION,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.METAL_STONE,
          VoidstateCategorizedTokenRegistry.AND,
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.NEGATIVE,
          VoidstateCategorizedTokenRegistry.AND,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.PERSON }),
      // VoidState i)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.PERSON,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PRECIOUS_MATERIAL,
          VoidstateCategorizedTokenRegistry.BODY_PART,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.ANIMAL }),
      // VoidState j)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.RELATION,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.PERSON,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.BUILDING }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.RELATION,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.BODY_PART }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.RELATION,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.CONDITION,
          VoidstateCategorizedTokenRegistry.LOCATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.RELATION,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.COLOR,
          VoidstateCategorizedTokenRegistry.CELESTIAL_BODY }),
      // VoidState k)
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.LOCATION }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.HEROIC,
          VoidstateCategorizedTokenRegistry.ANIMAL,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.EMOTION_POSITIVE,
          VoidstateCategorizedTokenRegistry.NATURAL_OBJECT }),
      new CategorizedTokenNameTemplate(new TokenCategory[] {
          VoidstateCategorizedTokenRegistry.MOVEMENT,
          VoidstateCategorizedTokenRegistry.WEAPON,
          VoidstateCategorizedTokenRegistry.OF,
          VoidstateCategorizedTokenRegistry.THE,
          VoidstateCategorizedTokenRegistry.EMOTION_NEGATIVE,
          VoidstateCategorizedTokenRegistry.BUILDING }) };
  private CategorizedTokenNameFactory factory;

  public ThresholdNameGenerator() {
    this(new VoidstateCategorizedTokenRegistry());
  }

  public ThresholdNameGenerator(ICategorizedTokenConfiguration tokenRegistry) {
    factory = new CategorizedTokenNameFactory(tokenRegistry);
  }

  public String[] createNames(int count) {
    String[] names = new String[count];
    for (int nameIndex = 0; nameIndex < names.length; nameIndex++) {
      ICategorizedTokenNameTemplate template = RandomUtilities.choose(templates);
      names[nameIndex] = factory.createName(template);
    }
    return names;
  }
}