package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import java.awt.Color;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.equipment.impl.reporting.second.EquipmentEncodingUtilities;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public final class TagsStatsGroup implements IEquipmentStatsGroup<IWeapon> {
  private final String title;
  private final IResources resources;

  public TagsStatsGroup(IResources resources) {
    this.resources = resources;
    this.title = resources.getString("Sheet.Equipment.Header.Tags"); //$NON-NLS-1$ ;
  }

  public int getColumnCount() {
    return 1;
  }

  public String getTitle() {
    return title;
  }

  public Float[] getColumnWeights() {
    return new Float[] { new Float(1.7) };
  }

  public void addContent(PdfPTable table, Font font, IWeapon weapon, IGenericTrait... traits) {
    if (weapon == null) {
      table.addCell(createEmptyNameCell(font));
    }
    else {
      IIdentificate[] tags = weapon.getTags();
      String[] values = ArrayUtilities.transform(tags, String.class, new ITransformer<IIdentificate, String>() {
        public String transform(IIdentificate input) {
          return resources.getString("Weapons.Tags." + input.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
        }
      });
      String valueString = values.length == 0 ? " " : AnathemaStringUtilities.concat(values, ","); //$NON-NLS-1$ //$NON-NLS-2$
      table.addCell(createFilledContentCell(font, valueString));
    }
  }

  private PdfPCell createEmptyNameCell(Font font) {
    return createFilledContentCell(font, " "); //$NON-NLS-1$
  }

  private PdfPCell createFilledContentCell(Font font, final String text) {
    return EquipmentEncodingUtilities.createContentCellTable(
        Color.BLACK,
        text,
        font,
        0.5f,
        Rectangle.BOTTOM,
        Element.ALIGN_LEFT);
  }
}