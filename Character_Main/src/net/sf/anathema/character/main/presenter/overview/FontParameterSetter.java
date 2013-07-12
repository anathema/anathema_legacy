package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.view.labelledvalue.IStyleableView;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.lib.control.legality.FontStyle;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;

public class FontParameterSetter {

  private final IStyleableView view;
  private final SpendingModel model;

  public FontParameterSetter(SpendingModel model, IStyleableView view) {
    this.model = model;
    this.view = view;
  }

  public void setFontParameters() {
    ValueLegalityState fontStyleState = model.getSpentBonusPoints() > 0 ? ValueLegalityState.Increased : ValueLegalityState.Okay;
    FontStyle fontStyle = new LegalityFontProvider().getFontStyle(fontStyleState);
    view.setFontStyle(fontStyle);
    LegalityColorProvider legalityColorProvider = new LegalityColorProvider();
    ValueLegalityState textColorState = null;
    int alotment = model.getAllotment();
    if (model.getValue() < alotment) {
      textColorState = ValueLegalityState.Lowered;
    }
    if (model.getValue() == alotment) {
      textColorState = ValueLegalityState.Okay;
    }
    if (model.getValue() > alotment) {
      textColorState = ValueLegalityState.High;
    }
    view.setTextColor(legalityColorProvider.getTextColor(textColorState));
  }
}
