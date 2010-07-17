package net.sf.anathema.character.abyssal.template;

import net.sf.anathema.character.generic.impl.exalt.AbstractUnsupportedExaltTemplate;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.magic.CharmSet;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.impl.template.magic.ICharmSet;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.abyssal.caste.*;

public class UnsupportedAbyssal2ndTemplate extends AbstractUnsupportedExaltTemplate {

  private final ICharmTemplate charmTemplate;
  private final IPresentationProperties presentationProperties;

  public UnsupportedAbyssal2ndTemplate(ICharmCache charmProvider) {
    ICharmSet charmSet = CharmSet.createRegularCharmSet(
        charmProvider,
        CharacterType.ABYSSAL,
        ExaltedEdition.SecondEdition);
    this.charmTemplate = new CharmTemplate(new DefaultMartialArtsRules(MartialArtsLevel.Celestial), charmSet);
    this.presentationProperties = new AbyssalPresentationProperties("Should.Not.Be.Displayed");
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Resistance, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Ride, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Sail, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Survival, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Thrown, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Craft, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Dodge, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Linguistics, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Performance, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Socialize, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Archery, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Integrity, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Melee, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Presence, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.War, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Investigation, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Larceny, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Lore, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Occult, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Stealth, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Athletics, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Awareness, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Bureaucracy, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.MartialArts, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Medicine, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()) };
  }

  public IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  public ITemplateType getTemplateType() {
    return LoyalAbyssalTemplate.TEMPLATE_TYPE;
  }

  @Override
  public IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  public IMagicTemplate getMagicTemplate() {
    return new IMagicTemplate() {
      public boolean canBuyFromFreePicks(IMagic magic) {
        return true;
      }

      public ISpellMagicTemplate getSpellMagic() {
        throw new UnsupportedOperationException("Spell Magic not yet implemented for 2E Abyssals."); //$NON-NLS-1$
      }

      public ICharmTemplate getCharmTemplate() {
        return charmTemplate;
      }

      public FavoringTraitType getFavoringTraitType() {
        return FavoringTraitType.AbilityType;
      }
    };
  }
}