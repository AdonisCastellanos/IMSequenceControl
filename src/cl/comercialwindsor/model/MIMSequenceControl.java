package cl.comercialwindsor.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_DocType;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MMailText;
import org.compiere.model.MOrg;
import org.compiere.model.MSequence;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Util;

public class MIMSequenceControl extends X_IM_SequenceControl {

	
	public MIMSequenceControl(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MIMSequenceControl(Properties ctx, int IM_SequenceControl_ID, String trxName) {
		super(ctx, IM_SequenceControl_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public void ValidateDocumentTypeSequence() {
		I_C_DocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		if(docType==null)
			throw new AdempiereException("@Mandatory@:@C_DocType_ID@");
		if(docType.getDocNoSequence_ID()<=0)
			throw new AdempiereException("@Mandatory@:@DocNoSequence_ID@ on DocumentType");	
	}
	
	public String AddNumberByReverseIndex(String number, int index) {
		if(index>number.length())
			return "1"+number;
		char [] chars = number.toCharArray();
		int updateIndex = chars.length-index;
		int indexNumber = Integer.parseInt(String.valueOf(chars[updateIndex]))+1;
		if(indexNumber<10) {
			chars[updateIndex] = Character.forDigit(indexNumber, 10);
			System.out.println(String.valueOf(chars));
			return String.valueOf(chars);
		}
		
		chars[updateIndex] = Character.forDigit(0, 10);
		return AddNumberByReverseIndex(String.valueOf(chars), index+1);
	}
	/*
	public String GenerateNextSequenceByRange(String sequence, int range) {
		
		int rangeLength = String.valueOf(range).length();
		String actualRange = sequence.substring(sequence.length()-rangeLength,sequence.length());
		if(actualRange!=null) {
			int integerRange = Integer.parseInt(actualRange);		
			String nextInitialSequence = sequence.substring(0,sequence.length()-rangeLength)+range;
			if(integerRange<range) {
				return nextInitialSequence;
			}else {
				return	AddNumberByReverseIndex(nextInitialSequence, rangeLength+1);
			}
			
		}		
		return "";
	}
	
	public String GenerateNextInitialSequence() {
		StringBuilder sql = new StringBuilder("SELECT FinalSequence FROM ").append(Table_Name);
		
		sql.append(" WHERE IsActive='Y' AND FinalSequence<>'' AND ")
		.append(COLUMNNAME_IM_SequenceControl_ID).append("<>").append(get_ID())
		.append(" ORDER BY FinalSequence DESC")
		;

		String seq = DB.getSQLValueStringEx(get_TrxName(), sql.toString());
		
		if(seq==null || seq.equals("")) {
			return GenerateNextSequenceByRange(getPreviousSequence(),getInitialRange());
		}
		
		return GenerateNextSequenceByRange(seq,getInitialRange());
	}
	
	
	public String GenerateNextFinalSequence() {
		
		return GenerateNextSequenceByRange(getInitialSequence(),getFinalRange());
	}*/
	
	public static MIMSequenceControl GetSequenceControlForSequence(Properties ctx, String trxName,int sequence , int ExcludedSequenceControl_ID){
		
		String whereClause = " "+ sequence+ " BETWEEN "+COLUMNNAME_InitialRange+" AND "+ COLUMNNAME_FinalRange;
		
		if(ExcludedSequenceControl_ID>0)
			whereClause += " AND "+COLUMNNAME_IM_SequenceControl_ID+"<>"+ExcludedSequenceControl_ID;
		
		MIMSequenceControl seq = new Query(ctx,MTable.get(ctx, MIMSequenceControl.Table_Name), whereClause, trxName).setOnlyActiveRecords(true).first();
		
		return seq;
	}
	public static MIMSequenceControl GetSequenceControlForSequence(Properties ctx, String trxName, int sequence){
		return MIMSequenceControl.GetSequenceControlForSequence(ctx, trxName, sequence, -1);
	}
	
	
	public void ValidateInitialSequence() {
		
		MIMSequenceControl seq = MIMSequenceControl.GetSequenceControlForSequence(getCtx(), get_TrxName(), getInitialRange(), get_ID());
		
		if(seq!=null){
			throw new AdempiereException("Ya existe una secuencia que cubre el rango inicial de: "+seq.getInitialRange()+" a "+seq.getFinalRange());
		}
		
	}
	public void ValidateFinalSequence() {
		
		MIMSequenceControl seq = MIMSequenceControl.GetSequenceControlForSequence(getCtx(), get_TrxName(), getFinalRange(), get_ID());
		
		if(seq!=null){
			throw new AdempiereException("Ya existe una secuencia que cubre el rango final de: "+seq.getInitialRange()+" a "+seq.getFinalRange());
		}
		
	}
	
	public void UpdateDocumentTypeSequence() {
		ValidateDocumentTypeSequence();
		
		MSequence docSequence = (MSequence) MDocType.get(getCtx(), getC_DocType_ID()).getDocNoSequence();		
		setPreviousSequence(Integer.toString(docSequence.getCurrentNext()));
		
		
		if(getInitialRange()>=getFinalRange())
			throw new AdempiereException("El rango inicial debe ser menor que el rango final");
		
		ValidateInitialSequence();
		ValidateFinalSequence();
		
		docSequence.setCurrentNext(getInitialRange());
		docSequence.saveEx(get_TrxName());		
		
		setNotificationMessage(NOTIFICATIONMESSAGE_None);
	}
	
	
	
	public String SendNotificationMessages(String email, String password) {
		ValidateDocumentTypeSequence();
		MSequence docSequence = (MSequence) MDocType.get(getCtx(), getC_DocType_ID()).getDocNoSequence();	
		int criticalInterval = get_ValueAsInt("CriticalInterval");
		int criticalNotificationsNo = get_ValueAsInt("CriticalNotificationNo");
		int breakValue = getFinalRange()-docSequence.getCurrentNext();
		BigDecimal calculatedNotifications =  BigDecimal.valueOf(breakValue).divide(BigDecimal.valueOf(criticalInterval), RoundingMode.CEILING);
		if(breakValue<=getCriticalBreak()) {
			if(calculatedNotifications.intValue()<criticalNotificationsNo){				
				String err = SendEmail(getR_MailText2_ID(), docSequence, email, password);
				if(err!=null)
					return err;
				setNotificationMessage(NOTIFICATIONMESSAGE_Critical);	
				set_ValueOfColumn("CriticalNotificationNo", calculatedNotifications.intValue());
			}
		}	
		if(breakValue<=getBreak() && (getNotificationMessage().equals(NOTIFICATIONMESSAGE_None))) {
			String err = SendEmail(getR_MailText_ID(), docSequence, email, password);
			if(err!=null)
				return err;
			setNotificationMessage(NOTIFICATIONMESSAGE_First);
		}
			
		return null;
	}
	
	public String SendEmail(int R_MailText_ID,MSequence docSequence, String emailFrom, String password) {
		MClient client = MClient.get(getCtx(), getAD_Client_ID());
		MMailText mailtext = new MMailText(getCtx(),R_MailText_ID,get_TrxName());
		mailtext.setPO(this);//po (this is the information of PO to put on the mail text )

		String subject = "Control de Folios"
		+ ": " + (mailtext.getMailHeader()!=null?mailtext.getMailHeader():"");

		String message = mailtext.getMailText(true)
		+ "\n---------\n Tienda: " + MOrg.get(getCtx(), getAD_Org_ID()).getName()
		+ "\n Tipo de documento: " + MDocType.get(getCtx(), getC_DocType_ID()).getName()
		+ "\n Secuencia actual: " + docSequence.getCurrentNext()
		+ "\n Secuencia limite: " + getFinalRange()				
		+ "\n Número de folios restantes: " + (getFinalRange()-docSequence.getCurrentNext())	
		;
		String to = getEMail();
		EMail email = new EMail (client,
				emailFrom, to,
				   subject, message, mailtext.isHtml());
		if (client.isSmtpAuthorization())
			email.createAuthenticator (emailFrom, password);
		
		try {

			String msg = email.send();
			if (EMail.SENT_OK.equals (msg))
			{
				log.info("Sent EMail " + subject + " to " + to);
				return null;
			}
			else
			{
				log.warning("Could NOT Send Email: " + subject 
					+ " to " + to + ": " + msg
					+ " (" + client.getName() + ")");
				return  "Could NOT Send Email: " + subject
						+ ", to " + to 
						//+ ": " + message
						;
			}
		}catch(Exception e){			
			log.severe(e.toString());
			return "Could NOT Send Email: " + subject
					+ ", to " + to 
					//+ ": " + message
					;
		}
	}
	
	
	protected boolean beforeSave (boolean newRecord) {
		if(newRecord){
			set_ValueOfColumn("CriticalNotificationNo", 1000);
				
		}
		
		if(newRecord && is_ValueChanged(COLUMNNAME_C_DocType_ID))
			ValidateDocumentTypeSequence();
		
		if(is_ValueChanged(COLUMNNAME_InitialRange)|| is_ValueChanged(COLUMNNAME_FinalRange)) 
			UpdateDocumentTypeSequence();
		
		if (!Util.isEmpty(getEMail()) && (newRecord || is_ValueChanged("EMail"))) {
		}
		
		if(!newRecord && (is_ValueChanged(COLUMNNAME_Break) || is_ValueChanged(COLUMNNAME_CriticalBreak))) {
			setNotificationMessage(NOTIFICATIONMESSAGE_None);
			set_ValueOfColumn("CriticalNotificationNo", 1000);
			/*String err = SendNotificationMessages();
			if(err!=null){
				log.saveError("Error:", err);
				return false;
			}*/
		}
		
		if(!newRecord && is_ValueChanged("CriticalInterval")){
			int criticalInterval = get_ValueAsInt("CriticalInterval");
			if(criticalInterval<=0)
				throw new AdempiereException("El intervalo de notificaciones criticas debe ser mayor a 0");
			
		}
		
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2695209386882799037L;

}
