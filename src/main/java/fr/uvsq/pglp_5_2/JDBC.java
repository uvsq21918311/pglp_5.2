package fr.uvsq.pglp_5_2;

import fr.uvsq.pglp_5_2.AbstractDAOFactory.DAOType;
import fr.uvsq.pglp_5_2.Personnel.PersonnelBuilder;
public enum JDBC {
	ENVIRONNEMENT;
	
	private void run(String[] args, DAOType dt) {
		DAO<Personnel> personnelDAO = AbstractDAOFactory
				.getFactory(dt) // Factory
				.getPersonnelDAO();
		DAO<PersonnelGroupe> pgDAO = AbstractDAOFactory
				.getFactory(dt)
				.getPersonnelGroupeDAO();
		// Creation personnel
		Personnel Kamal = new PersonnelBuilder("EL KADI", "Kamal", "Employé")
				   			.build();
		Personnel Henry = new PersonnelBuilder("David", "Henry", "Employé")
							.build();
		PersonnelGroupe pg = new PersonnelGroupe();
		PersonnelGroupe spg = new PersonnelGroupe();
		pg.addPersonnel(spg);
		pg.addPersonnel(Henry);
		spg.addPersonnel(Kamal);
		// Ajout DAO
		pgDAO.create(pg);
		pgDAO.create(spg);
		personnelDAO.create(Kamal); // update
		personnelDAO.create(Henry); // update
		System.out.println("\t" + pgDAO.read(spg.getId()));
		System.out.println("\t" + personnelDAO.read("Henry"));
	}
	
	public static void main(String[] args) {
		JdbcInitializer jinit = new JdbcInitializer();
		// the 1st time, use jinit.createTables()
		jinit.createTables();
		//jinit.dropCreateTables(); 
		
		ENVIRONNEMENT.run(args, DAOType.JDBC);
	}
}
