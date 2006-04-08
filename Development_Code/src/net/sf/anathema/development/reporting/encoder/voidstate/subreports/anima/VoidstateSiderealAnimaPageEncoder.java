package net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateSiderealAnimaPageEncoder extends AbstractVoidstateAnimaPageEncoder {

  public VoidstateSiderealAnimaPageEncoder(IOneColumnEncoder columnEncoder) {
    super(columnEncoder);
  }

  @Override
  protected void fillAnimaTable(Element parent, Rectangle bounds, int yOffset) {
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "1-3", "Caste Mark glitters", "Normal", true);
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "4-7", "Caste Mark burns lightly", "Normal", true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "8-10",
        "Caste Mark burns clearly",
        "+2 in dark",
        true);
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "11-15", "Soft Halo", "Impossible", true);
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "16+", "Burning Halo", "Impossible", false);
  }

  @Override
  protected List<ITextPart[]> getAnimaEffectTextParts() {
    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    List<ITextPart[]> textParts = new ArrayList<ITextPart[]>();
    textParts.add(new ITextPart[] { new TextPart("Cause Caste Mark to glow brightly (1 mote)", smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart(
        "Cause anima to glow bright enough to read by (1 mote)",
        smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart("Recognize nearby gateways (1 mote)", smallTextFormat) });
    return textParts;
  }

  public String getGroupName() {
    return "VoidStateSiderealAnimaSubreport";
  }
}