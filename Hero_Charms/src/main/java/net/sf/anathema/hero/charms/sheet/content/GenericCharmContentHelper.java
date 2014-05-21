package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.template.magic.AbilityFavoringType;
import net.sf.anathema.character.main.template.magic.AttributeFavoringType;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.character.main.traits.lists.TraitTypeList;
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

  private IdentifiedTraitTypeList[] getCharmTraitGroups() {
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    if (type.equals(new AbilityFavoringType())) {
      return AbilityModelFetcher.fetch(hero).getTraitTypeList();
    }
    if (type.equals(new AttributeFavoringType())) {
      return AttributesModelFetcher.fetch(hero).getTraitTypeList();
    }
    return new IdentifiedTraitTypeList[0];
  }

  public List<TraitType> getGenericCharmTraits() {
    List<TraitType> traits = new ArrayList<>();
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    for (TraitTypeList group : getCharmTraitGroups()) {
      traits.addAll(group.getAll());
    }
    if (traits.isEmpty()) {
      Collections.addAll(traits, type.getTraitTypesForGenericCharms());
    }
    return traits;
  }

  public int getDisplayedGenericCharmCount() {
    int count = 0;
    CharmContentHelper helper = new CharmContentHelper(hero);
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
