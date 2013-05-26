package com.pne.arch.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.pne.arch.entity.Team;

public interface TeamService {

	@PreAuthorize("hasRole('PERM_TEAM_ADD')")
	public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> getTeams();

}
