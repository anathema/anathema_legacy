package net.sf.anathema.hero.abilities.sheet.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.magic.AbilityFavoringType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.reporting.pdf.content.traits.FavorableTraitContent;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.Arrays;
import java.util.List;

public class AbilitiesContent extends FavorableTraitContent {

  private Hero hero;

  public AbilitiesContent(Hero hero, IGenericCharacter character, Resources resources) {
    super(hero, character, resources);
    this.hero = hero;
  }

  @Override
  public List<? extends TraitType> getMarkedTraitTypes() {
    return Arrays.asList(AbilityType.Athletics, AbilityType.Dodge, AbilityType.Larceny, AbilityType.Ride, AbilityType.Stealth);
  }

  @Override
  public boolean shouldShowExcellencies() {
    return hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType().equals(new AbilityFavoringType());
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups() {
    return AbilityModelFetcher.fetch(hero).getAbilityTypeGroups();
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
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
  public String getExcellencyCommentKey() {
    return "Sheet.Comment.AbilityExcellency";
  }

  @Override
  public String getHeaderKey() {
    return "Abilities";
  }
}
