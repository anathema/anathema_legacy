package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.model.gift.AttributeEnhancingGift;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftVisitorAdapter;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.MutationVisitorAdapter;
import net.sf.anathema.character.mutations.model.types.AttributeEnhancingMutation;

public class BeastformGenericTraitCollection implements IGenericTraitCollection {

  private final IBeastformTraitCollection beastmanCollection;
  private final IGenericTraitCollection collection;
  private final IQualityModel<?> model;

  public BeastformGenericTraitCollection(
      IGenericTraitCollection collection,
      IBeastformTraitCollection beastmanCollection,
      IQualityModel<?> model) {
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
    for (IQualitySelection<?> selection : model.getSelectedQualities()) {
    	if (selection.getQuality() instanceof IGift)
    	{
	      ((IGift)selection.getQuality()).accept(new GiftVisitorAdapter() {
	        @Override
	        public void visitAttributeEnhancingGift(AttributeEnhancingGift gift) {
	          if (gift.getAttributeType() == type) {
	            value[0] += gift.getBonus();
	          }
	        }
	      });
    	}
    	if (selection.getQuality() instanceof IMutation)
    	{
	      ((IMutation)selection.getQuality()).accept(new MutationVisitorAdapter() {
	        @Override
	        public void visitAttributeEnhancingMutation(AttributeEnhancingMutation mutation) {
	          if (mutation.getAttributeType() == type) {
	            value[0] += mutation.getBonus();
	          }
	        }
	      });
    	}
    }
    return new ValuedTraitType(type, value[0]);
  }

  public IFavorableGenericTrait getFavorableTrait(final ITraitType type) {
    throw new UnsupportedOperationException("Irrelevant for DBTGenericTraitCollection."); //$NON-NLS-1$
  }

  public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
    throw new UnsupportedOperationException("Irrelevant for DBTGenericTraitCollection."); //$NON-NLS-1$
  }

  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return collection.isFavoredOrCasteTrait(type);
  }
}