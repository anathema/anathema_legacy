package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import com.google.common.base.Joiner;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractTextStatsGroup;
import net.sf.anathema.lib.collection.ArrayUtilities;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.ITransformer;

public class KnackDetailStatsGroup extends AbstractTextStatsGroup<IKnackStats> {

  private final IResources resources;

  public KnackDetailStatsGroup(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IKnackStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, null));
    } else {
      String[] details = ArrayUtilities.transform(
              stats.getDetailString(resources),
              String.class,
              new ITransformer<String, String>() {
                @Override
                public String transform(String input) {
                  return resources.getString(input);
                }
              });
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
