package net.sf.anathema.lib.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.EntityResolver;

public class DocumentUtilities {

  // We should save all documents in an encoding which can encode any valid string.
  private static final String DEFAULT_ENCODING = "UTF-8"; //$NON-NLS-1$

  private DocumentUtilities() {
    // Nothing to do
  }

  public static void save(Document document, File file) throws IOException {
    OutputStream outputStream = null;
    try {
      outputStream = new FileOutputStream(file);
      save(document, outputStream);
    }
    finally {
      IOUtilities.close(outputStream);
    }
  }

  public static void save(Document document, OutputStream outputStream) throws IOException {
    save(document, new XMLWriter(outputStream, createOutputFormat()));
  }

  public static void save(Document document, Writer writer) throws IOException {
    save(document, new XMLWriter(writer, createOutputFormat()));
  }

  private static void save(Document document, XMLWriter writer) throws IOException {
    try {
      writer.write(document);
      writer.flush();
    }
    catch (UnsupportedEncodingException e) {
      throw new IOException(e.getMessage());
    }
  }

  private static void save(Document document, String encoding, Writer writer) throws IOException {
    save(document, new XMLWriter(writer, createOutputFormat(encoding)));
  }

  private static OutputFormat createOutputFormat() {
    return createOutputFormat(DEFAULT_ENCODING);
  }

  private static OutputFormat createOutputFormat(String encoding) {
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding(encoding);
    return format;
  }

  public static String asString(Document document) {
    return asString(document, DEFAULT_ENCODING);
  }

  public static String asString(Document document, String encoding) {
    StringWriter writer = new StringWriter();
    try {
      DocumentUtilities.save(document, encoding, writer);
      return writer.toString();
    }
    catch (IOException e) {
      // throw new RuntimeException(e); // JDK1.4
      RuntimeException runtimeException = new RuntimeException(e.toString());
      runtimeException.fillInStackTrace();
      throw runtimeException;
    }
    finally {
      IOUtilities.close(writer);
    }
  }

  public static Document read(String source) throws AnathemaException {
    return read(new StringReader(source));
  }

  public static Document read(Reader reader) throws AnathemaException {
    try {
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(reader);
      return document;
    }
    catch (DocumentException exception) {
      throw new AnathemaException(exception);
    }
  }

  public static Document read(File file) throws FileNotFoundException, AnathemaException {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(file);
      return read(inputStream);
    }
    finally {
      IOUtilities.close(inputStream);
    }
  }

  public static Document read(InputStream in) throws PersistenceException {
    return read(in, null);
  }

  public static Document read(InputStream inputStream, String systemId) throws PersistenceException {
    try {
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(inputStream, systemId);
      return document;
    }
    catch (DocumentException exception) {
      throw new PersistenceException(exception);
    }
  }

  public static Document read(InputStream inputStream, String systemId, EntityResolver resolver)
      throws AnathemaException {
    try {
      SAXReader saxReader = new SAXReader();
      saxReader.setEntityResolver(resolver);
      Document document = saxReader.read(inputStream, systemId);
      return document;
    }
    catch (DocumentException exception) {
      throw new AnathemaException(exception);
    }
  }

  public static Element getRootElement(Document document, String expectedRootElementName) throws PersistenceException {
    Element rootElement = document.getRootElement();
    if (!expectedRootElementName.equals(rootElement.getName())) {
      throw new PersistenceException(
          "Element '" + expectedRootElementName + "' expected, was:'" + rootElement.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    return rootElement;
  }

  /**
   * Finds the first occurrence of an element in a document.
   * 
   * @param document the document to be searched for the element. Must not be null.
   * @param childName the name of the element to be searched for. Must not be null.
   * @return the found element or null if it could not be found
   * @throws NullPointerException thrown if any of the arguments is null
   */
  public static Element findElement(Document document, String childName) {
    XPath xpath = document.createXPath("//"+childName+"[1]");
    Object result = xpath.evaluate(document.getRootElement());
    if (result instanceof List) {
      return null;
    }
    return (Element) result;
  }
}