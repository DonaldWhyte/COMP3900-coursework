package sc10dw.distributed.cw2.web;

/**
 * @author sc10dw
 * Contains general configuration parameters for the payroll web application.
 */
public class Config {

	/**
	 * Root path to this web application on the server its running on.
	 */
	public static final String ROOT_URL = "/payroll";
	/**
	 * Convenient constant which contains content type for HTML
	 * documents. This is placed in the servlets' response headers.
	 */
	public static final String HTTP_CONTENT_TYPE = "text/html";

}
