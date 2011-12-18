package net.sf.anathema.character.infernal.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.FavorableTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class InfernalYoziListEncoder extends FavorableTraitEncoder implements IVariableBoxContentEncoder
{
	private final float lineHeight = 16;
	
	public InfernalYoziListEncoder(BaseFont baseFont, IResources resources) {
		super(baseFont, resources, 0);
	}

	@Override
	public float getRequestedHeight(ReportContent content, float width) {
		return lineHeight * YoziType.values().length;
	}

	@Override
	public String getHeaderKey(ReportContent reportContent) {
		return "Infernal.Yozis";
	}

	@Override
	public boolean hasContent(ReportContent content) {
		return true;
	}

	@Override
	protected String getGroupNamePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(
			IGenericCharacter character) {
		return character.getYoziTypeGroups();
	}

}
