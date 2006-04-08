package net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateSolarAnimaPageEncoder extends AbstractVoidstateAnimaPageEncoder {

  public VoidstateSolarAnimaPageEncoder(IOneColumnEncoder columnEncoder) {
    super(columnEncoder);
  }

  @Override
  protected void fillAnimaTable(Element bandElement, Rectangle parentRect, int yOffset) {
    yOffset += addAnimaTableRow(
        bandElement,
        parentRect,
        parentRect.y + yOffset,
        "1-3",
        "Caste Mark glitters",
        "Normal",
        true);
    yOffset += addAnimaTableRow(bandElement, parentRect, parentRect.y + yOffset, "4-7", "Caste Mark burns", "+2", true);
    yOffset += addAnimaTableRow(
        bandElement,
        parentRect,
        parentRect.y + yOffset,
        "8-10",
        "Coruscant Aura",
        "Impossible",
        true);
    yOffset += addAnimaTableRow(
        bandElement,
        parentRect,
        parentRect.y + yOffset,
        "11-15",
        "Brilliant Bonfire",
        "Impossible",
        true);
    yOffset += addAnimaTableRow(
        bandElement,
        parentRect,
        parentRect.y + yOffset,
        "16+",
        "Totemic Aura",
        "Impossible",
        false);
  }

  @Override
  protected List<ITextPart[]> getAnimaEffectTextParts() {
    List<ITextPart[]> textParts = new ArrayList<ITextPart[]>();
    TextFormat smallTextFormat = new TextFormat(
        FontStyle.PLAIN,
        false,
        FONT_SIZE - 1);
    textParts.add(new ITextPart[] { new TextPart("Cause Caste Mark to glow brightly (1 mote)", smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart(
        "Cause anima to glow bright enough to read by (1 mote)",
        smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart("Know the precise time of day (1 mote)", smallTextFormat) });
    return textParts;
  }

  public String getGroupName() {
    return "VoidStateSolarAnimaSubreport";
  }
}