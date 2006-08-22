package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.model.gift.AttributeEnhancingGift;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftVisitorAdapter;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;

public class BeastformGenericTraitCollection implements IGenericTraitCollection {

  private final IBeastformTraitCollection beastmanCollection;
  private final IGenericTraitCollection collection;
  private final IGiftModel model;

  public BeastformGenericTraitCollection(
      IGenericTraitCollection collection,
      IBeastformTraitCollection beastmanCollection,
      IGiftModel model) {
    this.collection = collection;
    this.beastmanCollection = beastmanCollection;
    this.model = model;
  }

  public IGenericTrait getTrait(final ITraitType type) {
    final IGenericTrait attribute;
    IBeastformAttribute beastformattribute = beastmanCollection.getDeadlyBeastmanAttribute(type);
    if (beastformattribute == null) {
      attribute = collection.getTrait(type);
    }
    else {
      attribute = beastformattribute.getTrait();
    }
    final int[] value = new int[1];
    value[0] = attribute.getCurrentValue();
    for (IQualitySelection<IGift> selection : model.getSelectedQualities()) {
      selection.getQuality().accept(new GiftVisitorAdapter() {
        @Override
        public void visitAttributeEnhancingGift(AttributeEnhancingGift gift) {
          if (gift.getAttributeType() == type) {
            value[0] += gift.getBonus();
          }
        }
      });
    }
    return new ValuedTraitType(type, value[0]);
  }

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    throw new UnsupportedOperationException("Irrelevant for DBTGenericTraitCollection."); //$NON-NLS-1$
  }
  
  public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
    throw new UnsupportedOperationException("Irrelevant for DBTGenericTraitCollection."); //$NON-NLS-1$
  }
  
  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return false;
  }
}