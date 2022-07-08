package com.mulesoft.parquet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

public class ParquetAvroWriter
{
    private static final Schema SCHEMA; 
    private static final String SCHEMA_PATH = "/Users/kalyana.venkata/Desktop/FL-Citizens/ParquetSchemas/outpatient_claims.avsc";
    private static final Path OUTPUT_PATH = new Path("/Users/kalyana.venkata/Desktop/FL-Citizens/Parquet/outpatient_claims_10Records.parquet");
    
    static
    {
        try (InputStream inStream = new FileInputStream(new File(SCHEMA_PATH))) 
        {
            SCHEMA = new Schema.Parser().parse(IOUtils.toString(inStream, "UTF-8"));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Can't read SCHEMA file from " + SCHEMA_PATH, e);
        }
    }

    public void write(List<GenericData.Record> records, Path filePath) throws IOException
    {
        try (ParquetWriter<GenericData.Record> writer = AvroParquetWriter
                .<GenericData.Record>builder(filePath)
                .withSchema(SCHEMA)
                .withConf(new Configuration())
                .withCompressionCodec(CompressionCodecName.SNAPPY)
                .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
                .build())
        {

            for (GenericData.Record record : records)
            {
                writer.write(record);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        try
        {
            GenericData.Record record = null;
            
            List<GenericData.Record> records = new ArrayList<GenericData.Record>();
        	File inputCSVFile = new File("/Users/kalyana.venkata/Desktop/FL-Citizens/Outpatient/Outpatient_Claims_PoC_10Records.csv");
        	Scanner scn = new Scanner(inputCSVFile);
        	
        	if (scn.hasNextLine()) {
        		scn.nextLine();
        	}
        	
        	while(scn.hasNextLine()) {
        		List<String> values = new ArrayList<String>();
        		try(Scanner rowScanner = new Scanner(scn.nextLine())) {
        			rowScanner.useDelimiter(",");
        			while(rowScanner.hasNext()) {
        				values.add(rowScanner.next());
        			}
        			
        			rowScanner.close();
        			String[] columns = new String[values.size()];
        			columns = (String[]) values.toArray(columns);

        			record = new GenericData.Record(SCHEMA);
            		
            		record.put("DESYNPUF_ID", columns[0]);
            		record.put("CLM_ID", columns[1]);
            		record.put("SEGMENT", columns[2]);
            		record.put("CLM_FROM_DT", columns[3]);
            		record.put("CLM_THRU_DT", columns[4]);
            		record.put("PRVDR_NUM", columns[5]);
            		record.put("CLM_PMT_AMT", columns[6]);
            		record.put("NCH_PRMRY_PYR_CLM_PD_AMT", columns[7]);
            		record.put("AT_PHYSN_NPI", columns[8]);
            		record.put("OP_PHYSN_NPI", columns[9]);
            		record.put("OT_PHYSN_NPI", columns[10]);
            		record.put("NCH_BENE_BLOOD_DDCTBL_LBLTY_AM", columns[11]);
            		record.put("ICD9_DGNS_CD_1", columns[12]);
            		record.put("ICD9_DGNS_CD_2", columns[13]);
            		record.put("ICD9_DGNS_CD_3", columns[14]);
            		record.put("ICD9_DGNS_CD_4", columns[15]);
            		record.put("ICD9_DGNS_CD_5", columns[16]);
            		record.put("ICD9_DGNS_CD_6", columns[17]);
            		record.put("ICD9_DGNS_CD_7", columns[18]);
            		record.put("ICD9_DGNS_CD_8", columns[19]);
            		record.put("ICD9_DGNS_CD_9", columns[20]);
            		record.put("ICD9_DGNS_CD_10", columns[21]);
            		record.put("ICD9_PRCDR_CD_1", columns[22]);
            		record.put("ICD9_PRCDR_CD_2", columns[23]);
            		record.put("ICD9_PRCDR_CD_3", columns[24]);
            		record.put("ICD9_PRCDR_CD_4", columns[25]);
            		record.put("ICD9_PRCDR_CD_5", columns[26]);
            		record.put("ICD9_PRCDR_CD_6", columns[27]);
            		record.put("NCH_BENE_PTB_DDCTBL_AMT", columns[28]);
            		record.put("NCH_BENE_PTB_COINSRNC_AMT", columns[29]);
            		record.put("ADMTNG_ICD9_DGNS_CD", columns[30]);
            		record.put("HCPCS_CD_1", columns[31]);
            		record.put("HCPCS_CD_2", columns[32]);
            		record.put("HCPCS_CD_3", columns[33]);
            		record.put("HCPCS_CD_4", columns[34]);
            		record.put("HCPCS_CD_5", columns[35]);
            		record.put("HCPCS_CD_6", columns[36]);
            		record.put("HCPCS_CD_7", columns[37]);
            		record.put("HCPCS_CD_8", columns[38]);
            		record.put("HCPCS_CD_9", columns[39]);
            		record.put("HCPCS_CD_10", columns[40]);
            		record.put("HCPCS_CD_11", columns[41]);
            		record.put("HCPCS_CD_12", columns[42]);
            		record.put("HCPCS_CD_13", columns[43]);
            		record.put("HCPCS_CD_14", columns[44]);
            		record.put("HCPCS_CD_15", columns[45]);
            		record.put("HCPCS_CD_16", columns[46]);
            		record.put("HCPCS_CD_17", columns[47]);
            		record.put("HCPCS_CD_18", columns[48]);
            		record.put("HCPCS_CD_19", columns[49]);
            		record.put("HCPCS_CD_20", columns[50]);
            		record.put("HCPCS_CD_21", columns[51]);
            		record.put("HCPCS_CD_22", columns[52]);
            		record.put("HCPCS_CD_23", columns[53]);
            		record.put("HCPCS_CD_24", columns[54]);
            		record.put("HCPCS_CD_25", columns[55]);
            		record.put("HCPCS_CD_26", columns[56]);
            		record.put("HCPCS_CD_27", columns[57]);
            		record.put("HCPCS_CD_28", columns[58]);
            		record.put("HCPCS_CD_29", columns[59]);
            		record.put("HCPCS_CD_30", columns[60]);
            		record.put("HCPCS_CD_31", columns[61]);
            		record.put("HCPCS_CD_32", columns[62]);
            		record.put("HCPCS_CD_33", columns[63]);
            		record.put("HCPCS_CD_34", columns[64]);
            		record.put("HCPCS_CD_35", columns[65]);
            		record.put("HCPCS_CD_36", columns[66]);
            		record.put("HCPCS_CD_37", columns[67]);
            		record.put("HCPCS_CD_38", columns[68]);
            		record.put("HCPCS_CD_39", columns[69]);
            		record.put("HCPCS_CD_40", columns[70]);
            		record.put("HCPCS_CD_41", columns[71]);
            		record.put("HCPCS_CD_42", columns[72]);
            		record.put("HCPCS_CD_43", columns[73]);
            		record.put("HCPCS_CD_44", columns[74]);
            		record.put("HCPCS_CD_45", "");
            		
            		records.add(record);
        		}
        	}
        	scn.close();
            
            ParquetAvroWriter writer = new ParquetAvroWriter();
            writer.write(records, OUTPUT_PATH);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
