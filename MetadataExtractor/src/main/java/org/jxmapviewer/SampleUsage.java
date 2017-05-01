package org.jxmapviewer;

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

/**
 * Showcases the most popular ways of using the metadata-extractor library.
 * <p>
 * For more information, see the project wiki: https://github.com/drewnoakes/metadata-extractor/wiki/GettingStarted
 *
 * @author Drew Noakes https://drewnoakes.com
 */
public class SampleUsage
{
    /**
     * Executes the sample usage program.
     *
     * @param args command line parameters
     */
    public static void main(String[] args)
    {
        File file = new File("C:/Users/Lycéen/Desktop/test.jpg");
        System.out.println(file);
        System.out.println(" ");
        
        try {
            // We are only interested in handling
            Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());

            Metadata metadata = JpegMetadataReader.readMetadata(file, readers);

            print(metadata);
        } catch (JpegProcessingException e) {
            // handle exception
        } catch (IOException e) {
            // handle exception
        }
    }

    private static void print(Metadata metadata)
    {
        
        GpsDirectory gpsDir =(GpsDirectory)metadata.getFirstDirectoryOfType(GpsDirectory.class);
        double lat = gpsDir.getGeoLocation().getLatitude();
        double lon = gpsDir.getGeoLocation().getLongitude();
        //peut être utile Double.toString(lat)
        System.out.println("Latitude: " + lat );
        System.out.println("Longitude: " + lon);
    }
}