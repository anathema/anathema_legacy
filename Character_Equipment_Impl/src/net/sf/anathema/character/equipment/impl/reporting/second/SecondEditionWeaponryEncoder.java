package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.AbstractWeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.first.FirstEditionWeaponryTableEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionWeaponryEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionWeaponryEncoder(IResources resources, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.resources = resources;
  }

  public String getHeaderKey() {
    return "Weapons"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, final IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    final AbstractWeaponryTableEncoder[] encoder = new AbstractWeaponryTableEncoder[1];
    character.getRules().getEdition().accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        encoder[0] = new FirstEditionWeaponryTableEncoder(baseFont, resources, character.getTraitCollection());
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        encoder[0] = new SecondEditionWeaponryTableEncoder(baseFont, resources);
      }

    });
    encoder[0].encodeTable(directContent, character, bounds);
  }
}