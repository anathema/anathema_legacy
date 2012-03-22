package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.MutationVisitorAdapter;
import net.sf.anathema.character.mutations.model.types.AttributeEnhancingMutation;

public class BeastformGenericTraitCollection implements IGenericTraitCollection {

  private final IBeastformTraitCollection beastmanCollection;
  private final IGenericTraitCollection collection;
  private final IQualityModel<?> model;

  public BeastformGenericTraitCollection(IGenericTraitCollection collection,
                                         IBeastformTraitCollection beastmanCollection, IQualityModel<?> model) {
    this.collection = collection;
    this.beastmanCollection = beastmanCollection;
    this.model = model;
  }

  @Override
  public IGenericTrait getTrait(final ITraitType type) {
    final IGenericTrait attribute;
    IBeastformAttribute beastformattribute = beastmanCollection.getDeadlyBeastmanAttribute(type);
    if (beastformattribute == null) {
      attribute = collection.getTrait(type);
    } else {
      attribute = beastformattribute.getTrait();
    }
    final int[] value = new int[1];
    value[0] = attribute.getCurrentValue();
    for (IQualitySelection<?> selection : model.getSelectedQualities()) {
      ((IMutation) selection.getQuality()).accept(new MutationVisitorAdapter() {
        @Override
        public void visitAttributeEnhancingMutation(AttributeEnhancingMutation mutation) {
          if (mutation.getAttributeType() == type) {
            value[0] += mutation.getBonus();
          }
        }
      });
    }
    return new ValuedTraitType(type, value[0]);
  }

  @Override
  public IFavorableGenericTrait getFavorableTrait(final ITraitType type) {
    throw new UnsupportedOperationException("Irrelevant for DBTGenericTraitCollection."); //$NON-NLS-1$
  }

  @Override
  public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
    throw new UnsupportedOperationException("Irrelevant for DBTGenericTraitCollection."); //$NON-NLS-1$
  }

  @Override
  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return collection.isFavoredOrCasteTrait(type);
  }
}