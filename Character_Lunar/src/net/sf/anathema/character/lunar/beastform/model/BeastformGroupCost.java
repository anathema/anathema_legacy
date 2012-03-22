package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformAttribute;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.model.MutationVisitorAdapter;
import net.sf.anathema.character.mutations.model.types.AttributePointsProvidingMutation;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class BeastformGroupCost implements IBeastformGroupCost {

  private final IBeastformTraitCollection collection;
  private int totalDots;
  private final GenericControl<IAlotmentChangedListener> control = new GenericControl<IAlotmentChangedListener>();

  public BeastformGroupCost(IBeastformTraitCollection collection, final IBeastformModel model) {
    this.collection = collection;
    IMutationsModel listenModel = model.getMutationModel();
    listenModel.addModelChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        calculateDots(model.getMutationModel(), model.getCharmValue());
      }
    });
    model.addCharmLearnCountChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        calculateDots(model.getMutationModel(), model.getCharmValue());
      }
    });
  }

  @Override
  public int getUnspentDots() {
    final int[] dotsSpent = new int[]{getSpentDots()};
    control.forAllDo(new IClosure<IAlotmentChangedListener>() {
      @Override
      public void execute(IAlotmentChangedListener input) {
        input.spentChanged(dotsSpent[0]);
      }
    });
    return Math.max(0, totalDots - dotsSpent[0]);
  }

  @SuppressWarnings("unchecked")
  private void calculateDots(IQualityModel<?> model, int charmValue) {
    final int[] dotsFromGifts = new int[1];
    for (IQualitySelection<IMutation> selection : ((IQualityModel<IMutation>) model).getSelectedQualities()) {
      selection.getQuality().accept(new MutationVisitorAdapter() {
        @Override
        public void visitAttributePointsProvidingMutation(AttributePointsProvidingMutation mutation) {
          dotsFromGifts[0] += mutation.getProvidedPoints();
        }
      });
    }
    int charmDots = charmValue == 0 ? 0 : 5 + (charmValue - 1) * 3;
    this.totalDots = dotsFromGifts[0] + charmDots;
    control.forAllDo(new IClosure<IAlotmentChangedListener>() {
      @Override
      public void execute(IAlotmentChangedListener input) {
        input.totalChanged(totalDots);
      }
    });
  }

  @Override
  public int getTotalDots() {
    return totalDots;
  }

  @Override
  public int getSpentDots() {
    int dotsSpent = 0;
    for (ITraitType type : AttributeType.getAllFor(AttributeGroupType.Physical)) {
      IBeastformAttribute deadlyBeastmanAttribute = collection.getDeadlyBeastmanAttribute(type);
      dotsSpent += deadlyBeastmanAttribute.getPointCost() * deadlyBeastmanAttribute.getAdditionalDots();
    }
    return dotsSpent;
  }

  @Override
  public void addCostChangeListener(IAlotmentChangedListener listener) {
    control.addListener(listener);
  }
}