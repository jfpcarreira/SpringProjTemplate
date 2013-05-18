package com.pne.arch.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pne.arch.entity.Team;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void addTeam(Team team) {
		entityManager.persist(team);
	}

	public void updateTeam(Team team) {
		Team teamToUpdate = getTeam(team.getId());
		teamToUpdate.setName(team.getName());
		teamToUpdate.setRating(team.getRating());
		entityManager.merge(teamToUpdate);

	}

	public Team getTeam(int id) {
		Team team = (Team) entityManager.find(Team.class, id);
		return team;
	}

	public void deleteTeam(int id) {
		Team team = getTeam(id);
		if (team != null)
			entityManager.remove(team);
	}

	@SuppressWarnings("unchecked")
	public List<Team> getTeams() {
		return entityManager.createQuery("from Team").getResultList();
	}

}
