package net.sf.anathema.character.lunar.reporting.rendering.heartsblood;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodNameStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodStaminaStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodStrengthStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.IHeartsBloodStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class FirstEditionLunarHeartsBloodEncoder extends AbstractFixedLineStatsTableEncoder<IHeartsBloodStats> implements ContentEncoder {

  private final IResources resources;

  public FirstEditionLunarHeartsBloodEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    encodeTable(graphics, reportContent, bounds);
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Lunar.HeartsBlood");
  }

  @Override
  protected int getLineCount(ReportContent content) {
    return 11;
  }

  @Override
  protected IHeartsBloodStats[] getPrintStats(ReportContent content) {
    IHeartsBloodModel model = (IHeartsBloodModel) content.getCharacter().getAdditionalModel(HeartsBloodTemplate.TEMPLATE_ID);
    List<IHeartsBloodStats> stats = new ArrayList<IHeartsBloodStats>();
    for (final IAnimalForm form : model.getEntries()) {
      stats.add(new IHeartsBloodStats() {
        @Override
        public IIdentificate getName() {
          return new Identificate(form.getName());
        }

        @Override
        public String getAppearanceString() {
          return String.valueOf(form.getAppearance());
        }

        @Override
        public String getStaminaString() {
          return String.valueOf(form.getStamina());
        }

        @Override
        public String getDexterityString() {
          return String.valueOf(form.getDexterity());
        }

        @Override
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
    return new IStatsGroup[]{new HeartsBloodNameStatsGroup(resources), new HeartsBloodStrengthStatsGroup(resources), new HeartsBloodStaminaStatsGroup(resources)};

  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
