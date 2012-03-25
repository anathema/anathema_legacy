package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.GenericCharmStats;

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
    List<IMagicStats> genericCharmStats = new ArrayList<IMagicStats>();
    ICharm[] charms = CharmCache.getInstance().getCharms(character.getTemplate().getTemplateType().getCharacterType());
    for (ICharm charm : charms) {
      if (charm.isInstanceOfGenericCharm()) {
        IMagicStats stats = new GenericCharmStats(charm, character);
        if (!genericCharmStats.contains(stats)) genericCharmStats.add(stats);
      }
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

  public static List<ITraitType> getGenericCharmTraits(IGenericCharacter character) {
    FavoringTraitType type = character.getTemplate().getMagicTemplate().getFavoringTraitType();
    List<ITraitType> traits = new ArrayList<ITraitType>();
    IIdentifiedTraitTypeGroup[] list = null;
    if (type == FavoringTraitType.AbilityType) {
      list = character.getAbilityTypeGroups();
    }
    if (type == FavoringTraitType.AttributeType) {
      list = character.getAttributeTypeGroups();
    }
    if (type == FavoringTraitType.YoziType) {
      list = character.getYoziTypeGroups();
    }
    for (ITraitTypeGroup group : list) {
      Collections.addAll(traits, group.getAllGroupTypes());
    }
    return traits;
  }
}
