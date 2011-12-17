package net.sf.anathema.character.thaumaturgy.reporting;

import static net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants.LINE_HEIGHT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.encoder.PdfTraitEncoder;
import net.sf.anathema.character.reporting.extended.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.thaumaturgy.ThaumaturgyAdditionalModel;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyMagic;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyModel;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyDegree;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyProcedure;
import net.sf.anathema.character.thaumaturgy.template.ThaumaturgyTemplate;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class ThaumaturgyEncoder implements IPdfContentBoxEncoder
{
//	private final IResources resources;
//	private final BaseFont baseFont;
	private final Font boldFont;
	private final Font regularFont;
	private final PdfTraitEncoder traitEncoder;

	public ThaumaturgyEncoder(IResources resources, BaseFont baseFont)
	{
	//	this.resources = resources;
	//	this.baseFont = baseFont;
		this.regularFont = TableEncodingUtilities.createFont(baseFont);
		this.boldFont = TableEncodingUtilities.createFont(baseFont);
		this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
	    boldFont.setStyle(Font.BOLD);
	}

	@Override
	public void encode(PdfContentByte directContent,
			IGenericCharacter character, IGenericDescription description, Bounds bounds)
			throws DocumentException {
		IThaumaturgyModel model = ((ThaumaturgyAdditionalModel)character.getAdditionalModel(ThaumaturgyTemplate.ID)).
			getThaumaturgyModel();
		float horizPadding = 5;
		float columnWidth = bounds.width;
		float contentWidth = columnWidth - 2 * horizPadding;
	    float initialY = bounds.getMaxY() - LINE_HEIGHT;
	    float initialX = bounds.x + horizPadding;
	    float yPosition = initialY;
	    float xPosition = initialX;
	    
	    Map<String, List<IThaumaturgyMagic>> artMap = new HashMap<String, List<IThaumaturgyMagic>>();
	    for (IThaumaturgyMagic degree : model.getLearnedDegrees())
	    {
	    	List<IThaumaturgyMagic> artList = new ArrayList<IThaumaturgyMagic>();
	    	artList.add(degree);
	    	artMap.put(degree.getArt(), artList);
	    }
	    for (IThaumaturgyMagic procedure : model.getLearnedProcedures())
	    {
	    	List<IThaumaturgyMagic> artList = artMap.get(procedure.getArt());
	    	if (artList == null)
	    	{
	    		artList = new ArrayList<IThaumaturgyMagic>();
		    	artMap.put(procedure.getArt(), artList);
	    	}
	    	artList.add(procedure);
	    }
	    
	    for (String art : artMap.keySet())
	    {
	    	for (IThaumaturgyMagic magic : artMap.get(art))
	    	{
	    		if (yPosition < bounds.getMinY())
	  		      if (xPosition == initialX)
	  		    	  xPosition += columnWidth;
	  		      else
	  		    	  return;
	    		
	    		if (magic == artMap.get(art).get(0) &&
	    			!(magic instanceof ThaumaturgyDegree))
	    		{
	    			encodeTextLine(directContent, xPosition, yPosition, contentWidth, magic.getArt(), false);
	    			yPosition -= LINE_HEIGHT;
	    		}
	    		if (magic instanceof ThaumaturgyDegree)
	    			encodeTrait(directContent, xPosition, yPosition, contentWidth, magic);
	    		if (magic instanceof ThaumaturgyProcedure)
	    			encodeTextLine(directContent, xPosition, yPosition, contentWidth, " - " + magic.getProcedure(), false);
	    		yPosition -= LINE_HEIGHT;
	    	}
	    }
	    //encodeEmptyLines(directContent, bounds, yPosition);
	}
	
	private void encodeTrait(PdfContentByte directContent, float xPos, float yPos, float width, IThaumaturgyMagic art)
	{
		Position position = new Position(xPos, yPos);
	    int value = art.getCurrentValue();
	    traitEncoder.encodeWithText(directContent, art.getArt(), position, width, value, 3);
	}
	
	private void encodeTextLine(PdfContentByte directContent, float xPos, float yPos, float width, String text, boolean bold)
		throws DocumentException
	{
		Font font = bold ? boldFont : regularFont;
		Phrase phrase = new Phrase();
	    phrase.add(new Chunk(text, font));
	    Bounds bounds = new Bounds(xPos, yPos, width, LINE_HEIGHT);
	    PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, LINE_HEIGHT);
	}
	
	/*private boolean hasArt(List<String> artList, IThaumaturgyMagic magic)
	{
		for (String art : artList)
			if (art.equals(magic.getArt()))
				return true;
		return false;
	}*/

	@Override
	public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
		return "Thaumaturgy";
	}
	
	public boolean hasContent(IGenericCharacter character)
	{
		IThaumaturgyModel model = ((ThaumaturgyAdditionalModel)character.getAdditionalModel(ThaumaturgyTemplate.ID)).
			getThaumaturgyModel();
		return model.getLearnedDegrees().size() > 0 ||
			   model.getLearnedProcedures().size() > 0;
	}
}
