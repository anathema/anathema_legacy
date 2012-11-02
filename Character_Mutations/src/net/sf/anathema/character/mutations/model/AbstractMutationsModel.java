package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.quality.model.AbstractQualityModel;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;

public abstract class AbstractMutationsModel extends AbstractQualityModel<IMutation> implements IMutationsModel {
  protected IMutation[] allMutations;

  public AbstractMutationsModel(ICharacterModelContext context) {
    super(context);
  }

  @Override
  public boolean isSelectable(IMutation quality) {
    if (quality == null) {
      return false;
    }
    boolean prerequisitesFulfilled = quality.prerequisitesFulfilled(getSelectedQualities());
    return super.isSelectable(quality) && prerequisitesFulfilled;
  }

  @Override
  public IMutation[] getAvailableQualities() {
    ArrayList<IMutation> availableMutations = new ArrayList<>();
    for (IMutation Mutation : allMutations) {
      if (isSelected(Mutation)) {
        continue;
      }
      availableMutations.add(Mutation);
    }
    return availableMutations.toArray(new IMutation[availableMutations.size()]);
  }

  @Override
  public IMutation getMutationById(String MutationId) {
    for (IMutation Mutation : allMutations) {
      if (Mutation.getId().equals(MutationId)) {
        return Mutation;
      }
    }
    throw new IllegalArgumentException("No Mutation found for id \"" //$NON-NLS-1$
            + MutationId + "\"."); //$NON-NLS-1$
  }

  @Override
  public void addOverviewChangedListener(IChangeListener listener) {
    //nothing to do
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    getContext().getCharacterListening().addChangeListener(listener);
  }
}
