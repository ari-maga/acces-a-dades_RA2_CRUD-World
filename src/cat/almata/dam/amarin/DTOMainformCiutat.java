package cat.almata.dam.amarin;

public class DTOMainformCiutat {
	
	private int id = -1;
	private String nom;
	private String pais;
	private String districte;
	private long poblacio;
	
	public DTOMainformCiutat() {}

	public DTOMainformCiutat(String nom, String pais, String districte, long poblacio) {
		setNom(nom);
		setPais(pais);
		setDistricte(districte);
		setPoblacio(poblacio);
	}
	public DTOMainformCiutat(int id,String nom, String pais, String districte, long poblacio) {
		setId(id);
		setNom(nom);
		setPais(pais);
		setDistricte(districte);
		setPoblacio(poblacio);
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDistricte() {
		return districte;
	}

	public void setDistricte(String districte) {
		this.districte = districte;
	}

	public long getPoblacio() {
		return poblacio;
	}

	public void setPoblacio(long poblacio) {
		if(poblacio>=10000L && poblacio<=5000000000L)this.poblacio = poblacio;
	}
	
	

}
