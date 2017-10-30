package com.econsys.Genriclibrery;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.poi.ss.formula.functions.Count;

import POC.econsys.Dates;
import POC.econsys.RandomNumber;

import com.econsys.TestData.Workbook;

public class EconsysVariables {
	static Workbook wb = new Workbook();
	Logger log = Logger.getLogger(EconsysVariables.class.getName());
	//Project Name!
	//public String prjname;
	public String prjname1;
	//RTQ inputs
	public String estimatedSize,location_inside, estimatedSize0to100k_, location, estimatedSize500_, 
	location_other, location_SouthEast, estimatedSize250_, eSizertq2, locationrtq2, eSizertq3, locationrtq3;
	//CP1-CP2 asign sales leader
	public String sl;
	//Cp1-Cp3
	public String ourformat, bidsheetauthorised, exeCP2, cp2cp3ourformat, cp2cp3bidsheetauthorised, exeCP3;
	//Status of Submit Resubmit quote
	public String quote_StatusCp2Cp3;
	public String quote_StatusCp3Cp4;
	//Customer commitment inputs
	public String customerCommitmentType,clarification;
	public static String cusrecivedas=null;
	public String questionsLOI;
	public String loi_Commencement;
	public String termsandcond, docrefrence, scope, docsatis, startdate, endate;
	public String final_quoted_scope, docs_Refrenced, project_Start_Date, project_End_Date;
	public String any_limitation_to_scope, any_limitation_to_expenditure, any_Time_limit_to_Instructions, any_other_Review;
	public String terms_and_conditions;
	public String atherize;
	public String execp4;
	
	//Cp4-Cp5 
	public String draftproduced, exe5_SalestoOper, exeCP5_OperationAccep,meeting,delegatetoPL,meetingwithSL;
	
	///Cp6
	public String execp6;
	//CP8 variables 
	public String certificateobtained, retationapplied, onmSubmitted, snagListIdentified, internalCompletionDocument;
	public String execp8;
	//CP9 variables
	public String finalAccountAgreement, finalRetentionPaid, projectDocumentArchived, projectDebrief;
	public String subContractAccountSettled, closureofProject, bondsGuarantees_Resolved;
	public String execp9;
	
	public String quoteStatusCCARecived = "Customer Commitment Received";
	public String quoteStatusAmendBid = "Amend Bid";
	public String quoteStatusWithdrawn = "Withdrawn";
	public String quoteStatusLost = "Lost";
	public String quoteStatusLostUpdate ="Update";
	public String customerCommitmentType_Email = "Email Received - Under Review";
	public String customerCommitmentType_LOI = "LOI Received - Under Review";
	public String customerCommitmentType_SubCon = "Sub-Contract Received - Under Review";
	public String customerCommitmentType_PO = "PO Received - Under Review";
	public String customerCommitmentType_Verbal = "Verbal Commitment Received - Under Review";
	
	//organization working now
	public String org_Name = "RCC Limited";
	public String org_Matrixs = "MatrixCS";
	public String org_4eg = "UberControls";//"UberControls"
	public String el,cl,pl;
	public String select_No = "No";
	public String select_Yes = "Yes";
	//public String org_ASQ = "ASQ";MatrixCS;
	
	{
		//amber
		
		estimatedSize0to100k_ = "A 0-100k";
		location_inside = "Inside M25";
		estimatedSize250_ = "C 250-500k";
		location_SouthEast = "South East";
		//Red
		estimatedSize500_ = "D 500-1000k";
		location_other = "Other";
		
		try {
			//Project Name *****
			//wb.setExcelData(1, 1, 2, "econAuto_"+Dates.dateFormat());
			//String prjname = "\""+wb.getXLData(1,2,1)+"\"";
			//log.info("Project name econsys variables: "+prjname);
		    //System.out.println("EV : "+prjname);
			prjname1= wb.getXLData(1,2,1);
			
			//***********RTQ inputs
			estimatedSize=wb.getXLData(8, 1, 1);
			location=wb.getXLData(9,2,1);
			
			//RTQ 2 in prepare quote
			eSizertq2 = wb.getXLData(2, 4, 1);
			locationrtq2 = wb.getXLData(4, 4, 1);
			//RTQ 3 in revised prepare quote
			 eSizertq3 = wb.getXLData(7, 4, 1);
			 locationrtq3 = wb.getXLData(9, 4, 1);
			 //Assign sales leader
			 sl=wb.getXLData(7,3, 0);
			//prepare quote variables
			ourformat = wb.getXLData(2, 5, 0);
			bidsheetauthorised =wb.getXLData(4, 5, 0);
			exeCP2=wb.getXLData(6, 5, 0);
			//cp2-cp3 prepare quote
			cp2cp3ourformat=wb.getXLData(13, 5, 0);
			cp2cp3bidsheetauthorised=wb.getXLData(15,5, 0);
			exeCP3=wb.getXLData(17,5, 0);
			
			//Status of submit resubmit quote
			quote_StatusCp2Cp3=wb.getXLData(9,5, 0);
			quote_StatusCp3Cp4=wb.getXLData(10,5, 0);
			
			//customer commitment variables
			questionsLOI=wb.getXLData(13,3,0);
			loi_Commencement=wb.getXLData(11,3,0);
			final_quoted_scope=wb.getXLData(2,7,0);
			docs_Refrenced=wb.getXLData(4,7, 0);
			terms_and_conditions=wb.getXLData(6,7, 0);
			any_limitation_to_scope=wb.getXLData(5,6,0);
			any_limitation_to_expenditure=wb.getXLData(7,6,0);
			any_Time_limit_to_Instructions=wb.getXLData(9,6,0);
			any_other_Review=wb.getXLData(11,6,0);
			/*cusrecivedas=wb.getXLData(2, 6, 0);
			startdate=wb.getXLData(10, 6, 0);
			endate=wb.getXLData(12, 6, 0);*/
			project_Start_Date=wb.getXLData(8,7, 0);
			project_End_Date=wb.getXLData(10,7, 0);
			clarification=wb.getXLData(14,7,0);
			customerCommitmentType=wb.getXLData(9, 3, 0);
			atherize=wb.getXLData(11,9, 0);
			execp4=wb.getXLData(18,7, 0);
			
			//cp4-cp5 variables
			el=wb.getXLData(21,5, 0);
			cl=wb.getXLData(23,5, 0);
			pl=wb.getXLData(25,5, 0);
			draftproduced=wb.getXLData(2,9, 0);
			exe5_SalestoOper=wb.getXLData(24,7, 0);
	   		exeCP5_OperationAccep=wb.getXLData(8, 9, 0);
	   		meeting=wb.getXLData(22,7,0);
	   		delegatetoPL=wb.getXLData(22,7,0);
	   		meetingwithSL=wb.getXLData(6, 9, 0);
	   		
	   		//cp6
	   		execp6=wb.getXLData(4, 11, 0);
	   		
	   		//Obtain practical completion
	   		certificateobtained=wb.getXLData(6,11, 0);
	   		retationapplied=wb.getXLData(8,11, 0);
	   		onmSubmitted=wb.getXLData(10,11, 0);
	   		snagListIdentified=wb.getXLData(12,11, 0);
	   		internalCompletionDocument=wb.getXLData(14,11, 0);
	   		execp8=wb.getXLData(16,11, 0);
	   		
	   		//Post practical completion
	   		finalAccountAgreement=wb.getXLData(18,11, 0);
	   		finalRetentionPaid=wb.getXLData(20,11, 0);
	   		projectDocumentArchived=wb.getXLData(22,11, 0);
	   		projectDebrief=wb.getXLData(18,11, 0);
	   		subContractAccountSettled=wb.getXLData(1,12, 0);
	   		closureofProject=wb.getXLData(3,12, 0);
	   		bondsGuarantees_Resolved=wb.getXLData(5,12, 0);
	   		execp9=wb.getXLData(7,12, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String projectName() throws IOException{
		String prjName = "\""+wb.getXLData(1,2,1)+"\"";
		return prjName;
	}
	
	
}
