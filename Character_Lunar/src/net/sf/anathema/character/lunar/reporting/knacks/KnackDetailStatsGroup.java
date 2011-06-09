package net.sf.anathema.character.lunar.reporting.knacks;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractTextStatsGroup;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class KnackDetailStatsGroup extends AbstractTextStatsGroup<IKnackStats> {

  private final IResources resources;

  public KnackDetailStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IKnackStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, null));
    }
    else {
      String[] details = ArrayUtilities.transform(
          stats.getDetailString(resources),
          String.class,
          new ITransformer<String, String>() {
            public String transform(String input) {
              return resources.getString(input);
            }
          });
      String detailText = AnathemaStringUtilities.concat(details, ", "); //$NON-NLS-1$
      if (StringUtilities.isNullOrEmpty(detailText)) {
        detailText = "-"; //$NON-NLS-1$
      }
      table.addCell(createTextCell(font, detailText));
    }
  }

  public Float[] getColumnWeights() {
    return new Float[] { 7f };
  }

  public String getTitle() {
    return resources.getString("Sheet.Magic.Details"); //$NON-NLS-1$
  }
}