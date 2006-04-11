package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class AlotmentSubPresenter implements IOverviewSubPresenter {

  private final ILabelledAlotmentView view;
  private final IAlotmentModel model;
  private final int alotment;

  public AlotmentSubPresenter(IAlotmentModel model, ILabelledAlotmentView view, int alotment) {
    this.model = model;
    this.view = view;
    this.alotment = alotment;
  }

  public void update() {
    view.setValue(model.getValue());
    view.setAlotment(alotment);
    setFontParameters();
  }

  private void setFontParameters() {
    LegalityFontProvider legalityFontProvider = new LegalityFontProvider();
    ValueLegalityState fontStyleState = model.getSpentBonusPoints() > 0
        ? ValueLegalityState.Increased
        : ValueLegalityState.Okay;
    view.setFontStyle(legalityFontProvider.getFontStyle(fontStyleState));
    LegalityColorProvider legalityColorProvider = new LegalityColorProvider();
    ValueLegalityState textColorState = null;
    if (model.getValue() < alotment) {
      textColorState = ValueLegalityState.Lowered;
    }
    if (model.getValue() == alotment) {
      textColorState = ValueLegalityState.Okay;
    }
    if (model.getValue() > alotment) {
      textColorState = ValueLegalityState.Increased;
    }
    view.setTextColor(legalityColorProvider.getTextColor(textColorState));
  }
}