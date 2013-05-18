package com.pne.arch.repository;

import java.util.List;

import com.pne.arch.entity.Team;

public interface TeamRepository {

	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> getTeams();

}