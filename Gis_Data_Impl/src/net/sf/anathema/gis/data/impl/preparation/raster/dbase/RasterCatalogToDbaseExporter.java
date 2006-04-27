package net.sf.anathema.gis.data.impl.preparation.raster.dbase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.geotools.data.shapefile.dbf.DbaseFileException;
import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileWriter;

import com.vividsolutions.jts.geom.Envelope;

public class RasterCatalogToDbaseExporter {

  private static final int DECIMAL_COUNT = 10;
  private static final int FIELD_ASCII_LENGTH = 128;
  private static final int FIELD_NUMBER_LENGTH = 18;
  private static final String YMAX = "YMAX"; //$NON-NLS-1$
  private static final String XMAX = "XMAX"; //$NON-NLS-1$
  private static final String YMIN = "YMIN"; //$NON-NLS-1$
  private static final String XMIN = "XMIN"; //$NON-NLS-1$
  private static final String IMAGE = "IMAGE"; //$NON-NLS-1$

  public void writeDbaseFile(final File dbaseFile, ICatalogTile[] catalog) throws IOException, DbaseFileException {
    DbaseFileHeader header = createDBHeader(catalog);
    FileChannel out = new FileOutputStream(dbaseFile.getCanonicalFile()).getChannel();
    DbaseFileWriter fileWriter = new DbaseFileWriter(header, out);
    for (ICatalogTile tile : catalog) {
      fileWriter.write(convertToArray(tile.getRelativeFileName(), tile.getEnvelope()));
    }
    fileWriter.close();
  }

  private DbaseFileHeader createDBHeader(ICatalogTile[] catalog) throws DbaseFileException {
    DbaseFileHeader header = new DbaseFileHeader();
    header.addColumn(IMAGE, 'C', FIELD_ASCII_LENGTH, DECIMAL_COUNT);
    header.addColumn(XMIN, 'N', FIELD_NUMBER_LENGTH, DECIMAL_COUNT);
    header.addColumn(YMIN, 'N', FIELD_NUMBER_LENGTH, DECIMAL_COUNT);
    header.addColumn(XMAX, 'N', FIELD_NUMBER_LENGTH, DECIMAL_COUNT);
    header.addColumn(YMAX, 'N', FIELD_NUMBER_LENGTH, DECIMAL_COUNT);
    header.setNumRecords(catalog.length);
    return header;
  }

  private Object[] convertToArray(String fileName, Envelope envelope) {
    Object[] values = new Object[5];
    values[0] = fileName;
    values[1] = new Double(envelope.getMinX());
    values[2] = new Double(envelope.getMinY());
    values[3] = new Double(envelope.getMaxX());
    values[4] = new Double(envelope.getMaxY());
    return values;
  }
}