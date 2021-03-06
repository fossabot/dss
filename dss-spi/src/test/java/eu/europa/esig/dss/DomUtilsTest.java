package eu.europa.esig.dss;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

public class DomUtilsTest {

	private static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8'?>";
	private static final String XML_TEXT = "<hello><world></world></hello>";

	private static final String INCORRECT_XML_TEXT = "<hello><world></warld></hello>";

	@Test
	public void testNoHeader() {
		InputStream is = new ByteArrayInputStream(XML_TEXT.getBytes());
		assertNotNull(DomUtils.buildDOM(is));
		assertNotNull(DomUtils.buildDOM(XML_TEXT));
		assertNotNull(DomUtils.buildDOM(new InMemoryDocument(XML_TEXT.getBytes(), "my xml")));
	}

	@Test(expected = DSSException.class)
	public void testNoHeaderError() {
		DomUtils.buildDOM(INCORRECT_XML_TEXT);
	}

	@Test
	public void testHeader() {
		InputStream is = new ByteArrayInputStream((XML_HEADER + XML_TEXT).getBytes());
		assertNotNull(DomUtils.buildDOM(is));
		assertNotNull(DomUtils.buildDOM(XML_HEADER + XML_TEXT));
		assertNotNull(DomUtils.buildDOM(new InMemoryDocument((XML_HEADER + XML_TEXT).getBytes(), "my xml")));
	}

	@Test(expected = DSSException.class)
	public void testHeaderError() {
		DomUtils.buildDOM(XML_HEADER + INCORRECT_XML_TEXT);
	}

	@Test(expected = DSSException.class)
	public void testExpansionXml() throws Exception {
		assertNotNull(DomUtils.buildDOM(new FileInputStream("src/test/resources/xml_expansion.xml")));
	}

	@Test
	public void testEntityXml() throws Exception {
		// Should ignore the URL embedded in the DTD
		assertNotNull(DomUtils.buildDOM(new FileInputStream("src/test/resources/xml_entity.xml")));
	}

}
