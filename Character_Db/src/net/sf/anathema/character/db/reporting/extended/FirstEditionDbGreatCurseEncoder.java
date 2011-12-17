package net.sf.anathema.character.db.reporting.extended;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.common.encoder.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public class FirstEditionDbGreatCurseEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final IResources resources;

  public FirstEditionDbGreatCurseEncoder(BaseFont baseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "GreatCurse.Dragon-Blooded"; //$NON-NLS-1$
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    String virtueMessage = getVirtueString(character);
    String aspectMessage = getAspectString(character);
    String message = resources.getString("Sheet.GreatCurse.Message", virtueMessage, aspectMessage); //$NON-NLS-1$
    Phrase phrase = new Phrase(message, TableEncodingUtilities.createFont(getBaseFont()));
    encodeTextWithReducedLineHeight(directContent, bounds, phrase);
  }

  private String getVirtueString(IGenericCharacter character) {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    IGenericTrait topVirtue = sortedVirtues.get(0);
    int runnerUpValue = sortedVirtues.get(1).getCurrentValue();
    if (topVirtue.getCurrentValue() == runnerUpValue) {
      return resources.getString("Sheet.GreatCurse.UnknownVirtue"); //$NON-NLS-1$
    }
    return resources.getString(topVirtue.getType().getId());
  }

  private String getAspectString(IGenericCharacter character) {
    String casteTypeString = character.getCasteType().getId();
    if (casteTypeString != null) {
      String casteType = resources.getString("Caste." + casteTypeString); //$NON-NLS-1$
      return resources.getString("Sheet.GreatCurse.AspectMessage", casteType); //$NON-NLS-1$
    }
    if (isHighestVirtueKnown(character)) {
      return resources.getString("Sheet.GreatCurse.UnknownAspectKnownVirtue"); //$NON-NLS-1$
    }
    return resources.getString("Sheet.GreatCurse.UnknownAspectUnknownVirtue"); //$NON-NLS-1$
  }

  private boolean isHighestVirtueKnown(IGenericCharacter character) {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    IGenericTrait topVirtue = sortedVirtues.get(0);
    int runnerUpValue = sortedVirtues.get(1).getCurrentValue();
    return topVirtue.getCurrentValue() != runnerUpValue;
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
