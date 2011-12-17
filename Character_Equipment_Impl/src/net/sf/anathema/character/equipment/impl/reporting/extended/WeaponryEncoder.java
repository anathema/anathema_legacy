package net.sf.anathema.character.equipment.impl.reporting.extended;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

public class WeaponryEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final AbstractWeaponryTableEncoder customEncoder;

  public WeaponryEncoder(IResources resources, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.customEncoder = null;
  }

  public WeaponryEncoder(IResources resources,
		  BaseFont baseFont,
		  AbstractWeaponryTableEncoder customEncoder) {
	    this.baseFont = baseFont;
	    this.resources = resources;
	    this.customEncoder = customEncoder;
	  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Weapons"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, final IGenericCharacter character, IGenericDescription description, Bounds bounds)
      throws DocumentException {
    final AbstractWeaponryTableEncoder[] encoder = new AbstractWeaponryTableEncoder[1];
    final IEquipmentModifiers equipment = character.getEquipmentModifiers();
    character.getRules().getEdition().accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        encoder[0] = new FirstEditionWeaponryTableEncoder(baseFont, resources, equipment);
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        encoder[0] = new SecondEditionWeaponryTableEncoder(baseFont, resources, equipment);
      }

    });
    if (customEncoder != null)
    	encoder[0] = customEncoder;
    encoder[0].encodeTable(directContent, character, bounds);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
