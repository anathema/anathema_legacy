package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.sheet.common.health.AbstractHealthTableEncoder;
import net.sf.anathema.character.reporting.sheet.common.movement.AbstractHealthAndMovementTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class SecondEditionHealthTableEncoder extends AbstractHealthTableEncoder {

  public SecondEditionHealthTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected final Phrase createIncapacitatedComment() {
    return new Phrase(getResources().getString("Sheet.Movement.Comment.Mobility"), getCommentFont()); //$NON-NLS-1$
  }
}