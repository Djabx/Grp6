package Maven.Quickstart;

import com.drew.imaging.ImageMetadataReader;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.exif.GpsDescriptor;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.metadata.iptc.IptcReader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.SingleSelectionModel;


public class ExtraireDonnees{

	public static double LaLatitude(File fichier) throws JpegProcessingException, IOException {
		Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

		Metadata metadata = JpegMetadataReader.readMetadata(fichier, readers);

		GpsDirectory gpsDir =(GpsDirectory)metadata.getFirstDirectoryOfType(GpsDirectory.class);
		double lat = gpsDir.getGeoLocation().getLatitude();
		
		return lat;
	}
	
	public static double LaLongitude(File fichier) throws JpegProcessingException, IOException {
		Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

		Metadata metadata = JpegMetadataReader.readMetadata(fichier, readers);

		GpsDirectory gpsDir =(GpsDirectory)metadata.getFirstDirectoryOfType(GpsDirectory.class);
		double lon = gpsDir.getGeoLocation().getLongitude();
		
		return lon;
	}
}