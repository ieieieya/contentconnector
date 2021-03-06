package com.gentics.cr.lucene.indexer.transformer.ppt.test;

import java.io.InputStream;

import org.apache.poi.util.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gentics.cr.CRResolvableBean;
import com.gentics.cr.configuration.GenericConfiguration;
import com.gentics.cr.lucene.indexer.transformer.AbstractTransformerTest;
import com.gentics.cr.lucene.indexer.transformer.ContentTransformer;
import com.gentics.cr.lucene.indexer.transformer.ppt.PPTContentTransformer;

public class PPTContentTransformerTest extends AbstractTransformerTest {
	CRResolvableBean bean;
	GenericConfiguration config;

	@Before
	public void init() throws Exception {
		bean = new CRResolvableBean();

		InputStream stream = PPTContentTransformerTest.class.getResourceAsStream("testdoc.ppt");
		byte[] arr = IOUtils.toByteArray(stream);
		bean.set("binarycontent", arr);

		config = new GenericConfiguration();
		config.set("attribute", "binarycontent");
	}

	@Test
	public void testTransformer() throws Exception {
		ContentTransformer t = new PPTContentTransformer(config);
		t.processBean(bean);
		String s = (String) bean.get("binarycontent");
		String x = "Click to edit Master title style Click to edit Master text styles\rSecond level\rThird level\rFourth level\rFifth level Test Text This text contains special characters \u00D6\u00DC\u00C4\u00F6\u00FC\u00E4\u00DF? ";
		Assert.assertEquals(x, s);
	}

	@After
	public void tearDown() throws Exception {

	}
}
