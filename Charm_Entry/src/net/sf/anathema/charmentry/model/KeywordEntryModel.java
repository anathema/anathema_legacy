package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.IKeywordEntryModel;
import net.sf.anathema.lib.util.IIdentificate;

import static net.sf.anathema.lib.lang.AnathemaStringUtilities.bothNullOrEquals;

public class KeywordEntryModel extends AbstractRemovableEntryModel<ICharmAttribute> implements IKeywordEntryModel {

  private IIdentificate keyword;
  private final IConfigurableCharmData charmData;

  public KeywordEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  @Override
  protected ICharmAttribute createEntry() {
    return new CharmAttribute(keyword.getId(), true);
  }

  @Override
  public ICharmAttribute commitSelection() {
    ICharmAttribute charmAttribute = super.commitSelection();
    fireEntryChanged();
    charmData.addAttribute(charmAttribute);
    return charmAttribute;
  }

  @Override
  public void removeEntry(ICharmAttribute entry) {
    super.removeEntry(entry);
    charmData.removeAttribute(entry);
  }
  
  @Override
  protected boolean isEntryAllowed() {
    if (keyword == null) {
      return false;
    }
    for (ICharmAttribute attribute : getEntries()) {
      if (bothNullOrEquals(attribute.getId(), keyword.getId())) {
        return false;
      }
    }
    return true;
  }

  public void setCurrentKeyword(IIdentificate keyword) {
    this.keyword = keyword;
    fireEntryChanged();
  }

  public void clear() {
    for (ICharmAttribute entry : getEntries()) {
      removeEntry(entry);
    }
  }

  public IIdentificate[] getAvailableKeywords() {
    return new IIdentificate[] {
        ICharmData.ALLOWS_CELESTIAL_ATTRIBUTE,
        ICharmData.FORM_ATTRIBUTE,
        ICharmData.NO_STYLE_ATTRIBUTE,
        ICharmData.NOT_ALIEN_LEARNABLE,
        ICharmData.UNRESTRICTED_ATTRIBUTE,
        IExtendedCharmData.COMBO_BASIC_ATTRIBUTE,
        IExtendedCharmData.COMBO_OK_ATTRIBUTE,
        IExtendedCharmData.COMPULSION_ATTRIBUTE,
        IExtendedCharmData.COUNTERATTACK_ATTRIBUTE,
        IExtendedCharmData.CRIPPLING_ATTRIBUTE,
        IExtendedCharmData.EMOTION_ATTRIBUTE,
        IExtendedCharmData.EXCLUSIVE_ATTRIBUTE,
        IExtendedCharmData.HOLY_ATTRIBUTE,
        IExtendedCharmData.ILLUSION_ATTRIBUTE,
        IExtendedCharmData.KNOCKBACK_ATTRIBUTE,
        IExtendedCharmData.MANDATE_ATTRIBUTE,
        IExtendedCharmData.OBVIOUS_ATTRIBUTE,
        IExtendedCharmData.POISON_ATTRIBUTE,
        IExtendedCharmData.SERVITUDE_ATTRIBUTE,
        IExtendedCharmData.SHAPING_ATTRIBUTE,
        IExtendedCharmData.SICKNESS_ATTRIBUTE,
        IExtendedCharmData.SOCIAL_ATTRIBUTE,
        IExtendedCharmData.STACKABLE_ATTRIBUTE,
        IExtendedCharmData.TOUCH_ATTRIBUTE,
        IExtendedCharmData.TRAINING_ATTRIBUTE,
        IExtendedCharmData.WAR_ATTRIBUTE };
  }
}