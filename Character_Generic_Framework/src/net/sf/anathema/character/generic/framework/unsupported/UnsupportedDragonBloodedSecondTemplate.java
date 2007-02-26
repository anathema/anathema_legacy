package net.sf.anathema.character.generic.framework.unsupported;

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
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;

public class UnsupportedDragonBloodedSecondTemplate extends AbstractUnsupportedExaltTemplate {
  private final ICharmTemplate charmTemplate;
  private final IPresentationProperties presentationProperties;

  public UnsupportedDragonBloodedSecondTemplate(ICharmCache charmProvider) {
    ICharmSet charmSet = CharmSet.createRegularCharmSet(charmProvider, CharacterType.DB, ExaltedEdition.SecondEdition);
    this.charmTemplate = new CharmTemplate(new DefaultMartialArtsRules(MartialArtsLevel.Mortal), charmSet);
    this.presentationProperties = new DragonBloodedPresentationProperties(getTemplateType());
  }

  @Override
  public IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  @Override
  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] { new GroupedTraitType(AbilityType.Linguistics, "Air", "Air"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Lore, "Air", "Air"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Occult, "Air", "Air"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Stealth, "Air", "Air"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Thrown, "Air", "Air"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Awareness, "Earth", "Earth"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Craft, "Earth", "Earth"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Endurance, "Earth", "Earth"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.MartialArts, "Earth", "Earth"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Resistance, "Earth", "Earth"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Athletics, "Fire", "Fire"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Dodge, "Fire", "Fire"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Melee, "Fire", "Fire"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Presence, "Fire", "Fire"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Socialize, "Fire", "Fire"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Brawl, "Water", "Water"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Bureaucracy, "Water", "Water"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Investigation, "Water", "Water"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Larceny, "Water", "Water"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Sail, "Water", "Water"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Archery, "Wood", "Wood"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Medicine, "Wood", "Wood"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Performance, "Wood", "Wood"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Ride, "Wood", "Wood"), //$NON-NLS-1$ //$NON-NLS-2$
        new GroupedTraitType(AbilityType.Survival, "Wood", "Wood") }; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public IMagicTemplate getMagicTemplate() {
    return new IMagicTemplate() {

      public boolean canBuyFromFreePicks(IMagic magic) {
        return true;
      }

      public ISpellMagicTemplate getSpellMagic() {
        throw new UnsupportedOperationException("Spell Magic not yet implemented for 2E Dragon Blooded."); //$NON-NLS-1$
      }

      public ICharmTemplate getCharmTemplate() {
        return charmTemplate;
      }

      public FavoringTraitType getFavoringTraitType() {
        return FavoringTraitType.AbilityType;
      }
    };
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  @Override
  public ITemplateType getTemplateType() {
    return new TemplateType(CharacterType.DB);
  }
}