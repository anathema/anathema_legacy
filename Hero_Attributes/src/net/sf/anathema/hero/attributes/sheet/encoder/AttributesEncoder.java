package net.sf.anathema.hero.attributes.sheet.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.general.box.AbstractContentEncoder;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.attributes.sheet.content.AttributesContent;
import net.sf.anathema.hero.attributes.sheet.content.PrintAttribute;
import net.sf.anathema.hero.attributes.sheet.content.PrintAttributeGroup;

import java.util.List;

public class AttributesEncoder extends AbstractContentEncoder<AttributesContent> {

  private PdfTraitEncoder smallTraitEncoder;

  public AttributesEncoder() {
    super(AttributesContent.class);
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds contentBounds) throws DocumentException {
    AttributesContent content = createContent(reportSession);
    float groupSpacing = smallTraitEncoder.getTraitHeight() * 2 / 3.0f;
    float y = contentBounds.getMaxY() - groupSpacing;
    List<PrintAttributeGroup> attributeGroups = content.getAttributeGroups();
    for (PrintAttributeGroup group : attributeGroups) {
      y -= groupSpacing;
      for (PrintAttribute attribute : group.attributes) {
        Position position = new Position(contentBounds.x, y);
        y -= smallTraitEncoder.encodeWithText(graphics, attribute.name, position, contentBounds.width, attribute.value, content.getTraitMaximum());
      }
    }
  }
}
