package controllers;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Fattura;
import utils.JPAUtil;

public class FatturaControllerTest {

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws JsonProcessingException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<Fattura> fatture = em.createQuery("select c from fattura c", Fattura.class).getResultList();
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(fatture));
		//response.getWriter().append(om.writeValueAsString(fatture));
	}

}
