package controllers;

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
		//	cm.createQuery utilizza il linguaggio JPQL
		List<Integer> fatture = em.createQuery("select f.id from Fattura f", Integer.class).getResultList();
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(fatture));
		//	response.getWriter().append(om.writeValueAsString(fatture));
	}

}
