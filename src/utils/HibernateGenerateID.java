package utils;
 import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.QueryParameters;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
 /**
  * 
 * �����ƣ�      HibernateGenerateID   
 * ��������     ��hibernate�Ļ������Զ������Զ��������
 * �����ˣ�     andy_lj  
 * ����ʱ�䣺2012-08-10 ����11:40:50     
* �޸ı�ע��   
 * @version
  */
 public class HibernateGenerateID implements Configurable, IdentifierGenerator {
     public String sign;// user000000001�е�user
     public String classname; //ʵ���������
     public String pk;//��������
     public String idLength;//user000000001�ĳ���
     
    /**
      * ȡ��beans.hbm.xml�е��Զ����ֵ
      */
     @Override
     public void configure(Type arg0, Properties arg1, Dialect arg2)
             throws MappingException {
         this.classname = arg1.getProperty("classname");
         this.pk = arg1.getProperty("pk");
         this.sign = arg1.getProperty("sign");
         this.idLength = arg1.getProperty("idLength");
     }
     /**
      * ��������
      */
     @Override
     public Serializable generate(SessionImplementor arg0, Object arg1)
             throws HibernateException {
          //��������ĳ���
         int leng = Integer.valueOf(idLength);
         //��Ҫ��ѯ���ݿ�������ID��
         StringBuffer sql = new StringBuffer("select max(a.").append(pk)
                                                             .append(") from ")
                                                             .append(classname)
                                                             .append(" as a where a.")
                                                             .append(pk)
                                                             .append(" like '")
                                                             .append(sign)
                                                             .append("%'");
         QueryParameters qp = new QueryParameters();
         List ls = arg0.list(sql.toString(), qp);
         String max = (String) ls.get(0);
         int i = 0;
         //����ǵ�һ����Ӽ�¼��ô��������user000000001
         if (max == null || max.trim().equals("")) {
             max = "1";
             for(; i < leng-sign.length()-1; i++) {
                 max = "0" + max;
             }
             i = 0;
             return sign + max;
         }//���ǵ�һ�εĲ���,���Ҽ�¼�ĳ���û�г����������ļ��ж�ȡ�ĳ���
         else if(max != null && max.length() <= leng) {
             max = max.replaceAll(sign, "");
             Integer imax = Integer.parseInt(max) + 1;
             String returnnum = String.valueOf(imax);
             int zero = leng-sign.length()-returnnum.length();
             for(; i < zero; i++) {
                 returnnum = "0" + returnnum;
             }
             i = 0;
             return sign + returnnum;
         }//���ǵ�һ�εĲ���,��¼�ĳ��ȳ����˴������ļ��ж�ȡ�ĳ���
         else {
             leng = max.length();
             max = max.replaceAll(sign, "");
             Integer imax = Integer.parseInt(max) + 1;
             String returnnum = String.valueOf(imax);
             int zero = leng-sign.length()-returnnum.length();
             for(; i < zero; i++) {
                 returnnum = "0" + returnnum;
             }
             return sign + returnnum;
         }
     }
 
 }
