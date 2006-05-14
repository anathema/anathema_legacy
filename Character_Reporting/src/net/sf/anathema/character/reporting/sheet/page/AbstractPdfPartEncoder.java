package net.sf.anathema.character.reporting.sheet.page;

import java.awt.Point;
import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.SimplePdfEssenceEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractPdfPartEncoder extends AbstractPdfEncoder implements IPdfPartEncoder {

  private final BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
  private final IResources resources;
  private final PdfTraitEncoder smallTraitEncoder;
  private final int essenceMax;

  public AbstractPdfPartEncoder(IResources resources, int essenceMax) throws DocumentException, IOException {
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public final BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds) {
    PdfAbilitiesEncoder encoder = new PdfAbilitiesEncoder(getBaseFont(), getResources(), essenceMax);
    IIdentifiedTraitTypeGroup[] groups = character.getAbilityTypeGroups();
    Point abilityPosition = new Point((int) contentBounds.getMinX(), (int) contentBounds.getMaxY());
    encoder.encodeAbilities(directContent, character, groups, abilityPosition, contentBounds.width);
  }

  public final void encodeAttributes(
      PdfContentByte directContent,
      SmartRectangle contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    int groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    int y = (int) contentBounds.getMaxY() - groupSpacing;
    String groupId = null;
    for (IGroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(groupId)) {
        groupId = groupedTraitType.getGroupId();
        y -= groupSpacing;
      }
      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = getResources().getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Point position = new Point(contentBounds.x, y);
      y -= smallTraitEncoder.encodeWithText(directContent, traitLabel, position, contentBounds.width, value, essenceMax);
    }
  }

  public final IResources getResources() {
    return resources;
  }

  public void encodeEssence(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds) {
    SimplePdfEssenceEncoder encoder = new SimplePdfEssenceEncoder(getBaseFont(), getResources(), essenceMax);
    encoder.encodeEssence(directContent, character, contentBounds);
  }
}