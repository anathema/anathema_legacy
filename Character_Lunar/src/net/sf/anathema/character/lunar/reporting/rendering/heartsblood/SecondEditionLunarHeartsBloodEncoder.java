package net.sf.anathema.character.lunar.reporting.rendering.heartsblood;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodAppearanceStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodDexterityStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodNameStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodNotesStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodStaminaStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodStrengthStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.IHeartsBloodStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractStatsTableEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class SecondEditionLunarHeartsBloodEncoder extends AbstractStatsTableEncoder<IHeartsBloodStats> implements IBoxContentEncoder {

  private final IResources resources;

  public SecondEditionLunarHeartsBloodEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    encodeTable(graphics.getDirectContent(), reportContent, graphics.getBounds());
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Lunar.HeartsBlood"; //$NON-NLS-1$
  }

  protected IHeartsBloodStats[] getPrintStats(ReportContent content) {
    IHeartsBloodModel model = (IHeartsBloodModel) content.getCharacter().getAdditionalModel(HeartsBloodTemplate.TEMPLATE_ID);
    List<IHeartsBloodStats> stats = new ArrayList<IHeartsBloodStats>();
    for (final IAnimalForm form : model.getEntries()) {
      stats.add(new IHeartsBloodStats() {
        public IIdentificate getName() {
          return new Identificate(form.getName());
        }

        public String getAppearanceString() {
          return String.valueOf(form.getAppearance());
        }

        public String getStaminaString() {
          return String.valueOf(form.getStamina());
        }

        public String getDexterityString() {
          return String.valueOf(form.getDexterity());
        }

        public String getStrengthString() {
          return String.valueOf(form.getStrength());
        }
      });
    }
    return stats.toArray(new IHeartsBloodStats[stats.size()]);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IHeartsBloodStats>[] createStatsGroups(ReportContent content) {
    return new IStatsGroup[]{new HeartsBloodNameStatsGroup(resources), new HeartsBloodStrengthStatsGroup(resources),
                             new HeartsBloodDexterityStatsGroup(resources), new HeartsBloodStaminaStatsGroup(resources),
                             new HeartsBloodAppearanceStatsGroup(resources), new HeartsBloodNotesStatsGroup(resources)};
  }

  @Override
  protected void encodeContent(PdfPTable table, ReportContent content, Bounds bounds) {
    float heightLimit = bounds.height - 3;
    IHeartsBloodStats[] statSet = getPrintStats(content);
    IStatsGroup<IHeartsBloodStats>[] statGroups = createStatsGroups(content);

    //boolean encodeLine = true;
    for (IHeartsBloodStats stats : statSet) {
      if (table.getTotalHeight() < heightLimit) {
        encodeContentLine(table, statGroups, stats);
      }
    }
    while (table.getTotalHeight() < heightLimit) {
      encodeContentLine(table, statGroups, null);
    }
    table.deleteLastRow();
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
