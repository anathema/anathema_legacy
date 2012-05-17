package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.google.common.base.Joiner;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.collection.ArrayUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.ITransformer;
import net.sf.anathema.lib.util.Identified;

public final class TagsStatsGroup implements IEquipmentStatsGroup<IWeaponStats> {
  private final String title;
  private final IResources resources;

  public TagsStatsGroup(IResources resources) {
    this.resources = resources;
    this.title = resources.getString("Sheet.Equipment.Header.Tags"); //$NON-NLS-1$ ;
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{new Float(1.7)};
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyNameCell(font));
    } else {
      Identified[] tags = weapon.getTags();
      String[] values = ArrayUtilities.transform(tags, String.class, new ITransformer<Identified, String>() {
        @Override
        public String transform(Identified input) {
          return resources.getString("Weapons.Tags." + input.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
        }
      });
      String valueString = values.length == 0 ? " " : Joiner.on(",").join(values);
      table.addCell(createFilledContentCell(font, valueString));
    }
  }

  private PdfPCell createEmptyNameCell(Font font) {
    return createFilledContentCell(font, " "); //$NON-NLS-1$
  }

  private PdfPCell createFilledContentCell(Font font, String text) {
    return TableEncodingUtilities.createContentCellTable(BaseColor.BLACK, text, font, 0.5f, Rectangle.BOTTOM, Element.ALIGN_LEFT);
  }
}
