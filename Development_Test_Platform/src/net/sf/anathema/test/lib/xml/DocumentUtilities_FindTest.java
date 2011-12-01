package net.sf.anathema.test.lib.xml;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;

public class DocumentUtilities_FindTest {

  @Test
  public void findsNullIfThereIsNoMatch() throws Exception {
    Document document = DocumentUtilities.read("<A><B></B></A>");
    String childName = "C";
    Element element = DocumentUtilities.findElement(document, childName);
    assertThat(element, is(nullValue()));
  }
  
  @Test
  public void findsElementInAnyDepth() throws Exception {
    Document document = DocumentUtilities.read("<A><B><C></C></B></A>");
    String childName = "C";
    Element element = DocumentUtilities.findElement(document, childName);
    assertThat(element.getName(), is("C"));
  }
  
  @Test
  public void findsOnlyOneElement() throws Exception {
    Document document = DocumentUtilities.read("<A><B><C></C><C></C></B></A>");
    String childName = "C";
    Element element = DocumentUtilities.findElement(document, childName);
    assertThat(element.getName(), is("C"));
  }
}