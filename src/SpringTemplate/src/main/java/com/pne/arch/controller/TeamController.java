package com.pne.arch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pne.arch.entity.Team;
import com.pne.arch.service.TeamService;

@Controller
@RequestMapping(value="/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@RequestMapping(value="/add")
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView = new ModelAndView("add_team");
		modelAndView.addObject("team", new Team());
		return modelAndView;
	}

	@RequestMapping(value="/add/process")
	public ModelAndView addingTeam(@ModelAttribute Team team) {

		ModelAndView modelAndView = new ModelAndView("list_teams");

		teamService.addTeam(team);
		modelAndView.addObject("message", "Team was successfully added.");

		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);

		return modelAndView;
	}

	@RequestMapping(value="/list")
	public ModelAndView listOfTeams() {
		ModelAndView modelAndView = new ModelAndView("list_teams");

		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);

		return modelAndView;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit_team");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("team",team);
		return modelAndView;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("list_teams");

		teamService.updateTeam(team);
		modelAndView.addObject("message", "Team was successfully edited.");

		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);

		return modelAndView;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("list_teams");

		teamService.deleteTeam(id);
		modelAndView.addObject("message", "Team was successfully deleted.");

		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);

		return modelAndView;
	}

}
