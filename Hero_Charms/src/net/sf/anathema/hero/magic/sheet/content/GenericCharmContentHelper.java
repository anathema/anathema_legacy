package net.sf.anathema.hero.magic.sheet.content;

import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.character.main.template.magic.AbilityFavoringType;
import net.sf.anathema.character.main.template.magic.AttributeFavoringType;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.main.traits.groups.ITraitTypeGroup;
import net.sf.anathema.hero.abilities.model.AbilityModelFetcher;
import net.sf.anathema.hero.attributes.model.AttributesModelFetcher;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericCharmContentHelper {

  private Hero hero;

  public GenericCharmContentHelper(Hero hero) {
    this.hero = hero;
  }

  private IIdentifiedTraitTypeGroup[] getCharmTraitGroups() {
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    if (type.equals(new AbilityFavoringType())) {
      return AbilityModelFetcher.fetch(hero).getAbilityTypeGroups();
    }
    if (type.equals(new AttributeFavoringType())) {
      return AttributesModelFetcher.fetch(hero).getAttributeTypeGroups();
    }
    return new IIdentifiedTraitTypeGroup[0];
  }

  public List<TraitType> getGenericCharmTraits() {
    List<TraitType> traits = new ArrayList<>();
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    for (ITraitTypeGroup group : getCharmTraitGroups()) {
      Collections.addAll(traits, group.getAllGroupTypes());
    }
    if (traits.isEmpty()) {
      Collections.addAll(traits, type.getTraitTypesForGenericCharms());
    }
    return traits;
  }

  public int getDisplayedGenericCharmCount() {
    int count = 0;
    MagicContentHelper helper = new MagicContentHelper(hero);
    for (IMagicStats stats : helper.getGenericCharmStats()) {
      if (helper.shouldShowCharm(stats)) {
        count++;
      }
    }
    return count;
  }

  public boolean hasDisplayedGenericCharms() {
    return getDisplayedGenericCharmCount() > 0;
  }
}
