package net.sf.anathema.character.infernal.reporting.rendering;

import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.infernal.reporting.content.InfernalYoziListContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class YoziListEncoder extends FavorableTraitBoxContentEncoder implements IVariableContentEncoder {
  private static final float LINE_HEIGHT = 16;

  public YoziListEncoder() {
    super(InfernalYoziListContent.class);
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    return LINE_HEIGHT * YoziType.values().length;
  }
}
