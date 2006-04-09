package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.model.gift.AttributePointsProvidingGift;
import net.sf.anathema.character.lunar.beastform.model.gift.GiftVisitorAdapter;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class BeastformGroupCost implements IBeastformGroupCost {

  private final IBeastformTraitCollection collection;
  private int totalDots;
  private final GenericControl<IAlotmentChangedListener> control = new GenericControl<IAlotmentChangedListener>();

  public BeastformGroupCost(IBeastformTraitCollection collection, final BeastformModel model) {
    this.collection = collection;
    model.getGiftModel().addModelChangeListener(new IChangeListener() {
      public void changeOccured() {
        calculateDots(model.getGiftModel(), model.getCharmValue());
      }
    });
    model.addCharmLearnCountChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        calculateDots(model.getGiftModel(), model.getCharmValue());
      }
    });
  }

  public int getUnspentDots() {
    final int[] dotsSpent = new int[] { getSpentDots() };
    control.forAllDo(new IClosure<IAlotmentChangedListener>() {
      public void execute(IAlotmentChangedListener input) {
        input.spentChanged(dotsSpent[0]);
      }
    });
    return Math.max(0, totalDots - dotsSpent[0]);
  }

  private void calculateDots(IQualityModel<IGift> giftModel, int charmValue) {
    final int[] dotsFromGifts = new int[1];
    for (IQualitySelection<IGift> selection : giftModel.getSelectedQualities()) {
      selection.getQuality().accept(new GiftVisitorAdapter() {
        @Override
        public void visitAttributePointsProvidingGift(AttributePointsProvidingGift gift) {
          dotsFromGifts[0] += gift.getProvidedPoints();
        }
      });
    }
    int charmDots = charmValue == 0 ? 0 : 5 + (charmValue - 1) * 3;
    this.totalDots = dotsFromGifts[0] + charmDots;
    control.forAllDo(new IClosure<IAlotmentChangedListener>() {
      public void execute(IAlotmentChangedListener input) {
        input.totalChanged(totalDots);
      }
    });
  }

  public int getTotalDots() {
    return totalDots;
  }

  public int getSpentDots() {
    int dotsSpent = 0;
    for (ITraitType type : AttributeType.getAllFor(AttributeGroupType.Physical)) {
      IBeastformAttribute deadlyBeastmanAttribute = collection.getDeadlyBeastmanAttribute(type);
      dotsSpent += deadlyBeastmanAttribute.getPointCost() * deadlyBeastmanAttribute.getAdditionalDots();
    }
    return dotsSpent;
  }

  public void addCostChangeListener(IAlotmentChangedListener listener) {
    control.addListener(listener);
  }
}