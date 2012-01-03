package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class InfernalYoziListEncoder extends FavorableTraitBoxContentEncoder implements IVariableBoxContentEncoder {
  private final float lineHeight = 16;

  public InfernalYoziListEncoder() {
    super(InfernalYoziListContent.class);
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    return lineHeight * YoziType.values().length;
  }
}
