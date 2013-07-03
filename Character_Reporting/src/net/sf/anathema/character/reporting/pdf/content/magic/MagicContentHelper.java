package net.sf.anathema.character.reporting.pdf.content.magic;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.template.magic.AbilityFavoringType;
import net.sf.anathema.character.generic.template.magic.AttributeFavoringType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModelFetcher;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.model.attributes.AttributesModelFetcher;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.model.traits.GenericTraitCollectionFacade;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.GenericCharmStats;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MagicContentHelper {

  private final Hero hero;

  public MagicContentHelper(Hero hero) {
    this.hero = hero;
  }

  public IGenericTraitCollection getTraitCollection() {
    return new GenericTraitCollectionFacade(TraitModelFetcher.fetch(hero));
  }

  public Specialty[] getSpecialties(TraitType traitType) {
    SpecialtiesModel specialtyConfiguration = SpecialtiesModelFetcher.fetch(hero);
    ISubTraitContainer specialtiesContainer = specialtyConfiguration.getSpecialtiesContainer(traitType);
    return specialtiesContainer.getSubTraits();
  }

  public List<IMagic> getAllLearnedMagic() {
    List<IMagic> magicList = new ArrayList<>();
    magicList.addAll(Arrays.asList(getLearnedCharms()));
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    magicList.addAll(Arrays.asList(SpellsModelFetcher.fetch(hero).getLearnedSpells(experienced)));
    return magicList;
  }

  public boolean isMultipleEffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof IMultipleEffectCharmConfiguration && !(charmConfiguration instanceof ISubeffectCharmConfiguration);
  }

  public String[] getLearnedEffects(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    if (!(charmConfiguration instanceof IMultipleEffectCharmConfiguration)) {
      return new String[0];
    }
    IMultipleEffectCharmConfiguration configuration = (IMultipleEffectCharmConfiguration) charmConfiguration;
    List<String> learnedEffectIds = new ArrayList<>();
    for (ISubeffect effect : configuration.getEffects()) {
      if (effect.isLearned()) {
        learnedEffectIds.add(effect.getId());
      }
    }
    return learnedEffectIds.toArray(new String[learnedEffectIds.size()]);
  }

  public ICharm[] getGenericCharms() {
    List<ICharm> genericCharms = new ArrayList<>();
    for (ILearningCharmGroup group : CharmsModelFetcher.fetch(hero).getAllGroups()) {
      for (ICharm charm : group.getAllCharms()) {
        if (charm.isInstanceOfGenericCharm() &&
            charm.getCharacterType().equals(hero.getTemplate().getTemplateType().getCharacterType())) {
          genericCharms.add(charm);
        }
      }
    }
    return genericCharms.toArray(new ICharm[genericCharms.size()]);
  }

  private ICharm[] getLearnedCharms() {
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    return CharmsModelFetcher.fetch(hero).getLearnedCharms(experienced);
  }

  public boolean isSubeffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = CharmsModelFetcher.fetch(hero).getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof ISubeffectCharmConfiguration;
  }

  public int getLearnCount(ICharm charm) {
    return getLearnCount(charm, CharmsModelFetcher.fetch(hero));
  }

  private int getLearnCount(ICharm charm, CharmsModel configuration) {
    ISpecialCharmConfiguration specialCharmConfiguration = configuration.getSpecialCharmConfiguration(charm.getId());
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCurrentLearnCount();
    }
    return configuration.isLearned(charm) ? 1 : 0;
  }

  public boolean shouldShowCharm(IMagicStats stats) {
    if (AnathemaCharacterPreferences.getDefaultPreferences().printAllGenerics()) {
      return true;
    }
    for (IMagic magic : getAllLearnedMagic()) {
      if (magic.getId().startsWith(stats.getName().getId())) return true;
    }
    return false;
  }

  public boolean isGenericCharmFor(ICharm charm) {
    IMagicStats[] genericCharmStats = getGenericCharmStats();
    String charmId = charm.getId();
    for (IMagicStats stat : genericCharmStats) {
      if (charmId.startsWith(stat.getName().getId())) {
        return true;
      }
    }
    return false;
  }

  public IMagicStats[] getGenericCharmStats() {
    List<IMagicStats> genericCharmStats = new ArrayList<>();
    for (ICharm charm : getGenericCharms()) {
      IMagicStats stats = new GenericCharmStats(charm, hero);
      if (!genericCharmStats.contains(stats)) genericCharmStats.add(stats);
    }
    return genericCharmStats.toArray(new IMagicStats[genericCharmStats.size()]);
  }

  public int getDisplayedGenericCharmCount() {
    int count = 0;
    for (IMagicStats stats : getGenericCharmStats()) {
      if (shouldShowCharm(stats)) {
        count++;
      }
    }
    return count;
  }

  public boolean hasDisplayedGenericCharms() {
    return getDisplayedGenericCharmCount() > 0;
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

  public boolean isCharmLearned(List<IMagic> allLearnedMagic, final String charmId) {
    Optional<? extends IMagic> optional = Iterables.tryFind(allLearnedMagic, new Predicate<IMagic>() {
      @Override
      public boolean apply(IMagic value) {
        return charmId.equals(value.getId());
      }
    });
    return optional.isPresent();
  }
}
