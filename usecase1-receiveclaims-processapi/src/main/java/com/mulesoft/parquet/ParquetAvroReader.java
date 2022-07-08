package com.mulesoft.parquet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.GenericData;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;

public class ParquetAvroReader
{
	public static List<String> read(byte[] b) throws IOException
    {
		ParquetStream ps = new ParquetStream("Outpatient", b);
		Configuration configuration = new Configuration();
    	
		try (ParquetReader<GenericData.Record> reader = AvroParquetReader
                .<GenericData.Record>builder(ps)
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
            List<String> result = read(FileUtils.readFileToByteArray(new File("/Users/kalyana.venkata/Desktop/FL-Citizens/Parquet/outpatient_claims.parquet")));
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
