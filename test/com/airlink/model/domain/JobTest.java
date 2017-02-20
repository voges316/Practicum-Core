package com.airlink.model.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JobTest {
	
	Job badJob;
	Job goodJob;
	Job sameJob;

	@Before
	public void setUp() throws Exception {
		badJob = new Job();
		
		goodJob = new Job("Training", 3);
		
		sameJob = new Job("Training", 3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		assertFalse("Jobs should not be equal", goodJob.equals(badJob));
		assertTrue("Jobs should equal one another", goodJob.equals(sameJob));		
	}

	@Test
	public void testValidate() {
		assertFalse("Job should not be valid", badJob.validate());
		assertTrue("Should be a valid job", goodJob.validate());
	}

}
