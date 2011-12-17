package net.sf.anathema.character.lunar.reporting.sheet;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.reporting.stats.heartsblood.HeartsBloodNameStatsGroup;
import net.sf.anathema.character.lunar.reporting.stats.heartsblood.HeartsBloodStaminaStatsGroup;
import net.sf.anathema.character.lunar.reporting.stats.heartsblood.HeartsBloodStrengthStatsGroup;
import net.sf.anathema.character.lunar.reporting.stats.heartsblood.IHeartsBloodStats;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.character.reporting.common.stats.IStatsGroup;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class FirstEditionLunarHeartsBloodEncoder extends AbstractFixedLineStatsTableEncoder<IHeartsBloodStats> implements
    IPdfContentBoxEncoder {

  private final IResources resources;

  public FirstEditionLunarHeartsBloodEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    encodeTable(directContent, character, bounds);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Lunar.HeartsBlood"; //$NON-NLS-1$
  }

  @Override
  protected int getLineCount() {
    return 11;
  }

  @Override
  protected IHeartsBloodStats[] getPrintStats(IGenericCharacter character) {
    IHeartsBloodModel model = (IHeartsBloodModel) character.getAdditionalModel(HeartsBloodTemplate.TEMPLATE_ID);
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
  protected IStatsGroup<IHeartsBloodStats>[] createStatsGroups(IGenericCharacter character) {
	    return new IStatsGroup[] {
	        new HeartsBloodNameStatsGroup(resources),
	        new HeartsBloodStrengthStatsGroup(resources),
	        new HeartsBloodStaminaStatsGroup(resources) };
		  
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
