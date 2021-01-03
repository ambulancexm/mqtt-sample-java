package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateApp {

	public static void main(String[] args) {
		
		PositionBn posBn = new PositionBn();
		
		posBn.setIdPosition(1);
		posBn.setVille("toulouse");
		posBn.setLieuDit("rue de le decouverte");
		posBn.setPiece("cuisine");
		posBn.setHauteur(150);
		
		try{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(posBn);
		t.commit();
		session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
