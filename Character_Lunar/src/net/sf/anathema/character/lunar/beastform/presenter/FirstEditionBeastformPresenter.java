package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.lunar.beastform.model.IAlotmentChangedListener;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.lunar.beastform.view.IBeastformOverviewView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformOverviewViewProperties;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class FirstEditionBeastformPresenter implements IPresenter {

  private final IResources resources;
  private final IBeastformView view;
  private final IBeastformModel model;
  private IBeastformOverviewView overView;
  private final LegalityColorProvider provider = new LegalityColorProvider();

  public FirstEditionBeastformPresenter(IResources resources, IBeastformView view, IBeastformModel model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  public void initPresentation() {
    initOverviewPresentation();
   	initCharmValuePresentation();
    initAttributePresentation();
    initGiftPresentation();
  }

  private void initOverviewPresentation() {
    overView = view.addOverviewView(new IBeastformOverviewViewProperties() {
	
      public String getGiftPicksString() {
        return resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label"); //$NON-NLS-1$
      }

      public String getAttributeDotsString() {
        return resources.getString("Lunar.DeadlyBeastmanTransformation.Attributes.Label"); //$NON-NLS-1$
      }

      public String getOverviewBorderString() {
        return resources.getString("Lunar.DeadlyBeastmanTransformation.Overview.Label"); //$NON-NLS-1$
      }
    });
  }

  private void initGiftPresentation() {
    final IGiftModel giftModel = model.getGiftModel();
    final IMagicLearnView giftView = view.addGiftsView(new FirstEditionGiftViewLearnProperties(resources, giftModel));

    giftView.addMagicViewListener(new IMagicViewListener() {
      @SuppressWarnings("unchecked")
      public void magicRemoved(Object[] removedMagic) {
        giftModel.removeQualitySelection((IQualitySelection<IGift>) removedMagic[0]);
      }

      public void magicAdded(Object[] addedMagic) {
        IGift gift = (IGift) addedMagic[0];
        IQualitySelection<IGift> selection = new QualitySelection<IGift>(gift, 1, !giftModel.isCharacterExperienced());
        giftModel.addQualitySelection(selection);
      }
    });
    giftModel.addModelChangeListener(new IChangeListener() {
      public void changeOccurred() {
        updateGiftViews(giftModel, giftView);
      }
    });
    giftModel.addOverviewChangedListener(new IChangeListener() {
      public void changeOccurred() {
        updateGiftViews(giftModel, giftView);
        updateOverview();
      }
    });
    updateGiftViews(giftModel, giftView);
    updateOverview();
  }

  private void updateOverview() {
    ILabelledAlotmentView giftOverview = overView.getGiftOverview();
    int giftSpent = model.getGiftModel().getSelectedQualities().length;
    giftOverview.setValue(giftSpent);
    int giftTotal = model.getGiftModel().getAllowedPicks();
    giftOverview.setAlotment(giftTotal);
    setOverviewColor(giftTotal, giftSpent, giftOverview);
  }

  private void updateGiftViews(final IQualityModel<IGift> giftModel, final IMagicLearnView giftView) {
    setAvailableGifts(giftModel, giftView);
    IQualitySelection<IGift>[] selectedGifts = giftModel.getSelectedQualities();
    giftView.setLearnedMagic(selectedGifts);
  }

  private void setAvailableGifts(final IQualityModel<IGift> giftModel, final IMagicLearnView giftView) {
    IGift[] availablePerks = giftModel.getAvailableQualities();
    IGift[] sortedPerks = new IGift[availablePerks.length];
    I18nedIdentificateSorter<IGift> sorter = createSorter();
    sorter.sortAscending(availablePerks, sortedPerks, resources);
    giftView.setMagicOptions(sortedPerks);
  }

  private I18nedIdentificateSorter<IGift> createSorter() {
    return new I18nedIdentificateSorter<IGift>() {
      @Override
      protected String getString(final IResources sorterResources, IGift gift) {
        return sorterResources.getString("DeadlyBeastmanTransformation.Gift." //$NON-NLS-1$
            + gift.getId());
      }
    };
  }

  private void initAttributePresentation() {
    for (IBeastformAttribute attribute : model.getAttributes()) {
      IDefaultTrait trait = attribute.getTrait();
      IIntValueView traitView = view.addAttributeValueView(
          resources.getString(trait.getType().getId()),
          trait.getCurrentValue(),
          trait.getMaximalValue());
      new TraitPresenter(trait, traitView).initPresentation();
    }
    model.getAttributeCostModel().addCostChangeListener(new IAlotmentChangedListener() {
      public void totalChanged(int newValue) {
        overView.getAttributeOverview().setAlotment(newValue);
        setOverviewColor(newValue, model.getAttributeCostModel().getSpentDots(), overView.getAttributeOverview());
      }

      public void spentChanged(int newValue) {
        overView.getAttributeOverview().setValue(newValue);
        setOverviewColor(model.getAttributeCostModel().getTotalDots(), newValue, overView.getAttributeOverview());
      }
    });
   	overView.getAttributeOverview().setAlotment(model.getAttributeCostModel().getTotalDots());
    overView.getAttributeOverview().setValue(model.getAttributeCostModel().getSpentDots());
    setOverviewColor(
        model.getAttributeCostModel().getTotalDots(),
        model.getAttributeCostModel().getSpentDots(),
        overView.getAttributeOverview());
  }

  private void initCharmValuePresentation() {
    final IIntValueView charmView = view.addCharmValueView(
        resources.getString("Lunar.DeadlyBeastmanTransformation.Value"), model.getCharmValue(), 7); //$NON-NLS-1$
    charmView.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setCharmLearnCount(newValue);
      }
    });
    model.addCharmLearnCountChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        charmView.setValue(newValue);
        int totalDots = model.getAttributeCostModel().getTotalDots();
        int spentDots = model.getAttributeCostModel().getSpentDots();
        setOverviewColor(totalDots, spentDots, overView.getAttributeOverview());
        int totalPicks = model.getGiftModel().getAllowedPicks();
        int spentPicks = model.getGiftModel().getSelectedQualities().length;
        setOverviewColor(totalPicks, spentPicks, overView.getGiftOverview());

      }
    });
  }

  private void setOverviewColor(int total, int spent, ILabelledAlotmentView view) {
    if (total < spent) {
      view.setTextColor(provider.getTextColor(ValueLegalityState.High));
    }
    else {
      view.setTextColor(provider.getTextColor(ValueLegalityState.Okay));
    }
  }
}