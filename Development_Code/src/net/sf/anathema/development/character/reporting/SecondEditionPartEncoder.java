package net.sf.anathema.development.character.reporting;

import java.awt.Point;
import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionPartEncoder implements IPdfPartEncoder {

  private static final int ESSENCE_MAX = 7;
  private final BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
  private final IResources resources;
  private final PdfTraitEncoder traitEncoder;
  private final PdfBoxEncoder boxEncoder;

  public SecondEditionPartEncoder(IResources resources) throws DocumentException, IOException {
    this.resources = resources;
    this.traitEncoder = new PdfTraitEncoder(baseFont);
    this.boxEncoder = new PdfBoxEncoder(baseFont);
  }

  public void encodeAttributes(
      PdfContentByte directContent,
      SmartRectangle contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    int groupSpacing = traitEncoder.getTraitHeight() / 2;
    int y = (int) contentBounds.getMaxY() - groupSpacing;
    String groupId = null;
    for (IGroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(groupId)) {
        groupId = groupedTraitType.getGroupId();
        y -= groupSpacing;
      }
      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("Sheet." + traitType.getId());
      traitEncoder.encodeWithText(
          directContent,
          traitLabel,
          new Point(contentBounds.x, y),
          contentBounds.width,
          traitCollection.getTrait(traitType).getCurrentValue(),
          ESSENCE_MAX);
      y -= traitEncoder.getTraitHeight();
    }
  }

  public BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeEditionSpecificFirstPagePart(PdfContentByte directContent, SmartRectangle restBounds) {
    boxEncoder.encodeBox(directContent, restBounds, "Rest");
  }
}