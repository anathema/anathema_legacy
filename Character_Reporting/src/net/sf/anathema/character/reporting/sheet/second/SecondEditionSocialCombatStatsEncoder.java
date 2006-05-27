package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.CharacterUtiltiies;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.LabelledValueEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionSocialCombatStatsEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionSocialCombatStatsEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float integrityWdth = bounds.width / 3.0f;
    float valueWidth = bounds.width - integrityWdth- IVoidStateFormatConstants.TEXT_PADDING;
    Bounds valueBounds = new Bounds(bounds.x, bounds.y, valueWidth, bounds.height);
    float valueHeight = encodeValues(directContent, character, valueBounds);
  }

  private float encodeValues(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    String joinLabel = resources.getString("Sheet.SocialCombat.JoinDebateBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.SocialCombat.DodgeMDV"); //$NON-NLS-1$
    int joinDebate = CharacterUtiltiies.getTotalValue(character, AttributeType.Wits, AbilityType.Awareness);
    int dodgeMDV = CharacterUtiltiies.getDvValue(
        character,
        OtherTraitType.Willpower,
        OtherTraitType.Essence,
        AbilityType.Integrity);
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(directContent, 0, joinLabel, joinDebate);
    encoder.addLabelledValue(directContent, 1, dodgeLabel, dodgeMDV);
    return encoder.getHeight();
  }
}