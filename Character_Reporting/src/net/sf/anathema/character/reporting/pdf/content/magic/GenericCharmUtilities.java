package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.AbilityFavoringType;
import net.sf.anathema.character.generic.template.magic.AttributeFavoringType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.model.attributes.AttributesModelFetcher;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.GenericCharmStats;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericCharmUtilities {

  public static boolean shouldShowCharm(IMagicStats stats, IGenericCharacter character) {
    if (AnathemaCharacterPreferences.getDefaultPreferences().printAllGenerics()) {
      return true;
    }
    for (IMagic magic : character.getAllLearnedMagic()) {
      if (magic.getId().startsWith(stats.getName().getId())) return true;
    }
    return false;
  }

  public static boolean isGenericCharmFor(ICharm charm, IGenericCharacter character) {
    IMagicStats[] genericCharmStats = getGenericCharmStats(character);
    String charmId = charm.getId();
    for (IMagicStats stat : genericCharmStats) {
      if (charmId.startsWith(stat.getName().getId())) {
        return true;
      }
    }
    return false;
  }

  public static IMagicStats[] getGenericCharmStats(IGenericCharacter character) {
    List<IMagicStats> genericCharmStats = new ArrayList<>();
    ICharm[] charms = character.getGenericCharms();
    for (ICharm charm : charms) {
      IMagicStats stats = new GenericCharmStats(charm, character);
      if (!genericCharmStats.contains(stats)) genericCharmStats.add(stats);
    }
    return genericCharmStats.toArray(new IMagicStats[genericCharmStats.size()]);
  }

  public static int getDisplayedGenericCharmCount(IGenericCharacter character) {
    int count = 0;
    for (IMagicStats stats : getGenericCharmStats(character)) {
      if (shouldShowCharm(stats, character)) {
        count++;
      }
    }
    return count;
  }

  public static boolean hasDisplayedGenericCharms(ReportSession session) {
    return getDisplayedGenericCharmCount(session.getCharacter()) > 0;
  }

  public static List<TraitType> getGenericCharmTraits(Hero hero) {
    List<TraitType> traits = new ArrayList<>();
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    for (ITraitTypeGroup group : getCharmTraitGroups(hero)) {
      Collections.addAll(traits, group.getAllGroupTypes());
    }
    if (traits.isEmpty()) {
      Collections.addAll(traits, type.getTraitTypesForGenericCharms());
    }
    return traits;
  }

  private static IIdentifiedTraitTypeGroup[] getCharmTraitGroups(Hero  hero) {
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    if (type.equals(new AbilityFavoringType())) {
      return AbilityModelFetcher.fetch(hero).getAbilityTypeGroups();
    }
    if (type.equals(new AttributeFavoringType())) {
      return AttributesModelFetcher.fetch(hero).getAttributeTypeGroups();
    }
    return new IIdentifiedTraitTypeGroup[0];
  }
}