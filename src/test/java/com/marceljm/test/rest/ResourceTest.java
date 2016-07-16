package com.marceljm.test.rest;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.marceljm.entity.Hero;
import com.marceljm.rest.HeroResource;

public class ResourceTest extends JerseyTest {

	Hero hero = new Hero();

	Entity<Hero> entity = Entity.entity(hero, MediaType.APPLICATION_JSON);

	@Override
	protected Application configure() {
		return new ResourceConfig(HeroResource.class);
	}

	private void post() {
		Response response = target("/heroes").request().post(entity);
		Assert.assertEquals(response.getStatus(), 200);
	}

	private void get(Long id) {
		Response response = target("/heroes/" + id).request(MediaType.APPLICATION_JSON).get();
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(hero.getName(), response.readEntity(Hero.class).getName());
	}

	private void get(String name) {
		Response response = target("/heroes/name/" + name).request(MediaType.APPLICATION_JSON).get();
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals(name, response.readEntity(Hero.class).getName());
	}

	private Long getId(String name) {
		Response response = target("/heroes/name/" + name).request(MediaType.APPLICATION_JSON).get();
		return response.readEntity(Hero.class).getId();
	}

	private void getAll() {
		Response response = target("/heroes").request(MediaType.APPLICATION_JSON).get();
		Assert.assertEquals(200, response.getStatus());
		List<Hero> list = response.readEntity(new GenericType<List<Hero>>() {
		});
		Assert.assertEquals(2, list.size());
	}

	private void getFail(Long id) {
		Response response = target("/heroes/" + id).request(MediaType.APPLICATION_JSON).get();
		Assert.assertNotEquals(200, response.getStatus());
	}

	private void put() {
		Response response = target("/heroes").request().put(entity);
		Assert.assertEquals(response.getStatus(), 200);
	}

	private void delete(Long id) {
		Response response = target("/heroes/" + id).request().delete();
		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void postTest() {
		hero.setName("Superman");
		post();
		get("Superman");
	}

	@Test
	public void putTest() {
		postTest();
		hero.setName("Spiderman");
		put();
		get("Spiderman");
	}

	@Test
	public void deleteTest() {
		postTest();
		Long id = getId("Superman");
		delete(id);
		getFail(id);
	}

	@Test
	public void getTest() {
		hero.setName("Superman");
		post();
		hero.setName("Spiderman");
		post();
		Long id = getId("Spiderman");
		get(id);
		getAll();
	}

}
