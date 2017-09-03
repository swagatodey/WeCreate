package com.wecreate.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlingServlet(paths="/bin/formDataServlet", methods="POST")
public class FormDataServlet extends SlingAllMethodsServlet {
private static final Logger logger = LoggerFactory.getLogger(FormDataServlet.class);

@Override
protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
logger.error("*********inside post method*********");
String firstN = request.getParameter("fname");
String lastN = request.getParameter("lname");
String url =request.getParameter("pathtosave");
logger.error("First Name:" + firstN + " Last Name:" + lastN + " pathtosave:" + url);
//Method 1
/*Here we are getting resourceResolver from request. From resourceResolver we are getting Session. From session
we are getting node.*/
ResourceResolver resourceResolver = request.getResourceResolver();
Session session = resourceResolver.adaptTo(Session.class);
logger.error("********* Session " + session.getUserID());
try {
Node rootNode = session.getNode(url);
rootNode.setProperty("firstName", firstN);
rootNode.setProperty("lastName", lastN);
session.save();
} catch (PathNotFoundException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
} catch (RepositoryException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
//Method 2
/*Here we are getting resourceResolver from request. From resourceResolver we are getting resource. From resource
we are getting node.
ResourceResolver resourceResolver = request.getResourceResolver();
Resource resource = resourceResolver.getResource(url);
Session session = resourceResolver.adaptTo(Session.class);
Node node = resource.adaptTo(Node.class);
try {
node.setProperty("firstName", firstN);
node.setProperty("lastName", lastN);
session.save();
} catch (ValueFormatException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (VersionException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (LockException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (ConstraintViolationException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (RepositoryException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}*/
//Method 3

/*Here we are getting resourceResolver from request. From resourceResolver we are getting node without resource.
ResourceResolver resourceResolver = request.getResourceResolver();
Session session = resourceResolver.adaptTo(Session.class);
Node node = resourceResolver.getResource(url).adaptTo(Node.class);
try {
node.setProperty("firstName", firstN);
node.setProperty("lastName", lastN);
session.save();
} catch (ValueFormatException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (VersionException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (LockException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (ConstraintViolationException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (RepositoryException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}*/
}

}
