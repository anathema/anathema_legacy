package net.sf.anathema.character.lunar.reporting.rendering.heartsblood;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodNameStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodStaminaStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.HeartsBloodStrengthStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.heartsblood.IHeartsBloodStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class FirstEditionLunarHeartsBloodEncoder extends AbstractFixedLineStatsTableEncoder<IHeartsBloodStats> implements IBoxContentEncoder {

  private final IResources resources;

  public FirstEditionLunarHeartsBloodEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    encodeTable(graphics, reportContent, bounds);
  }

  public String getHeaderKey(ReportContent content) {
    return "Lunar.HeartsBlood"; //$NON-NLS-1$
  }

  @Override
  protected int getLineCount() {
    return 11;
  }

  @Override
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
	    return new IStatsGroup[] {
	        new HeartsBloodNameStatsGroup(resources),
	        new HeartsBloodStrengthStatsGroup(resources),
	        new HeartsBloodStaminaStatsGroup(resources) };
		  
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
