package net.sf.anathema.hero.abilities.sheet.content;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.sheet.content.FavorableTraitContent;
import net.sf.anathema.framework.environment.Resources;

import java.util.Arrays;
import java.util.List;

public class AbilitiesContent extends FavorableTraitContent {

  private Hero hero;

  public AbilitiesContent(Hero hero, Resources resources) {
    super(AbilityModelFetcher.fetch(hero), resources);
    this.hero = hero;
  }

  @Override
  public List<? extends TraitType> getMarkedTraitTypes() {
    return Arrays.asList(AbilityType.Athletics, AbilityType.Dodge, AbilityType.Larceny, AbilityType.Ride, AbilityType.Stealth);
  }

  @Override
  public IdentifiedTraitTypeList[] getIdentifiedTraitTypeGroups() {
    return AbilityModelFetcher.fetch(hero).getTraitTypeList();
  }

  @Override
  public TraitMap getTraitMap() {
    return TraitModelFetcher.fetch(hero);
  }

  @Override
  public String getGroupNamePrefix() {
    return "AbilityGroup.";
  }

  @Override
  public String getTraitTypePrefix() {
    return "";
  }

  @Override
  public String getMarkerCommentKey() {
    return "Sheet.Comment.AbilityMobility";
  }

  @Override
  public String getHeaderKey() {
    return "Abilities";
  }
}
