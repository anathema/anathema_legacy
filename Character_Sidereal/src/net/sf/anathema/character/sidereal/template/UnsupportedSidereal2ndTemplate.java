package net.sf.anathema.character.sidereal.template;

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
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.presentation.SiderealPresentationProperties;

public class UnsupportedSidereal2ndTemplate extends AbstractUnsupportedExaltTemplate {

  private final ICharmTemplate charmTemplate;
  private final IPresentationProperties presentationProperties;

  public UnsupportedSidereal2ndTemplate(ICharmCache charmProvider) {
    ICharmSet charmSet = CharmSet.createRegularCharmSet(
        charmProvider,
        CharacterType.SIDEREAL,
        ExaltedEdition.SecondEdition);
    this.charmTemplate = new CharmTemplate(new DefaultMartialArtsRules(MartialArtsLevel.Mortal), charmSet);
    this.presentationProperties = new SiderealPresentationProperties();
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Resistance, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Ride, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Sail, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Survival, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Thrown, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Craft, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Dodge, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Linguistics, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Performance, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Socialize, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Archery, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Integrity, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Melee, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Presence, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.War, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Investigation, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Larceny, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Lore, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Occult, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Stealth, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Athletics, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.Awareness, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.Bureaucracy, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.MartialArts, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.Medicine, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()) };
  }

  public IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  public ITemplateType getTemplateType() {
    return DefaultSiderealTemplate.TEMPLATE_TYPE;
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
        throw new UnsupportedOperationException("Spell Magic not yet implemented for 2E Sids."); //$NON-NLS-1$
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