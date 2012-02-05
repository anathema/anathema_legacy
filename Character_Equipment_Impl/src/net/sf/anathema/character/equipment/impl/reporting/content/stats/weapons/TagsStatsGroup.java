package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public final class TagsStatsGroup implements IEquipmentStatsGroup<IWeaponStats> {
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
    return new Float[]{new Float(1.7)};
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyNameCell(font));
    } else {
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
    return TableEncodingUtilities.createContentCellTable(BaseColor.BLACK, text, font, 0.5f, Rectangle.BOTTOM, Element.ALIGN_LEFT);
  }
}
