package wandui.dataOne;

public class SQLBuilder {
	
	public static final String SELECT_CLIENT_ID_BY_NAME = "select c.clientid from client c where c.client_name = ?";

    public static final String SELECT_CLIENT_NAME_BY_ID = "select c.client_name from client c where c.clientid = ?";


    public static final String USERS_TO_EXCLUDE =
            "'8682','43536','aa','liyah.walker_279928@noemail.com','aamir.addona@cigna.com','aaron.arter@cigna.com',"
                    + "'aaron.barnes@novartis.com','Aaron_Chan@symantec.com','aaron.hammon@cigna.com','aaron.peeters@nike.com',"
                    + "'abelton@opensystemstech.com','acmsp','adam.frankel@healthspring.com','adrian_mesbah@symantec.com','ahab',"
                    + "'ak31_manager','alba.rivera@cigna.com','alec.farley@nike.com','ALisa_Tomatis@symantec.com',"
                    + "'allyson.peterson@nike.com','alonzo','amacur@aerotek.com','amar.raman@gs.com','amelsa','amiadm2','amicc2',"
                    + "'amifin2','amihd2','amiller@apexsystemsinc.com','amis','ammsp','amosa','amsp','amy.peterson@nike.com',"
                    + "'amy.wadowsky@cigna.com','anaonemail@gmail.com','angela.hall2@cigna.com','anna.rim@mail.ru','anna_sa',"
                    + "'antsys','apmsp','aqkc','aqkf','aqks','armsp','arossi@aerotek.com','b_fin','BA-1','barry.mchale@cigna.com',"
                    + "'bkravtsov80','bniu','boris_msp','bunny','cl_2','cl_serv','client_vo','Cronin','cs','cs_billing','debg',"
                    + "'denismsp','diana.johnson@alconlabs.com','dm@gm.com','Dmitriy','dmpars','doroty.ramzey@mail.ru','dtran',"
                    + "'dub','e@mail.ru ','ed_msp','eh','ek_msp','ekaterina.volynets','ekmsp','eksa','elya_sa','emsp','fa',"
                    + "'finance','finance_billing','frank.abate.supplier','fullpin','gendalf01','gi_msp','h_gold@mail.com',"
                    + "'hemmings.andrew@gene.com','hw@gm.com','ik_msp','ikolesnik_msp','ink_sys','ismsp',"
                    + "'jack.dephillips@cigna.com','jcorder3','joejohnson','joereddy','jstripunsky@kpmg.com',"
                    + "'kate.smith@kurtsalmon.com','kativol@i.ua ','kativolynets_SU ','kativolynetsSU ','kcoleman_prosupplier',"
                    + "'kevsys','kmc','kon','ks_msp','ks_sa','kyoko','lisa','lumsp','luna','m_fin','m_msp','m_msp1','m_msp2',"
                    + "'m_msp3','m_msp4','m_msp5','manager','managmn','meganrollinglove@gmail.com','mnp@gmail.com','mr.buzadzhy',"
                    + "'mspmar','mspmn','mspp','msptest','nssa','Overlord','pablo','patricia.surub@mail.ru','pine','qalyna',"
                    + "'qalyna - MSP','qamsp','qastage','rarnold_info','rmsp','rmspa','rnazir','rosec','rosenstern_msp','rw',"
                    + "'sa','Sarkar ','sm_msp','smorin','smz_msp','smz_sys','some5@i.ua','Stacy.Snyder@sbcglobal.net','Stewie',"
                    + "'stone','sup','sup_1','suppl','sys_msp','sysadmin1@gmail.com','ta-disk7','td_manager',"
                    + "'tgolubtest@gmail.com','user','vatas','vita_msp','vo_fin','vo_standard','vvsys','wh@gm.com',"
                    + "'yana.dolya@mail.ru','kgmsp','DBASU','newbyman','aaron.kanter@novartis.com','awaite@bmrn.com',"
                    + "'aaron.ilan@bmrn.com','adam.borenstein@bmrn.com','ango@bmrn.com','ibr','KH-sa',"
                    + "'cfrench','aaron.butler@cigna.com','ahe@illumina.com','BWBBOWMAN','rachel.mcinnis@gentiva.com',"
                    + "'Andy_Du@symantec.com','Aaron_Beck@symantec.com', "
                    + "'yamel' ,'vh_sa','vetal','vh_cs','vh_hd','vh_f','vh_msp','msp','ssovik@noemail.com','nmsp','nmspa','ser', 'varun.madnani@prounlimited.com', 'nishi.soni@prounlimited.com', "
                    + "'bivila.babu', 'rahul.sharma@prounlimited.com', 'pranali.telang@prounlimited.com' ";

    public static final String USERS_WITH_ONE_OF_FOLLOWING_DISCOVERY_AND_MGR_NON_EDIT_REQ_PERMISSION = "SELECT distinct userid FROM user_permission_role WHERE permission_roleid in(39, 41, 47, 13588402, 13588403, 11883333) ";

    public static final String USER_TEMPLATE =
            "SELECT ur.username  AS userName, ur.userid    AS userId, ur.personid  AS personId, "
                    + "pn.firstname AS firstName, pn.lastname  AS lastName, pn.email     AS email, ur.user_type as userType "
                    + "FROM users ur JOIN person pn ON pn.personid = ur.personid WHERE  ur.personid != '0' "
                    + "and ur.active = 'T' and ur.username not in (" + USERS_TO_EXCLUDE + ") "
                    + "AND ur.personid in (%s) AND ur.userid not in ("
                    + USERS_WITH_ONE_OF_FOLLOWING_DISCOVERY_AND_MGR_NON_EDIT_REQ_PERMISSION
                    + ") ORDER BY dbms_random.value";

    public static final String SELECT_MSP_WITH_CLIENT_SERVICE_ROLE =
            "select u.personid as personid from users u " + "where u.active = 'T' " + "and u.locked_user ='F' "
                    + "and u.personid in " + "(select distinct cmr.msp_repid from CLIENT_MSP_REP cmr "
                    + "where cmr.clientid = ?) " + "and u.userid in "
                    + "(select userid from USER_PERMISSION_ROLE where PERMISSION_ROLEID = 4) "
                    + "and u.userid NOT IN(select userid from LOGIN_ACCESS_TYPE_MSP where userid = u.userid and ACCESS_TYPE IN ('Single Sign On')) "
                    + "and u.user_type = 'MSP' " + "and rownum <50 ";

    public static final String SELECT_PASSWORD_BY_USERNAME = "select pw90.decrypt(password) from users where username = ? ";

    public static final String SELECT_PASSWORD_BY_USERID = "select pw90.decrypt(password) from users where userid = ? ";


}
