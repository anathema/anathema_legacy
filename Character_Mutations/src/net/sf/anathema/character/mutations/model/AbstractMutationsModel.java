package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.quality.model.AbstractQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

import java.util.ArrayList;

public abstract class AbstractMutationsModel extends AbstractQualityModel<IMutation> implements IMutationsModel {
  protected IMutation[] allMutations;
  private final ChangeControl overviewControl = new ChangeControl();

  public AbstractMutationsModel(final ICharacterModelContext context) {
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

  public IMutation[] getAvailableQualities() {
    ArrayList<IMutation> availableMutations = new ArrayList<IMutation>();
    for (IMutation Mutation : allMutations) {
      if (isSelected(Mutation)) {
        continue;
      }
      availableMutations.add(Mutation);
    }
    return availableMutations.toArray(new IMutation[availableMutations.size()]);
  }

  public boolean isCreationLearnedSelectionInExperiencedCharacter(IQualitySelection<IMutation> selection) {
    return selection.isCreationActive() && getContext().getBasicCharacterContext().isExperienced();
  }

  public IMutation getMutationById(String MutationId) {
    for (IMutation Mutation : allMutations) {
      if (Mutation.getId().equals(MutationId)) {
        return Mutation;
      }
    }
    throw new IllegalArgumentException("No Mutation found for id \"" //$NON-NLS-1$
                                       + MutationId + "\"."); //$NON-NLS-1$
  }

  public void addOverviewChangedListener(IChangeListener listener) {
    overviewControl.addChangeListener(listener);
  }
}
