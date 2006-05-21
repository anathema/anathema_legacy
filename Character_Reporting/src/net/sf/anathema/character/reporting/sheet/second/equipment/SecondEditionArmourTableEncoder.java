package net.sf.anathema.character.reporting.sheet.second.equipment;

import net.sf.anathema.character.reporting.sheet.second.equipment.armourstats.SoakArmourStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.IEquipmentStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class SecondEditionArmourTableEncoder extends AbstractEquipmentTableEncoder {

  private final IResources resources;

  public SecondEditionArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable() {
    PdfPTable pdfPTable = super.createTable();
    // todo vom (21.05.2006) (sieroux): Totalline hinzufügen
    return pdfPTable;
  }

  @Override
  protected IEquipmentStatsGroup[] createEquipmentGroups() {
    return new IEquipmentStatsGroup[] { new SoakArmourStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 3;
  }
}