package net.sf.anathema.character.lunar.renown.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.lunar.renown.view.RenownView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class RenownPresenter extends AbstractTraitPresenter implements IPresenter {

  private final IResources resources;
  private final RenownView view;
  private final IRenownModel model;

  public RenownPresenter(IRenownModel model, RenownView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    final IValueView<Integer> totalView = view.addTotalView(resources.getString("Lunar.Renown.RenownTotal")); //$NON-NLS-1$
    initTraitPresentation(totalView);
    initFacePresentation();
    final ILabelledAlotmentView overviewView = initOverviewPresentation();
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        view.showOverview(!experienced);
        overviewView.setValue(model.calculateTotalRenown());
      }
    });
    view.showOverview(!model.isCharacterExperienced());
    model.addRenownChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        int totalRenown = model.calculateTotalRenown();
        int allowedRenown = model.calculateAlottedRenownPoints();
        totalView.setValue(totalRenown);
        adjustOverview(overviewView, totalRenown, allowedRenown);
      }
    });
    model.addFreeRenownPointsChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        adjustOverview(overviewView, model.calculateTotalRenown(), newValue);
      }
    });
  }

  private void adjustOverview(final ILabelledAlotmentView overviewView, int totalRenown, int allowedRenown) {
    overviewView.setValue(totalRenown);
    overviewView.setAlotment(allowedRenown);
    if (totalRenown > allowedRenown) {
      overviewView.setTextColor(LegalityColorProvider.COLOR_HIGH);
    }
    else {
      overviewView.setTextColor(LegalityColorProvider.COLOR_OKAY);
    }
  }

  private ILabelledAlotmentView initOverviewPresentation() {
    ILabelledAlotmentView overviewView = view.addOverview(resources.getString("Lunar.Renown.OverviewLabel"), //$NON-NLS-1$
        resources.getString("Lunar.Renown.OverviewPoints")); //$NON-NLS-1$
    overviewView.setValue(model.calculateTotalRenown());
    overviewView.setAlotment(model.calculateAlottedRenownPoints());
    return overviewView;
  }

  private void initFacePresentation() {
    final IIntValueView faceView = view.addFaceSelectionView(resources.getString("Lunar.Renown.FaceLabel"), //$NON-NLS-1$
        new LegalityCheckListCellRenderer(resources) {
          @Override
          protected boolean isLegal(Object object) {
            return object != null && (Integer) object <= model.getMaximumAllowedFaceRank();
          }

          @Override
          protected String getPrintName(IResources res, Object value) {
            String valueString = String.valueOf(value);
            return valueString + ": " + resources.getString("Lunar.Renown.Rank." + valueString); //$NON-NLS-1$//$NON-NLS-2$
          }
        },
        model.getUltimateFaceRank());
    faceView.setValue(model.getFace().getCurrentValue());
    addModelValueListener(model.getFace(), faceView);
    addViewValueListener(faceView, model.getFace());
  }

  private void initTraitPresentation(final IValueView<Integer> totalView) {
    for (ITrait trait : model.getAllTraits()) {
      final IIntValueView renownView = view.addIntValueView(resources.getString("Lunar.Renown." + trait.getType().getId())); //$NON-NLS-1$
      addModelValueListener(trait, renownView);
      addViewValueListener(renownView, trait);
      renownView.setValue(trait.getCurrentValue());
    }
    totalView.setValue(model.calculateTotalRenown());
  }
}