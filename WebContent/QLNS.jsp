<%@page import="java.util.HashMap"%>
<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.xml.parsers.SAXParser,
 javax.xml.parsers.SAXParserFactory,
 thangpq.XmlSaxParser,
 java.io.InputStream,
 java.util.List,
 thangpq.Employee,
 javax.xml.parsers.ParserConfigurationException,
 org.xml.sax.SAXException,
 java.util.logging.Logger,
 java.io.FileInputStream,
 java.util.logging.Level" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="parseXML" class="thangpq.XmlSaxParser"></jsp:useBean>	
	<%
	try {
		HashMap<String, Double> departIncome = new HashMap<String,Double>();
        SAXParser parser = null;        
        
        parser = SAXParserFactory.newInstance().newSAXParser();
               
        String jspPath = session.getServletContext().getRealPath("/WEB-INF");
        InputStream stream = new FileInputStream(jspPath+"/QLNS.xml");
        
        parser.parse(stream, parseXML);
        
        List<Employee> list = parseXML.getEmployees();
        for (Employee employee : list) {
            
            if(departIncome.containsKey(employee.getDepartment())){
            	departIncome.put(employee.getDepartment(), departIncome.get(employee.getDepartment()) 
            														+ employee.getSalary());
            } else departIncome.put(employee.getDepartment(), employee.getSalary());
            out.print(employee.displayTotalPersonalIncome(employee));
        }
        
        out.print(Employee.displayTotalSalaryOfDepartment(departIncome));
        out.print(Employee.displayTotalCompanyIncome(list));
    } catch (ParserConfigurationException | SAXException ex) {
        Logger.getLogger(XmlSaxParser.class.getName()).log(Level.SEVERE, null, ex);
    } catch(FileNotFoundException e){
    	Logger.getLogger(XmlSaxParser.class.getName()).log(Level.SEVERE, null, e);
    }

	%>
</body>
</html>