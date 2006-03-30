package net.sf.anathema.character.generic.framework.unsupported;

import net.sf.anathema.character.generic.impl.exalt.AbstractUnsupportedExaltTemplate;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.template.magic.CharmSet;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.ICharmSet;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.GenericAbilityUtilities;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class UnsupportedDragonKingTemplate extends AbstractUnsupportedExaltTemplate {

  private ICharmTemplate charmTemplate;
  private DragonKingPresentationProperties presentationProperties;

  public UnsupportedDragonKingTemplate(ICharmCache charmProvider) throws PersistenceException {
    ICharmSet charmSet = CharmSet.createRegularCharmSet(charmProvider, CharacterType.DRAGON_KING);
    charmTemplate = new CharmTemplate(MartialArtsLevel.Mortal, false, charmSet);
    this.presentationProperties = new DragonKingPresentationProperties(getTemplateType());
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return GenericAbilityUtilities.createDefaultAbilityGroups();
  }

  public IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  public ITemplateType getTemplateType() {
    return new TemplateType(CharacterType.DRAGON_KING);
  }

  public IMagicTemplate getMagicTemplate() {
    return new IMagicTemplate() {
      public boolean canBuyFromFreePicks(IMagic magic) {
        return true;
      }

      public ISpellMagicTemplate getSpellMagic() {
        throw new UnsupportedOperationException("Spell Magic not yet implemented for Dragon Kings."); //$NON-NLS-1$
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