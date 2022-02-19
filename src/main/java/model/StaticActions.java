package main.java.model;

public class StaticActions implements IModel {

	// Timer actions
	public static final String ACTION_TIMER_START = "action_timer_start";
	public static final String ACTION_TIMER_PAUSE = "action_timer_pause";
	public static final String ACTION_TIMER_STOP = "action_timer_stop";
	public static final String ACTION_TIMER_RESET = "action_timer_reset";
	public static final String ACTION_TIMER_SAVE = "action_timer_save";
	public static final String ACTION_LOAD_SERVICES = "action_load_services";
	public static final String ACTION_SET_SERVICE = "action_set_service";
	public static final String ACTION_LOAD_PROJECTS = "action_load_projects";
	public static final String ACTION_SET_PROJECT = "action_set_project";
	public static final String ACTION_GET_HOURLIST = "action_get_hourlist"; // Only for TestHourEntryController.java
	public static final String ACTION_SEARCH_PROJECTS = "action_search_projects";
	public static final String ACTION_SAVE_PROJECT = "action_save_project";
	public static final String ACTION_RESET_PROJECTS = "action_reset_projects";
	public static final String ACTION_SAVE_CUSTOMER = "action_save_customer";
	public static final String ACTION_SAVE_SERVICE = "action_save_service";
	
	public static final String ACTION_SESSION_OVERVIEW_RESET = "action_sessionoverview_reset";
	public static final String ACTION_SESSION_OVERVIEW_LOAD = "action_session_load";
	public static final String ACTION_SESSION_OVERVIEW_SEARCH = "action_session_search";
	public static final String ACTION_SESSION_OVERVIEW_SET_PROJECT = "action_session_overview_set_project";
	public static final String ACTION_SESSION_OVERVIEW_SET_CLIENT = "action_session_overview_set_client";
	public static final String ACTION_SESSION_OVERVIEW_SET_SERVICE = "action_session_overview_set_service";
	public static final String ACTION_SESSION_NEW_SET_PROJECT = "action_session_new_set_project";
	public static final String ACTION_SESSION_NEW_SET_SERVICE = "action_session_new_set_service";
	public static final String ACTION_SESSION_NEW_SAVE = "action_session_save";

	public static final String ACTION_MENU_DASHBOARD = "action_menu_dashboard";
	public static final String ACTION_MENU_PROJECTS = "action_menu_projects";
	public static final String ACTION_MENU_SESSIONS = "action_menu_sessions";
	public static final String ACTION_MENU_ACCOUNT = "action_menu_account";
	public static final String ACTION_MENU_LOGOUT = "action_menu_logout";
	
	public static final String ACTION_NPROJECT_SAVE = "action_nproject_save";
	public static final String ACTION_NPROJECT_RESET = "action_nproject_reset";
	public static final String ACTION_PROJECT_SET_CLIENT = "action_project_set_client";;
	
}
