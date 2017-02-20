package com.airlink.model.services.jobs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.airlink.model.domain.Job;

public class JobSvcJPAImplTest {
	
	// Setup
	private static JobSvcJPAImpl svc;
	private static Job snacko;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		svc = new JobSvcJPAImpl();
		snacko = new Job("Snack Officer", 1);
		snacko.setDescription("Stock the snack bar and other random tasks");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		svc.shutdown();
	}

	@Before
	public void setUp() throws Exception {
		snacko = svc.createJob(snacko);
	}

	@After
	public void tearDown() throws Exception {
		Job j = svc.deleteJob(snacko.getId());
		if (j != null) snacko = j;
	}

	@Test
	public void testCreateJob() {
		// Snacko was added on setup, so should be in db
		Job j = null;
		j = svc.getJob(snacko.getId());
		assertNotNull("Should have retreived a valid job from db", j);
		
	}

	@Test
	public void testGetJob() {
		Job j = svc.getJob(snacko.getId());
		assertTrue("DB employee and snacko should be equal", j.equals(snacko));
	}

	@Test
	public void testUpdateJob() {
		String update = "New job description";
		snacko.setDescription(update);
		svc.updateJob(snacko);
		
		Job j = svc.getJob(snacko.getId());
		assertTrue("Descriptions should match after update", 
				j.getDescription().equals(update));
	}

	@Test
	public void testDeleteJob() {
		Job j = svc.deleteJob(snacko.getId());
		
		Job k = svc.getJob(j.getId());
		assertTrue("Job should have been removed from db", k == null);
	}

	@Test
	public void testGetJobs() {
		List<Job> jobs = svc.getJobs();
		// Should have at least 1 job
		boolean isEmpty = jobs.isEmpty();
		
		assertFalse("Job list should't be empty", isEmpty);
	}

	@Test
	public void testAddEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEmployee() {
		fail("Not yet implemented");
	}

}
