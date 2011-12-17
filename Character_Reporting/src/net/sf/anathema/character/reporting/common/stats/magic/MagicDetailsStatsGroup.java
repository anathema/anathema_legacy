package net.sf.anathema.character.reporting.common.stats.magic;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.common.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

public class MagicDetailsStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicDetailsStatsGroup(IResources resources) {
    this.resources = resources;
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, null));
    }
    else {
      String[] details = ArrayUtilities.transform(
          stats.getDetailKeys(),
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
