package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.workflow.labelledvalue.IStyleableView;

public class FontParameterSetter {

  private final int alotment;
  private final IStyleableView view;
  private final ISpendingModel model;

  public FontParameterSetter(ISpendingModel model, IStyleableView view, int alotment) {
    this.model = model;
    this.view = view;
    this.alotment = alotment;
  }

  public void setFontParameters() {
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
