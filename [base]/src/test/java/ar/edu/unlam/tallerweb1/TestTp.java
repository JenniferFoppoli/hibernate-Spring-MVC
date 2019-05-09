package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class TestTp extends SpringTest {

	@Test @Transactional @Rollback
	public void testQueBuscaPaisesDeHablaInglesa() {

		Pais eeuu= new Pais();
		eeuu.setIdioma("ingles");
		
		Pais inglaterra= new Pais();
		inglaterra.setIdioma("ingles");
		
		Pais argentina= new Pais();
		argentina.setIdioma("español");
		
		Pais mexico= new Pais();
		mexico.setIdioma("español");
		
		
		Session session= getSession();
		
		session.save(eeuu);
		session.save(inglaterra);
		session.save(argentina);
		session.save(mexico);
		
		
		List<Pais> paisesDeHablaInglesa = session.createCriteria(Pais.class)
				.add(Restrictions.eq("idioma","ingles"))
				.list();
		
	assertThat(paisesDeHablaInglesa.size()).isEqualTo(2);
		
	}

	@Test @Transactional @Rollback
	public void testQueBuscaPaisesDeEuropa() {
		
		Continente europa = new Continente();
		europa.setNombre("europa");
		Continente america = new Continente();
		america.setNombre("america");
		
		Session session = getSession();
		
		session.save(europa);
		session.save(america);
		
		Pais eeuu= new Pais();
		eeuu.setContinente(america);
		
		Pais inglaterra= new Pais();
		inglaterra.setContinente(europa);
		
		Pais alemania= new Pais();
		alemania.setContinente(europa);
		
		Pais croacia= new Pais();
		croacia.setContinente(europa);
		
	
		session.save(eeuu);
		session.save(inglaterra);
		session.save(alemania);
		session.save(croacia);
		
		
		
		List<Pais> paisesDeEuropa = session.createCriteria(Pais.class)
				.createAlias("continente","cont")
				.add(Restrictions.eq("cont.nombre","europa"))
				.list();
		
		
		assertThat(paisesDeEuropa.size()).isEqualTo(3);
		
	}
	
	
	@Test  @Transactional @ Rollback
	public void testQueBusquePaisesCuyaCapitalEsteAlNorteDelTropicoDeCancer()
	{
		

		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud(38.9);
		Ubicacion ubicacion2 = new Ubicacion();
		ubicacion2.setLatitud(45.4);
		Ubicacion ubicacion3 = new Ubicacion();
		ubicacion3.setLatitud(55.75);
		Ubicacion ubicacion4 = new Ubicacion();
		ubicacion4.setLatitud(-34.61);
		Ubicacion ubicacion5 = new Ubicacion();
		ubicacion5.setLatitud(-12.04);
		
		Session session = getSession();
		
		session.save(ubicacion1);
		session.save(ubicacion2);
		session.save(ubicacion3);
		session.save(ubicacion4);
		session.save(ubicacion5);
		
		Ciudad washington = new Ciudad();
		Ciudad ottawa = new Ciudad();
		Ciudad moscu = new Ciudad();
		Ciudad buenosAires = new Ciudad();
		Ciudad lima = new Ciudad();
		
		washington.setUbicacionGeografica(ubicacion1);
		ottawa.setUbicacionGeografica(ubicacion2);
		moscu.setUbicacionGeografica(ubicacion3);
		buenosAires.setUbicacionGeografica(ubicacion4);
		lima.setUbicacionGeografica(ubicacion5);
		
		session.save(washington);
		session.save(ottawa);
		session.save(moscu);
		session.save(buenosAires);
		session.save(lima);
		
		
		Pais eeuu = new Pais();
		Pais canada = new Pais();
		Pais rusia = new Pais();
		Pais argentina = new Pais();
		Pais peru = new Pais();
		
		eeuu.setCapital(washington);
		canada.setCapital(ottawa);
		rusia.setCapital(moscu);
		argentina.setCapital(buenosAires);
		peru.setCapital(lima);
		
		session.save(eeuu);
		session.save(canada);
		session.save(rusia);
		session.save(argentina);
		session.save(peru);


		List<Pais> paisesConCapitalAlNorteDelTropicoDeCancer =session.createCriteria(Pais.class)
			.createAlias("capital", "cap")
			.createAlias("cap.ubicacionGeografica", "ubi")
			.add(Restrictions.gt("ubi.latitud", 22.44))
			.list();
		
		
		assertThat(paisesConCapitalAlNorteDelTropicoDeCancer.size()).isEqualTo(3);
		
	}
	
	
	@Test  @Transactional @ Rollback
	public void testQueBusqueCiudadesEstenEnelHemisferioSur()
	{
	
		
		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud(38.9);
		Ubicacion ubicacion2 = new Ubicacion();
		ubicacion2.setLatitud(45.4);
		Ubicacion ubicacion3 = new Ubicacion();
		ubicacion3.setLatitud(55.75);
		Ubicacion ubicacion4 = new Ubicacion();
		ubicacion4.setLatitud(-34.61);
		Ubicacion ubicacion5 = new Ubicacion();
		ubicacion5.setLatitud(-12.04);
		
		Session session = getSession();
		
		session.save(ubicacion1);
		session.save(ubicacion2);
		session.save(ubicacion3);
		session.save(ubicacion4);
		session.save(ubicacion5);
		
		Ciudad washington = new Ciudad();
		Ciudad ottawa = new Ciudad();
		Ciudad moscu = new Ciudad();
		Ciudad buenosAires = new Ciudad();
		Ciudad lima = new Ciudad();
		
		washington.setUbicacionGeografica(ubicacion1);
		ottawa.setUbicacionGeografica(ubicacion2);
		moscu.setUbicacionGeografica(ubicacion3);
		buenosAires.setUbicacionGeografica(ubicacion4);
		lima.setUbicacionGeografica(ubicacion5);
		
		session.save(washington);
		session.save(ottawa);
		session.save(moscu);
		session.save(buenosAires);
		session.save(lima);
		
		
		Pais eeuu = new Pais();
		Pais canada = new Pais();
		Pais rusia = new Pais();
		Pais argentina = new Pais();
		Pais peru = new Pais();
		
		eeuu.setCapital(washington);
		canada.setCapital(ottawa);
		rusia.setCapital(moscu);
		argentina.setCapital(buenosAires);
		peru.setCapital(lima);
		
		session.save(eeuu);
		session.save(canada);
		session.save(rusia);
		session.save(argentina);
		session.save(peru);

		
		List<Ciudad> ciudadesQueEstanEnElHemisferioSur =session.createCriteria(Ciudad.class)
				.createAlias("ubicacionGeografica", "ubi")
				.add(Restrictions.lt("ubi.latitud",0.0))
				.list();
		
	
		assertThat(ciudadesQueEstanEnElHemisferioSur.size()).isEqualTo(2);
		
	}
		
}
