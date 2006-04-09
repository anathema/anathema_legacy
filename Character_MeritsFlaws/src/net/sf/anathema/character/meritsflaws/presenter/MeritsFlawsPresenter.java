package net.sf.anathema.character.meritsflaws.presenter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.MultiValuePerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.character.meritsflaws.presenter.view.IMeritsFlawsTabView;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkDetailsView;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkView;
import net.sf.anathema.character.meritsflaws.view.MeritsFlawsTabView;
import net.sf.anathema.character.meritsflaws.view.MultiValuePerkDetailsView;
import net.sf.anathema.character.meritsflaws.view.NullPerkDetailsView;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.resources.IResources;

public class MeritsFlawsPresenter {

  private final IMeritsFlawsTabView view;
  private final IMeritsFlawsModel model;
  private final IResources resources;

  public MeritsFlawsPresenter(
      MeritsFlawsTabView meritsFlawsTabView,
      IMeritsFlawsAdditionalModel model,
      IResources resources) {
    this.view = meritsFlawsTabView;
    this.model = model.getMeritsFlawsModel();
    this.resources = resources;
  }

  public void initPresentation() {
    final IPerkView meritsView = view.addPerkView();
    setFilter(null, null);
    setAvailablePerks(meritsView);
    meritsView.addPerkListener(new IPerkListener() {

      public void perkAdded(Object selectedObject, Object detailsViewObject) {
        IPerk perk = (IPerk) selectedObject;
        final IPerkDetailsView detailsView = (IPerkDetailsView) detailsViewObject;
        final IQualitySelection<IPerk>[] selection = new IQualitySelection[1];
        perk.accept(new IPerkVisitor() {
          public void visitMultiValuePerk(MultiValuePerk visitedPerk) {
            selection[0] = createMultiValuePerkSelection(visitedPerk, detailsView);
          }
        });
        model.addQualitySelection(selection[0]);
      }

      public void perkSelected(Object object) {
        IPerk perk = (IPerk) object;
        if (model.isSelectable(perk)) {
          model.setCurrentQuality(perk);
          createDetailsView(meritsView, perk);
        }
        meritsView.setAvailableListSelection(model.getCurrentQuality());
      }

      public void filterChanged(Object type, Object category) {
        setFilter(type, category);
        setAvailablePerks(meritsView);
      }

      public void perkRemoved(Object selection) {
        IQualitySelection<IPerk> perkSelection = (IQualitySelection<IPerk>) selection;
        model.removeQualitySelection(perkSelection);
      }

      public void selectionSelected(Object perkSelection) {
        meritsView.setRemoveEnabled(perkSelection != null);
      }
    });
    model.addModelChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        IQualitySelection<IPerk>[] selectedPerks = model.getSelectedQualities();
        setAvailablePerks(meritsView);
        meritsView.setSelectedPerks(selectedPerks);
        model.setCurrentQuality(null);
        createDetailsView(meritsView, null);
      }
    });
    model.addCharacterChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        createDetailsView(meritsView, model.getCurrentQuality());
      }
    });
    meritsView.setSelectedPerks(model.getSelectedQualities());
    meritsView.setRemoveEnabled(false);
  }

  private void setAvailablePerks(final IPerkView meritsView) {
    IPerk[] availablePerks = model.getAvailableQualities();
    IPerk[] sortedPerks = new IPerk[availablePerks.length];
    I18nedIdentificateSorter<IPerk> sorter = createSorter();
    sorter.sortAscending(availablePerks, sortedPerks, resources);
    meritsView.setAvailablePerks(sortedPerks);
    meritsView.setPerkDetails(new NullPerkDetailsView(resources.getString("Perks.Details.NullText"))); //$NON-NLS-1$
  }

  private void setFilter(Object type, Object category) {
    PerkType perkType = (PerkType) type;
    PerkCategory perkCategory = (PerkCategory) category;
    model.setCurrentFilter(perkType, perkCategory);
  }

  private I18nedIdentificateSorter<IPerk> createSorter() {
    I18nedIdentificateSorter<IPerk> sorter = new I18nedIdentificateSorter<IPerk>() {
      @Override
      protected String getString(final IResources sorterResources, IPerk perk) {
        return sorterResources.getString(perk.getType().getId() + "." //$NON-NLS-1$
            + perk.getCategory().getId()
            + "." //$NON-NLS-1$
            + perk.getId());
      }
    };
    return sorter;
  }

  private IQualitySelection<IPerk> createMultiValuePerkSelection(MultiValuePerk perk, IPerkDetailsView detailsView) {
    return new QualitySelection<IPerk>(perk, detailsView.getSelectedValue(), !(model.isCharacterExperienced()));
  }

  private void createDetailsView(final IPerkView meritsView, IPerk perk) {
    final IPerkDetailsView[] detailsView = new IPerkDetailsView[1];
    if (perk == null) {
      detailsView[0] = new NullPerkDetailsView(resources.getString("Perks.Details.NullText")); //$NON-NLS-1$
    }
    else {
      perk.accept(new IPerkVisitor() {
        public void visitMultiValuePerk(MultiValuePerk visitedPerk) {
          Integer[] pointValues = visitedPerk.getPointValues(model.getBasicCharacterData());
          detailsView[0] = createMultiValuePerkDetailsView(pointValues);
        }
      });
    }
    setDetailsView(meritsView, detailsView[0]);
  }

  private void setDetailsView(final IPerkView meritsView, final IPerkDetailsView detailsView) {
    detailsView.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        meritsView.setAddEnabled(detailsView.isComplete());
      }
    });
    meritsView.setPerkDetails(detailsView);
    meritsView.setAddEnabled(detailsView.isComplete());
  }

  private IPerkDetailsView createMultiValuePerkDetailsView(Integer[] pointValues) {
    return new MultiValuePerkDetailsView(pointValues, resources.getString("Perks.Details.PointValue")); //$NON-NLS-1$
  }
}