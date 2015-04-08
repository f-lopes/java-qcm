package com.ingesup.java.qcm.database;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.RoleService;
import com.ingesup.java.qcm.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 1/25/2015.
 * <florian.lopes@outlook.com>
 */
@Component
@EnableConfigurationProperties(value = {QcmDatabaseProperties.class, QcmAdministrationProperties.class})
public class DatabaseLoader {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);
	private static final boolean debug = logger.isDebugEnabled();

	private final UserService userService;
	private final GradeService gradeService;
	private final CourseService courseService;
	private final RoleService roleService;
	private final QcmDatabaseProperties databaseProperties;
	private final QcmAdministrationProperties administrationProperties;

	@Autowired
	public DatabaseLoader(UserService userService, GradeService gradeService,
						  CourseService courseService, RoleService roleService,
						  QcmDatabaseProperties databaseProperties, QcmAdministrationProperties administrationProperties) {
		this.userService = userService;
		this.gradeService = gradeService;
		this.courseService = courseService;
		this.roleService = roleService;
		this.databaseProperties = databaseProperties;
		this.administrationProperties = administrationProperties;
	}

	@PostConstruct
	private void initDatabaseData() {
		if (databaseProperties.isInitialize()) {
			logger.info("Initializing database ...");
			initRoles();
			initGrades();
			initCourses();
			initAdmin();
		}
	}

	private void initRoles() {
		Set<Role> roles = new HashSet<>();
		String[] rolesNames = StringUtils.split(databaseProperties.getRoles(), QcmDatabaseProperties.SEPARATOR);

		if (debug) {
			logger.debug("Adding roles {}", rolesNames);
		}

		for (String roleName : rolesNames) {
			roles.add(new Role(roleName));
		}

		roleService.add(roles);
	}

	private void initGrades() {
		Set<Grade> grades = new HashSet<>();
		String[] gradesNames = StringUtils.split(administrationProperties.getGrades(), QcmAdministrationProperties.SEPARATOR);

		if (debug) {
			logger.debug("Adding grades {}", gradesNames);
		}

		for (String gradeName : gradesNames) {
			grades.add(new Grade(gradeName));
		}

		gradeService.add(grades);
	}

	private void initCourses() {
		Set<Course> courses = new HashSet<>();
		String[] coursesNames = StringUtils.split(administrationProperties.getCourses(), QcmAdministrationProperties.SEPARATOR);

		if (debug) {
			logger.debug("Adding courses {}", coursesNames);
		}

		for (String courseName : coursesNames) {
			courses.add(new Course(courseName));
		}

		courseService.add(courses);
	}

	private void initAdmin() {
		User adminUser = new User(administrationProperties.getFirstname(),
								  administrationProperties.getLastname(),
								  administrationProperties.getEmail(),
								  administrationProperties.getPassword());

		if (debug) {
			logger.debug("Adding admin {}", adminUser);
		}

		adminUser.addRole(RoleEnum.ROLE_ADMIN);

		userService.add(adminUser);
	}
}
