package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.TraitFavorization;
import net.sf.anathema.character.library.trait.rules.IFavorableTraitRules;

public class FavorableTrait extends DefaultTrait implements IFavorableTrait {

  private TraitFavorization traitFavorization;
  private final IBasicCharacterData basicData;
  private ICharacterChangeListener changeListener = new DedicatedCharacterChangeAdapter() {
    @Override
    public void casteChanged() {
      resetCurrentValue();
      if (getFavorization().isCaste() != isSupportedCasteType(basicData.getCasteType())) {
        updateFavorableState();
      }
    }
  };

  public FavorableTrait(
      IFavorableTraitRules traitRules,
      ICasteType<? extends ICasteTypeVisitor>  caste,
      ITraitValueStrategy valueStrategy,
      IBasicCharacterData basicData,
      ICharacterListening listening,
      IValueChangeChecker valueChangeChecker,
      IIncrementChecker favoredIncrementChecker) {
    super(traitRules, valueStrategy, valueChangeChecker);
    this.basicData = basicData;
    this.traitFavorization = new TraitFavorization(caste, favoredIncrementChecker, this, traitRules.isRequiredFavored());
    listening.addChangeListener(changeListener);
    updateFavorableState();
  }

  public ITraitFavorization getFavorization() {
    return traitFavorization;
  }

  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  private void updateFavorableState() {
    getFavorization().setCaste(isSupportedCasteType(basicData.getCasteType()));
  }

  public boolean isSupportedCasteType(ICasteType<? extends ICasteTypeVisitor>  casteType) {
    ICasteType<? extends ICasteTypeVisitor>  favorizationCaste = getFavorization().getCaste();
    return favorizationCaste != null && favorizationCaste == casteType;
  }

  @Override
  public void setCreationValue(int value) {
    if (getFavorization().getFavorableState() == FavorableState.Favored) {
      value = Math.max(value, 1);
    }
    super.setCreationValue(value);
  }
}