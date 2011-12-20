package net.sf.anathema.character.infernal.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.traits.FavorableTraitContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class InfernalYoziListEncoder extends FavorableTraitBoxContentEncoder implements IVariableBoxContentEncoder {
  private final float lineHeight = 16;

  public InfernalYoziListEncoder() {
    super(InfernalYoziListContent.class);
  }

  @Override
  public float getRequestedHeight(ReportContent content, float width) {
    return lineHeight * YoziType.values().length;
  }
 }
