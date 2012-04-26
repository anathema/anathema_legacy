package net.sf.anathema.character.mutations.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.character.mutations.view.MutationViewLearnProperties;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.IResources;

public class MutationsPresenter implements Presenter {

  private final IMutationsView view;
  private final IMutationsModel model;
  private final IResources resources;

  public MutationsPresenter(IMutationsView view, IMutationsModel model, IResources resources) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  public IMutationsView getView() {
    return view;
  }

  protected IMutationsModel getModel() {
    return model;
  }

  @Override
  public void initPresentation() {
    final IMagicLearnView mutationView = view.addMutationsView(new MutationViewLearnProperties(resources, model));

    model.designOverview(view.createOverview(resources.getString("Mutations.Overview")), resources);

    mutationView.addMagicViewListener(new IMagicViewListener() {
      @Override
      @SuppressWarnings("unchecked")
      public void magicRemoved(Object[] removedMagic) {
        model.removeQualitySelection((IQualitySelection<IMutation>) removedMagic[0]);
        updateOverview();
      }

      @Override
      public void magicAdded(Object[] addedMagic) {
        IMutation mutation = (IMutation) addedMagic[0];
        IQualitySelection<IMutation> selection = new QualitySelection<IMutation>(mutation, mutation.getCost(), !model.isCharacterExperienced());
        model.addQualitySelection(selection);
        updateOverview();
      }
    });
    model.addModelChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateMutationsViews(mutationView);
      }
    });
    model.addOverviewChangedListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateMutationsViews(mutationView);
        updateOverview();
      }
    });
    updateMutationsViews(mutationView);
    updateOverview();
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateOverview();
      }
    });
  }

  public void updateOverview() {
    model.updateOverview();
    hideOverviewForExperiencedCharacter();
  }

  private void hideOverviewForExperiencedCharacter() {
    if (model.isCharacterExperienced()){
      view.hideOverview();
    }
  }

  private void updateMutationsViews( IMagicLearnView giftView) {
    setAvailableMutations(model, giftView);
    IQualitySelection<IMutation>[] selectedMutations = model.getSelectedQualities();
    giftView.setLearnedMagic(selectedMutations);
  }

  private void setAvailableMutations( IQualityModel<IMutation> giftModel, IMagicLearnView giftView) {
    IMutation[] availablePerks = giftModel.getAvailableQualities();
    IMutation[] sortedPerks = new IMutation[availablePerks.length];
    I18nedIdentificateSorter<IMutation> sorter = createSorter();
    sorter.sortAscending(availablePerks, sortedPerks, resources);
    giftView.setMagicOptions(sortedPerks);
  }

  private I18nedIdentificateSorter<IMutation> createSorter() {
    return new I18nedIdentificateSorter<IMutation>() {
      @Override
      protected String getString(IResources sorterResources, IMutation mutation) {
        String typeString = sorterResources.getString("Mutations.Type." + mutation.getType().getId());
        String mutationString = sorterResources.getString("Mutations.Mutation." //$NON-NLS-1$
                                                          + mutation.getId());
        return "(" + typeString + ") " + mutationString;
      }
    };
  }
}
