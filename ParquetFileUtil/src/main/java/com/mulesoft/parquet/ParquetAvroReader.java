package com.mulesoft.parquet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;

public class ParquetAvroReader
{
    /* public static List<String> read(String inputFilePath) throws IOException
    {
    	System.out.println("Input File Path === " + inputFilePath);
    	Path filePath = new Path(inputFilePath);
        Configuration configuration = new Configuration();
        configuration.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        HadoopInputFile inputFile = HadoopInputFile.fromPath(filePath, configuration);

        try (ParquetReader<GenericData.Record> reader = AvroParquetReader
                .<GenericData.Record>builder(inputFile)
                .withConf(configuration)
                .build())
        {
            GenericData.Record record;
            List<String> outpatientClaimsRecords = new ArrayList<String>();
            while ((record = reader.read()) != null)
            {
            	outpatientClaimsRecords.add(record.toString());
            	
            }
            return outpatientClaimsRecords;
        }
    } */
	
	public static List<String> read(String inputFilePath) throws IOException
    {
    	System.out.println("Input File Path === " + inputFilePath);
    	Path filePath = new Path(inputFilePath);
    	Configuration configuration = new Configuration();
    	//configuration.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
    	//configuration.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        
        try (ParquetReader<GenericData.Record> reader = AvroParquetReader
                .<GenericData.Record>builder(filePath)
                .withConf(configuration)
                .build())
        {
            GenericData.Record record;
            List<String> outpatientClaimsRecords = new ArrayList<String>();
            while ((record = reader.read()) != null)
            {
            	outpatientClaimsRecords.add(record.toString());
            	
            }
            return outpatientClaimsRecords;
        }
    }
	

    public static void main(String[] args) throws Exception
    {
        try
        {
            List<String> result = read("/Users/kalyana.venkata/Desktop/FL-Citizens/Parquet/outpatient_claims.parquet");
            for (int i=0; i<result.size(); i++) {
            	System.out.println(result.get(i));
            }
            System.out.println("Parquet Processing Completed!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
