package net.sf.anathema.character.lunar.reporting.sheet;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.reporting.stats.heartsblood.*;
import net.sf.anathema.character.reporting.sheet.common.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.stats.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class SecondEditionLunarHeartsBloodEncoder extends AbstractStatsTableEncoder<IHeartsBloodStats> implements
    IPdfContentBoxEncoder {

  private final IResources resources;

  public SecondEditionLunarHeartsBloodEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    encodeTable(directContent, character, bounds);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Lunar.HeartsBlood"; //$NON-NLS-1$
  }

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
		        new HeartsBloodDexterityStatsGroup(resources),
		        new HeartsBloodStaminaStatsGroup(resources),
	  			new HeartsBloodAppearanceStatsGroup(resources),
	  			new HeartsBloodNotesStatsGroup(resources)};
  }

	@Override
	protected void encodeContent(PdfPTable table, IGenericCharacter character,
			Bounds bounds)
	{
		float heightLimit = bounds.height - 3;
		IHeartsBloodStats[] statSet = getPrintStats(character);
		IStatsGroup<IHeartsBloodStats>[] statGroups = createStatsGroups(character);

		//boolean encodeLine = true;
	    for (IHeartsBloodStats stats : statSet)
	    {
	    	if (table.getTotalHeight() < heightLimit)
	    	{
	    		encodeContentLine(table, statGroups, stats);
	    	}
	    }
	    while (table.getTotalHeight() < heightLimit)
	        encodeContentLine(table, statGroups, null);
	      table.deleteLastRow();
	}
	
	public boolean hasContent(IGenericCharacter character)
	  {
		  return true;
	  }
}