package net.sf.anathema.character.lunar.reporting.sheet;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthAndMovemenTableEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionLunarHealthAndMovementEncoder extends SecondEditionHealthAndMovementEncoder
{
	final IGenericCharacter character;

	public SecondEditionLunarHealthAndMovementEncoder(IResources resources,
			BaseFont baseFont, BaseFont symbolBaseFont, IGenericCharacter character)
	{
		super(resources, baseFont, symbolBaseFont);
		this.character = character;
	}
	
	@Override
	protected final IPdfTableEncoder createTableEncoder()
	{
		return new SecondEditionHealthAndMovemenTableEncoder(getResources(), getBaseFont())
		{
			protected IGenericTraitCollection getTraits(IGenericCharacter character)
			{
				return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getBeastTraitCollection();
			}
		};
	}

}
