package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.sheet.common.anima.AbstractAnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SolarAnimaTableEncoder extends AbstractAnimaTableEncoder {

  public SolarAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    super(resources, baseFont, fontSize);
  }

  @Override
  protected String getFifthLevelKey() {
    return "Sheet.AnimaTable.TotemicAura"; //$NON-NLS-1$
  }

  @Override
  protected String getFourthLevelKey() {
    return "Sheet.AnimaTable.BrilliantBonfire"; //$NON-NLS-1$
  }

  @Override
  protected String getThirdLevelKey() {
    return "Sheet.AnimaTable.CoruscantAura"; //$NON-NLS-1$
  }

  @Override
  protected String getSecondLevelKey() {
    return "Sheet.AnimaTable.CasteMarkBurns"; //$NON-NLS-1$
  }

  @Override
  protected String getFirstLevelKey() {
    return "Sheet.AnimaTable.CasteMarkGlitters"; //$NON-NLS-1$
  }
}