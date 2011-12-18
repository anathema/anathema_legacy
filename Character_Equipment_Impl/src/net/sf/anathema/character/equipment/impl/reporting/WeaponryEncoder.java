package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class WeaponryEncoder implements IBoxContentEncoder {

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

  public String getHeaderKey(ReportContent reportContent) {
    return "Weapons"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, final ReportContent content, Bounds bounds)
      throws DocumentException {
    final AbstractWeaponryTableEncoder[] encoder = new AbstractWeaponryTableEncoder[1];
    content.getCharacter().getRules().getEdition().accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        encoder[0] = new FirstEditionWeaponryTableEncoder(baseFont, resources, content.getCharacter().getEquipmentModifiers());
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        encoder[0] = new SecondEditionWeaponryTableEncoder(baseFont, resources, content.getCharacter().getEquipmentModifiers());
      }

    });
    if (customEncoder != null)
    	encoder[0] = customEncoder;
    encoder[0].encodeTable(graphics.getDirectContent(), content, bounds);
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
