package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import com.google.common.base.Joiner;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class MagicDetailsStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  private final IResources resources;

  public MagicDetailsStatsGroup(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, null));
    } else {
      String[] details = stats.getDetailStrings(resources);
      String detailText = Joiner.on(", ").join(details);
      if (StringUtilities.isNullOrEmpty(detailText)) {
        detailText = "-"; //$NON-NLS-1$
      }
      table.addCell(createTextCell(font, detailText));
    }
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[]{7f};
  }

  @Override
  public String getTitle() {
    return resources.getString("Sheet.Magic.Details"); //$NON-NLS-1$
  }
}
