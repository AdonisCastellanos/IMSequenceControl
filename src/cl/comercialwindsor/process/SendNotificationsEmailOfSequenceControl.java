package cl.comercialwindsor.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MSequence;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import cl.comercialwindsor.model.MIMSequenceControl;

public class SendNotificationsEmailOfSequenceControl  extends SvrProcess{

	private int m_AD_Client_ID;
	
	private String m_Email;
	
	private String m_EmailDomain;	

	private String m_Password;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = para[i].getParameterAsInt();
			else if (name.equals("Email"))
				m_Email = (String)para[i].getParameter();
			else if (name.equals("EmailDomain"))
				m_EmailDomain = (String)para[i].getParameter();
			else if (name.equals("Password"))
				m_Password = (String)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {

		if(m_Email == null || m_EmailDomain == null || m_Password==null)
			return "Debe proporcionar un correo y una clave";
		
		m_Email += "@"+m_EmailDomain;
		
		
		m_AD_Client_ID = getAD_Client_ID();
		int emailSended = 0;
		int errors = 0;
		StringBuilder errorMessage = new StringBuilder();
		
		StringBuilder sql = new StringBuilder("SELECT sqc.").append(MIMSequenceControl.COLUMNNAME_IM_SequenceControl_ID)
				.append(" ,(sqc.finalrange - sq.currentnext) as breackcount ")
				.append(" FROM ").append(MIMSequenceControl.Table_Name).append(" sqc ")
				.append(" JOIN ").append(MDocType.Table_Name).append(" dt  ON sqc.C_DocType_ID=dt.C_DocType_ID")
				.append(" JOIN ").append(MSequence.Table_Name).append(" sq ON dt.docnosequence_id=sq.AD_Sequence_ID")
				;
		
		//where
		sql.append(" WHERE sq.AD_Client_ID=").append(m_AD_Client_ID)
		.append(" AND sqc.isactive='Y'")
		.append(" AND (sqc.finalrange-sq.currentnext)<=break ")
		;
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		
		try {
			pstm = DB.prepareStatement(sql.toString(),get_TrxName());
			rs = pstm.executeQuery();
			while(rs.next()) {
				int IM_SequenceControl_ID = rs.getInt(MIMSequenceControl.COLUMNNAME_IM_SequenceControl_ID);
				MIMSequenceControl seqControl = new MIMSequenceControl(getCtx(), IM_SequenceControl_ID, get_TrxName());
				String err = seqControl.SendNotificationMessages(m_Email, m_Password);	
				
				if(err!=null){
					errorMessage.append(err).append(", ");
					errors++;
				}
				else {
					emailSended++;			
					seqControl.saveEx(get_TrxName());			
				}			
			}			
			
		}catch(Exception e) {
			throw new AdempiereException(e);
		}finally {
			DB.close(rs, pstm);
			pstm = null;
			rs = null;
		}
		
		
		
		return "Emails Enviados:"+emailSended+ ", Errores:"+errors+", Mensajes de Error:("+errorMessage.toString()+")";
	}

}
