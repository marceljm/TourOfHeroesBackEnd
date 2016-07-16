package com.marceljm.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.marceljm.entity.Hero;
import com.marceljm.entity.Message;
import com.marceljm.service.GenericService;

@Component
@Path("/heroes")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class HeroResource {

	@Inject
	GenericService<Hero> heroService;

	@GET
	public List<Hero> get() {
		return heroService.select(Hero.class);
	}

	@GET
	@Path("{id}")
	public Hero get(@PathParam("id") Long id) {
		return heroService.select(Hero.class, id);
	}

	@GET
	@Path("name/{name}")
	public Hero get(@PathParam("name") String name) {
		return heroService.select(Hero.class, name);
	}

	@POST
	public Response post(Hero hero) {
		heroService.insert(hero);
		return Response.ok(new Message("Hero Inserted")).build();
	}

	@PUT
	public Response put(Hero hero) {
		heroService.update(hero);
		return Response.ok(new Message("Hero Updated")).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Hero hero = heroService.select(Hero.class, id);
		heroService.delete(hero);
		return Response.ok(new Message("Hero Deleted")).build();
	}
}
